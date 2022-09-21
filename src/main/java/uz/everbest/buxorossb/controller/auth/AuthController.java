package uz.everbest.buxorossb.controller.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.everbest.buxorossb.controller.vm.LoginVM;
import uz.everbest.buxorossb.dto.JWTTokenDto;
import uz.everbest.buxorossb.dto.user.RegistrationUserDto;
import uz.everbest.buxorossb.dto.user.UserDto;
import uz.everbest.buxorossb.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

   @PostMapping("/login")
    public ResponseEntity<JWTTokenDto> login(HttpServletRequest request, @RequestBody @Valid LoginVM loginVM) {
        JWTTokenDto jwt = authService.loginUser(request, loginVM);
        if (jwt.isSuccess()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt.getToken());
            return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(jwt);

    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody @Valid RegistrationUserDto userDto) throws Exception {
        return ResponseEntity.ok(authService.register(userDto));
    }

}
