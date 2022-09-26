package uz.everbest.buxorossb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.everbest.buxorossb.dto.request.CreationUserDto;
import uz.everbest.buxorossb.entity.enums.Role;
import uz.everbest.buxorossb.repository.UserRepository;
import uz.everbest.buxorossb.service.UserService;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepository userRepository;
    private final UserService userService;

    @Bean
    public void createAdmin(){

        if (userRepository.existsByRole(Role.ADMIN))
            return;

        CreationUserDto admin = new CreationUserDto();
        admin.setName("Admin");
        admin.setUsername("admin");
        admin.setPassword("pass");
        admin.setConfirmPassword("pass");
        admin.setRole(Role.ADMIN);

        userService.save(admin);

    }

}
