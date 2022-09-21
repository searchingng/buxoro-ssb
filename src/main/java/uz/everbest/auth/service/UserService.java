package uz.everbest.auth.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.everbest.auth.dto.AlertResponseDto;
import uz.everbest.auth.dto.JWTTokenDto;
import uz.everbest.auth.dto.RegistrationUserDto;
import uz.everbest.auth.dto.UserDto;

import java.util.Optional;


public interface UserService {

    Page<UserDto> findAll(Pageable pageable);

    Optional<UserDto> findOne(Long id);

    JWTTokenDto save(RegistrationUserDto userDto) throws Exception;

    AlertResponseDto delete(Long id) throws Exception;
}
