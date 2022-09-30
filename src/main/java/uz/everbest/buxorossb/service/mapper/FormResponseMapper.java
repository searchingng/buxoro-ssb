package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.everbest.buxorossb.dto.response.FormResponseDto;
import uz.everbest.buxorossb.entity.Form;

@Mapper(componentModel = "spring")
public interface FormResponseMapper extends EntityMapper<FormResponseDto, Form>{

    @Override
    @Mapping(source = "docType.name", target = "docTypeName")
    @Mapping(source = "organisation.name", target = "organisationName")
    @Mapping(source = "region.name", target = "regionName")
    @Mapping(source = "doctor.user.name", target = "doctorName")
    FormResponseDto toDto(Form entity);
}
