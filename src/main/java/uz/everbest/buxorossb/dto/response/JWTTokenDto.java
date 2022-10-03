package uz.everbest.buxorossb.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.everbest.buxorossb.dto.UserDto;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JWTTokenDto {
    private String token;
    private boolean success;
    private UserDto user;

    public JWTTokenDto(String token) {
        this.token = token;
    }

    @JsonProperty("id_token")
    public String getToken() {
        return token;
    }

    public void setIdToken(String token) {
        this.token = token;
    }

}
