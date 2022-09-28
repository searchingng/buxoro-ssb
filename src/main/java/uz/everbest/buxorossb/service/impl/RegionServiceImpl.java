package uz.everbest.buxorossb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.entity.DocType;
import uz.everbest.buxorossb.entity.Region;
import uz.everbest.buxorossb.entity.enums.Organisation;
import uz.everbest.buxorossb.repository.DocTypeRepository;
import uz.everbest.buxorossb.repository.OrganisationRepository;
import uz.everbest.buxorossb.repository.RegionRepository;
import uz.everbest.buxorossb.service.RegionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final OrganisationRepository organisationRepository;
    private final DocTypeRepository docTypeRepository;

    @Override
    public Region createRegion(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public Organisation createOrganisation(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    @Override
    public DocType createDocType(DocType docType) {
        return docTypeRepository.save(docType);
    }

    @Override
    public List<Region> findAllRegions() {
        return regionRepository.findAllByOrderByName();
    }

    @Override
    public List<Organisation> findAllOrganisations() {
        return organisationRepository.findAllByOrderByName();
    }

    @Override
    public List<DocType> findAllDocTypes() {
        return docTypeRepository.findAll();
    }

    @Override
    public Region findRegionById(Long regionId) {
        return regionRepository.findById(regionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Region not found"));
    }

    @Override
    public Organisation findOrganisationById(Long organisationId) {
        return organisationRepository.findById(organisationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organisation not found"));
    }

    @Override
    public DocType findDocTypeById(Long docTypeId) {
        return docTypeRepository.findById(docTypeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "DocType Not found"));
    }
}
