package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.everbest.buxorossb.dto.request.DoctorRequest;
import uz.everbest.buxorossb.entity.Doctor;

@Mapper(componentModel = "spring")
public interface DoctorRequestDoctorMapper extends EntityMapper<DoctorRequest, Doctor>{

    @Override
    @Mapping(source = "regionId", target = "region.id")
    @Mapping(source = "organisationId", target = "organisation.id")
    Doctor toEntity(DoctorRequest dto);
}
