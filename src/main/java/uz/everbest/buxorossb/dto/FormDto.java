package uz.everbest.buxorossb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FormDto {

    private Long id;

    private String name;

    private String passport;

    private LocalDate startedDate;

    private LocalDate expiredDate;

    private Long docTypeId;

    private Long organisationId;

    private Long regionId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long doctorId;


}
