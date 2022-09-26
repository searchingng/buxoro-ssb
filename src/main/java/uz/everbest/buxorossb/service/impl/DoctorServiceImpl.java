package uz.everbest.buxorossb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.dto.DoctorDto;
import uz.everbest.buxorossb.dto.UserDto;
import uz.everbest.buxorossb.dto.request.CreationUserDto;
import uz.everbest.buxorossb.dto.request.DoctorRequest;
import uz.everbest.buxorossb.entity.Doctor;
import uz.everbest.buxorossb.entity.User;
import uz.everbest.buxorossb.entity.enums.DoctorStatus;
import uz.everbest.buxorossb.entity.enums.Role;
import uz.everbest.buxorossb.repository.DoctorRepository;
import uz.everbest.buxorossb.repository.UserRepository;
import uz.everbest.buxorossb.service.DoctorService;
import uz.everbest.buxorossb.service.UserService;
import uz.everbest.buxorossb.service.mapper.DoctorMapper;
import uz.everbest.buxorossb.service.mapper.DoctorRequestDoctorMapper;
import uz.everbest.buxorossb.service.mapper.DoctorRequestMapper;
import uz.everbest.buxorossb.util.UserUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final DoctorRequestMapper doctorRequestMapper;
    private final DoctorRequestDoctorMapper doctorRequestDoctorMapper;
    private final DoctorMapper doctorMapper;

    @Override
    public DoctorDto save(DoctorRequest request) {
        CreationUserDto creationUser = doctorRequestMapper.toEntity(request);
        if (request.getId() != null && doctorRepository.existsById(request.getId())){
            Doctor old = findById(request.getId());
            creationUser.setId(old.getUserId());
        } else {
            creationUser.setId(null);
        }

        UserDto user = userService.save(creationUser);

        Doctor doctor = doctorRequestDoctorMapper.toEntity(request);
        doctor.setStatus(DoctorStatus.ACTIVE);
        doctor.setUserId(user.getId());
        doctorRepository.save(doctor);
        return getById(doctor.getId());
    }

    @Override
    public Page<DoctorDto> findAll(Pageable pageable) {
        return doctorRepository.findAllByStatus(DoctorStatus.ACTIVE, pageable)
                .map(doctorMapper::toDto);
    }

    @Override
    public Doctor findById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor not found"));
    }

    @Override
    public DoctorDto getById(Long id) {
        return doctorMapper.toDto(findById(id));
    }

    @Override
    public void deleteById(Long doctorId) {
        Doctor doctor = findById(doctorId);

        User user = doctor.getUser();
        user.setRole(Role.USER);
        userRepository.save(user);

        doctor.setStatus(DoctorStatus.DELETED);
        doctorRepository.save(doctor);
    }

    public Doctor findByUserId(Long userId) {
        return doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor not found"));
    }

    public List<DoctorDto> findByRegionId(Long id) {
        return doctorRepository.findByRegionId(id)
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DoctorDto> findByOrganisationId(Long id) {
        return doctorRepository.findByOrganisationId(id)
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    public Doctor findCurrent() {
        User user = UserUtil.currentUser();
        return findByUserId(user.getId());
    }

    public void deleteByUserId(Long userId) {
        Doctor doctor = findByUserId(userId);

        User user = doctor.getUser();
        user.setRole(Role.USER);
        userRepository.save(user);

        doctor.setStatus(DoctorStatus.DELETED);
        doctorRepository.save(doctor);
    }
}
