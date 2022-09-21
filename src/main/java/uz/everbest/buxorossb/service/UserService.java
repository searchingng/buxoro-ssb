package uz.everbest.buxorossb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.everbest.buxorossb.dto.AlertResponseDto;
import uz.everbest.buxorossb.dto.JWTTokenDto;
import uz.everbest.buxorossb.dto.user.CreationUserDto;
import uz.everbest.buxorossb.dto.user.UserDto;

import java.util.Optional;


public interface UserService {

    Page<UserDto> findAll(Pageable pageable);

    Optional<UserDto> findOne(Long id);

    JWTTokenDto save(CreationUserDto userDto);

    AlertResponseDto delete(Long id) throws Exception;
}
