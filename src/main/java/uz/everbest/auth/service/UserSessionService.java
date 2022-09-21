package uz.everbest.auth.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.everbest.auth.dto.UserSessionDto;
import uz.everbest.auth.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserSessionService {

    Page<UserSessionDto> findAll(Pageable pageable, User user);

    void save(HttpServletRequest request, User user, String token);

//    AdminSessionDto findOne(Long id);


}
