package uz.everbest.buxorossb.service;

import uz.everbest.buxorossb.controller.vm.LoginVM;
import uz.everbest.buxorossb.dto.UserDto;
import uz.everbest.buxorossb.dto.request.RegistrationUserDto;
import uz.everbest.buxorossb.dto.response.JWTTokenDto;
import uz.everbest.buxorossb.errors.BadRequestAlertException;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    JWTTokenDto loginUser(HttpServletRequest request, LoginVM loginVM);

    UserDto register(RegistrationUserDto userDto) throws BadRequestAlertException;
}
