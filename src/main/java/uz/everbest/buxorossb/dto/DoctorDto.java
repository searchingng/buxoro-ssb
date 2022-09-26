package uz.everbest.buxorossb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.everbest.buxorossb.entity.Region;
import uz.everbest.buxorossb.entity.enums.Organisation;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorDto {

    private Long id;

    private String position;

    private UserDto user;

    private Organisation organisation;

    private Region region;

}
