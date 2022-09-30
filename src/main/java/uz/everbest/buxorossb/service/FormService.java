package uz.everbest.buxorossb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.everbest.buxorossb.dto.FormDto;
import uz.everbest.buxorossb.dto.response.FormResponseDto;
import uz.everbest.buxorossb.entity.Form;

public interface FormService {

    FormDto save(FormDto dto);

    FormResponseDto findByCode(String code);

    Form findById(Long id);

    FormDto getOne(Long id);

    Page<FormResponseDto> findAll(Pageable pageable);

    Page<FormResponseDto> findFormsByDoctorId(Long doctorId, Pageable pageable);

    Page<FormResponseDto> findMyForms(Pageable pageable);

}
