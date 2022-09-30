package uz.everbest.buxorossb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormResponseDto {

    private Long id;

    private String name;

    private String passport;

    private LocalDate startedDate;

    private LocalDate expiredDate;

    private String docTypeName;

    private String organisationName;

    private String regionName;

    private String doctorName;

    private String code;

}
