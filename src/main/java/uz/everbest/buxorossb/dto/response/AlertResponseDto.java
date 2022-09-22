package uz.everbest.buxorossb.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertResponseDto {
    private String message;
    private boolean success;

    public AlertResponseDto(boolean success) {
        this.success = success;
    }
}
