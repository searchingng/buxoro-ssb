package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.everbest.buxorossb.dto.request.CreationUserDto;
import uz.everbest.buxorossb.dto.request.DoctorRequest;
import uz.everbest.buxorossb.entity.enums.Role;

@Mapper(componentModel = "spring")
public interface DoctorRequestMapper extends EntityMapper<DoctorRequest, CreationUserDto>{

    @Override
    @Mapping(source = "password", target = "confirmPassword")
    @Mapping(target = "role", expression = "java(getRole())")
    CreationUserDto toEntity(DoctorRequest dto);

    default Role getRole(){
        return Role.EMPLOYEE;
    }
}