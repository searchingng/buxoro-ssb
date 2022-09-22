package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.everbest.buxorossb.dto.request.CreationUserDto;
import uz.everbest.buxorossb.entity.User;

@Mapper(componentModel = "spring")
@Component
public interface CreationUserMapper extends EntityMapper<CreationUserDto, User> {

}
