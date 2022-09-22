package uz.everbest.buxorossb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mamadaliyev Nodirbek
 * @created 19/06/2021 - 12:52
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSessionDto {
    private Long id;

    private UserDto user;

    private String token;

    private String deviceIp;

    private String deviceModel;

    private String deviceOsVersion;
}
