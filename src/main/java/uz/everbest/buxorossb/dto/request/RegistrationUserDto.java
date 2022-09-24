package uz.everbest.buxorossb.dto.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationUserDto {

    @NotBlank(message = "Firstname is required")
    private String firstName;

    private String lastName;

    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;

    @JsonSetter
    @NotBlank(message = "ConfirmPassword is required")
    private String confirmPassword;
    @NotBlank(message = "phone is required")
    private String phone;

}
