package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.everbest.buxorossb.dto.user.RegistrationUserDto;
import uz.everbest.buxorossb.entity.User;

@Mapper(componentModel = "spring")
@Component
public interface RegistrationUserMapper extends EntityMapper<RegistrationUserDto, User> {

}
