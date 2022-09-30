package uz.everbest.buxorossb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.everbest.buxorossb.dto.FormDto;
import uz.everbest.buxorossb.dto.response.FormResponseDto;
import uz.everbest.buxorossb.service.FormService;
import uz.everbest.buxorossb.service.QRCodeService;

@RestController
@RequestMapping("/api/forms")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;
    private final QRCodeService qrCodeService;

    @PostMapping
    public ResponseEntity<FormDto> createForm(@RequestBody FormDto formDto){
        return ResponseEntity.ok(formService.save(formDto));
    }

    @GetMapping
    public ResponseEntity<Page<FormResponseDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(formService.findAll(pageable));
    }

    @GetMapping("/my-forms")
    public ResponseEntity<Page<FormResponseDto>> getMyForms(Pageable pageable){
        return ResponseEntity.ok(formService.findMyForms(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormDto> getMyForms(@PathVariable Long id){
        return ResponseEntity.ok(formService.getOne(id));
    }

    @GetMapping("/by-code")
    public ResponseEntity<FormResponseDto> getMyForms(@RequestParam("code") String code){
        return ResponseEntity.ok(formService.findByCode(code));
    }

    @PostMapping("/qr-code")
    public ResponseEntity<String> generateQRCode(@RequestParam("code") String code){
        return ResponseEntity.ok(qrCodeService.generateQRCode(code));
    }

}
