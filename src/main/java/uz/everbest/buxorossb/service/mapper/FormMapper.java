package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.everbest.buxorossb.dto.FormDto;
import uz.everbest.buxorossb.entity.Form;

@Mapper(componentModel = "spring")
public interface FormMapper extends EntityMapper<FormDto, Form> {

    @Override
    @Mapping(source = "docTypeId", target = "docType.id")
    @Mapping(source = "organisationId", target = "organisation.id")
    @Mapping(source = "regionId", target = "region.id")
    @Mapping(source = "doctorId", target = "doctor.id")
    Form toEntity(FormDto dto);

    @Override
    @Mapping(source = "docType.id", target = "docTypeId")
    @Mapping(source = "organisation.id", target = "organisationId")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "doctor.id", target = "doctorId")
    FormDto toDto(Form entity);
}
