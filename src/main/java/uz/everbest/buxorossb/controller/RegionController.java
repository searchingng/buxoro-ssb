package uz.everbest.buxorossb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.everbest.buxorossb.entity.DocType;
import uz.everbest.buxorossb.entity.Region;
import uz.everbest.buxorossb.entity.enums.Organisation;
import uz.everbest.buxorossb.service.RegionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegionController {

    private final RegionService regionService;

    @PostMapping("/regions")
    public ResponseEntity<Region> createRegion(@RequestBody Region region){
        return ResponseEntity.ok(regionService.createRegion(region));
    }

    @PostMapping("/organizations")
    public ResponseEntity<Organisation> createOrganisation(@RequestBody Organisation organisation){
        return ResponseEntity.ok(regionService.createOrganisation(organisation));
    }

    @PostMapping("/doc-types")
    public ResponseEntity<DocType> createDocType(@RequestBody DocType docType){
        return ResponseEntity.ok(regionService.createDocType(docType));
    }

    @GetMapping("/regions")
    public ResponseEntity<List<Region>> getRegions(){
        return ResponseEntity.ok(regionService.findAllRegions());
    }

    @GetMapping("/organizations")
    public ResponseEntity<List<Organisation>> getOrganisations(){
        return ResponseEntity.ok(regionService.findAllOrganisations());
    }

    @GetMapping("/doc-types")
    public ResponseEntity<List<DocType>> getDocTypes(){
        return ResponseEntity.ok(regionService.findAllDocTypes());
    }

}
