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

    @NotBlank(message = "Firstname is required")
    private String firstName;

    private String lastName;

    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "ConfirmPassword is required")
    private String confirmPassword;
    @NotBlank(message = "phone is required")
    private String phone;

    @NotNull
    private Role role;
}
