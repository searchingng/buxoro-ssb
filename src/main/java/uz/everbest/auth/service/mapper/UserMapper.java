package uz.everbest.auth.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.everbest.auth.dto.UserDto;
import uz.everbest.auth.entity.User;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper extends EntityMapper<UserDto, User> {

}
