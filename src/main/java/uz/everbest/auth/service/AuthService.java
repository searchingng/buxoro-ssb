package uz.everbest.auth.service;

import uz.everbest.auth.controller.vm.LoginVM;
import uz.everbest.auth.dto.JWTTokenDto;
import uz.everbest.auth.dto.RegistrationUserDto;
import uz.everbest.auth.dto.UserDto;
import uz.everbest.auth.errors.BadRequestAlertException;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    JWTTokenDto loginUser(HttpServletRequest request, LoginVM loginVM);

    UserDto register(RegistrationUserDto userDto) throws BadRequestAlertException;
}
