package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.everbest.buxorossb.dto.UserSessionDto;
import uz.everbest.buxorossb.entity.UserSession;

@Mapper(componentModel = "spring")
@Component
public interface UserSessionMapper extends EntityMapper<UserSessionDto, UserSession> {
}
