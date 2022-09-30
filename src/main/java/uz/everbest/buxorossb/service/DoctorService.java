package uz.everbest.buxorossb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.everbest.buxorossb.dto.DoctorDto;
import uz.everbest.buxorossb.dto.request.DoctorRequest;
import uz.everbest.buxorossb.entity.Doctor;

public interface DoctorService {

    DoctorRequest save(DoctorRequest request);

    Page<DoctorDto> findAll(Pageable pageable);

    Doctor findById(Long doctorId);

    Doctor findCurrent();

    DoctorDto getById(Long id);

    void deleteById(Long doctorId);

}
