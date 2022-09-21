package uz.everbest.auth.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.everbest.auth.dto.UserSessionDto;
import uz.everbest.auth.entity.UserSession;

@Mapper(componentModel = "spring")
@Component
public interface UserSessionMapper extends EntityMapper<UserSessionDto, UserSession> {
}
