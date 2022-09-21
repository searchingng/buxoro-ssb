package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.everbest.buxorossb.dto.user.UserDto;
import uz.everbest.buxorossb.entity.User;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper extends EntityMapper<UserDto, User> {

}
