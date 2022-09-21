package uz.everbest.buxorossb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.everbest.buxorossb.dto.UserSessionDto;
import uz.everbest.buxorossb.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserSessionService {

    Page<UserSessionDto> findAll(Pageable pageable, User user);

    void save(HttpServletRequest request, User user, String token);

//    AdminSessionDto findOne(Long id);


}
