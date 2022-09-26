package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import uz.everbest.buxorossb.dto.DoctorDto;
import uz.everbest.buxorossb.entity.Doctor;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface DoctorMapper extends EntityMapper<DoctorDto, Doctor>{

}
