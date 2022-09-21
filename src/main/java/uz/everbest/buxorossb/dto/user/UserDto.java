package uz.everbest.buxorossb.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.everbest.buxorossb.entity.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;

    private String fio;

    private String username;

    private String phone;

    private Role role;

}
