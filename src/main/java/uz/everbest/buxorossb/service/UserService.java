package uz.everbest.buxorossb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.everbest.buxorossb.dto.UserDto;
import uz.everbest.buxorossb.dto.request.CreationUserDto;
import uz.everbest.buxorossb.dto.response.AlertResponseDto;

import java.util.Optional;


public interface UserService {

    Page<UserDto> findAll(Pageable pageable);

    Optional<UserDto> findOne(Long id);

    UserDto save(CreationUserDto userDto);

    AlertResponseDto delete(Long id) throws Exception;
}
