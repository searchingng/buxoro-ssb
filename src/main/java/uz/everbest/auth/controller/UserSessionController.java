package uz.everbest.auth.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.everbest.auth.dto.UserSessionDto;
import uz.everbest.auth.entity.User;
import uz.everbest.auth.security.CurrentUser;
import uz.everbest.auth.service.UserSessionService;

@RestController
@RequestMapping("/api/userSessions")
public class UserSessionController {

    private final UserSessionService userSessionService;

    public UserSessionController(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserSessionDto>> getAllUserSession(Pageable pageable, @CurrentUser User user) {
        Page<UserSessionDto> result = userSessionService.findAll(pageable, user);
        return ResponseEntity.ok(result);
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<AdminSessionDto> getAllAdminSession(@PathVariable Long id){
//        AdminSessionDto adminSessionDtos = adminSessionService.findOne(id);
//        return ResponseEntity.ok(adminSessionDtos);
//    }
}
