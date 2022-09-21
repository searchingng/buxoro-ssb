package uz.everbest.auth.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.everbest.auth.dto.RegistrationUserDto;
import uz.everbest.auth.entity.User;

@Mapper(componentModel = "spring")
@Component
public interface RegistrationUserMapper extends EntityMapper<RegistrationUserDto, User> {

}
