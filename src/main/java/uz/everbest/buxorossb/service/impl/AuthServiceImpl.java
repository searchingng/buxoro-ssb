package uz.everbest.buxorossb.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.controller.vm.LoginVM;
import uz.everbest.buxorossb.dto.UserDto;
import uz.everbest.buxorossb.dto.request.RegistrationUserDto;
import uz.everbest.buxorossb.dto.response.JWTTokenDto;
import uz.everbest.buxorossb.entity.User;
import uz.everbest.buxorossb.entity.enums.Role;
import uz.everbest.buxorossb.errors.BadRequestAlertException;
import uz.everbest.buxorossb.repository.UserRepository;
import uz.everbest.buxorossb.security.jwt.JwtTokenProvider;
import uz.everbest.buxorossb.service.AuthService;
import uz.everbest.buxorossb.service.UserSessionService;
import uz.everbest.buxorossb.service.mapper.RegistrationUserMapper;
import uz.everbest.buxorossb.service.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider tokenProvider;
    private final UserSessionService userSessionService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserMapper userMapper;
    private final RegistrationUserMapper registrationUserMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JWTTokenDto loginUser(HttpServletRequest request, @NotNull LoginVM loginVM) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
        Authentication authentication = null;
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);
            userSessionService.save(request, (User) authentication.getPrincipal(), jwt);
            return new JWTTokenDto(jwt, true);
        } catch (Exception e) {
            return (new JWTTokenDto("Username or Password invalid", false));
        }
    }

    @Override
    public UserDto register(RegistrationUserDto userDto) throws BadRequestAlertException {
        User user = registrationUserMapper.toEntity(userDto);
        boolean existsByUsername = userRepository.existsByUsername(userDto.getUsername());

        if (!userDto.getConfirmPassword().equals(userDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password and confirm password is not match");
        }

        // create
        if (existsByUsername){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exits");
        }

        user.setRole(Role.USER);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }


}
