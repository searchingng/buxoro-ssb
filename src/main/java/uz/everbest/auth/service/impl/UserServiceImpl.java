package uz.everbest.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.everbest.auth.controller.vm.LoginVM;
import uz.everbest.auth.dto.AlertResponseDto;
import uz.everbest.auth.dto.JWTTokenDto;
import uz.everbest.auth.dto.RegistrationUserDto;
import uz.everbest.auth.dto.UserDto;
import uz.everbest.auth.entity.User;
import uz.everbest.auth.errors.BadRequestAlertException;
import uz.everbest.auth.repository.UserRepository;
import uz.everbest.auth.service.UserService;
import uz.everbest.auth.service.mapper.RegistrationUserMapper;
import uz.everbest.auth.service.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RegistrationUserMapper registrationUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthServiceImpl authService;
    private final HttpServletRequest httpServletRequest;

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        Page<UserDto> users = userRepository.findAll(pageable).map(userMapper::toDto);
        return users;
    }

    @Override
    public Optional<UserDto> findOne(Long id) {
        return userRepository.findById(id).map(userMapper::toDto);

    }

    @Override
    public JWTTokenDto save(RegistrationUserDto userDto) throws Exception {
        User user = registrationUserMapper.toEntity(userDto);
        boolean existsByUsername = userRepository.existsByUsername(userDto.getUsername());

        if (!userDto.getConfirmPassword().equals(userDto.getPassword())) {
            throw new BadRequestAlertException("password and confirm password is not match", "Admin", "confirmPassword");
        }

        // create
        if (existsByUsername){
            throw new BadRequestAlertException("Username already exits", "Admin", "username");
        }

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        JWTTokenDto jwtTokenDto = authService.loginUser(httpServletRequest,new LoginVM(userDto.getUsername(), userDto.getPassword()));
        return jwtTokenDto;

    }


    @Override
    public AlertResponseDto delete(Long id) {
        userRepository.deleteById(id);
        return new AlertResponseDto("User o'chirilmadi", false);
    }
}
