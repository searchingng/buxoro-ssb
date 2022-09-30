package uz.everbest.buxorossb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.dto.FormDto;
import uz.everbest.buxorossb.dto.response.FormResponseDto;
import uz.everbest.buxorossb.entity.Doctor;
import uz.everbest.buxorossb.entity.Form;
import uz.everbest.buxorossb.repository.FormRepository;
import uz.everbest.buxorossb.service.DoctorService;
import uz.everbest.buxorossb.service.FormService;
import uz.everbest.buxorossb.service.mapper.FormMapper;
import uz.everbest.buxorossb.service.mapper.FormResponseMapper;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;
    private final FormResponseMapper formResponseMapper;
    private final DoctorService doctorService;

    @Override
    public FormDto save(FormDto dto) {
        Doctor doctor = doctorService.findCurrent();

        if (dto.getId() != null && !formRepository.existsByIdAndDoctorId(dto.getId(), doctor.getId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This form does't belong to you");

        Form form = formMapper.toEntity(dto);
        form.setDoctor(doctor);

        String code = generateCode();
        while (formRepository.existsByCode(code)){
            code = generateCode();
        }

        form.setCode(code);
        formRepository.save(form);
        return formMapper.toDto(form);
    }

    @Override
    public FormResponseDto findByCode(String code) {
        Form form = formRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Form not found"));
        return formResponseMapper.toDto(form);
    }

    @Override
    public Form findById(Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Form not found"));
    }

    @Override
    public FormDto getOne(Long id) {
        return formMapper
                .toDto(findById((id)));
    }

    @Override
    public Page<FormResponseDto> findAll(Pageable pageable) {
        return formRepository.findAll(pageable)
                .map(formResponseMapper::toDto);
    }

    @Override
    public Page<FormResponseDto> findFormsByDoctorId(Long doctorId, Pageable pageable) {
        return formRepository.findByDoctorId(doctorId, pageable)
                .map(formResponseMapper::toDto);
    }

    @Override
    public Page<FormResponseDto> findMyForms(Pageable pageable) {
        return findFormsByDoctorId(doctorService.findCurrent().getId(), pageable);
    }

    public String generateCode(){
        long millis = System.currentTimeMillis();
        millis %= 1_000_000_000L;
        return String.valueOf(millis);
    }
}
