package uz.everbest.buxorossb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.everbest.buxorossb.dto.DoctorDto;
import uz.everbest.buxorossb.dto.request.DoctorRequest;
import uz.everbest.buxorossb.dto.response.AlertResponseDto;
import uz.everbest.buxorossb.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorRequest request){
        return ResponseEntity.ok(doctorService.save(request));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(doctorService.findAll(pageable));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<AlertResponseDto> deleteDoctor(@PathVariable("doctorId") Long doctorId){
        doctorService.deleteById(doctorId);
        return ResponseEntity.ok(new AlertResponseDto(true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getById(id));
    }



}
