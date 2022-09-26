package uz.everbest.buxorossb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.everbest.buxorossb.entity.enums.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreationUserDto {

    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "ConfirmPassword is required")
    private String confirmPassword;

    @NotNull
    private Role role;
}
