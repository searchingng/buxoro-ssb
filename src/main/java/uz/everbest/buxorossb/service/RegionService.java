package uz.everbest.buxorossb.service;

import uz.everbest.buxorossb.entity.DocType;
import uz.everbest.buxorossb.entity.Organisation;
import uz.everbest.buxorossb.entity.Region;

import java.util.List;

public interface RegionService {

    Region createRegion(Region region);

    Organisation createOrganisation(Organisation organisation);

    DocType createDocType(DocType docType);

    List<Region> findAllRegions();

    List<Organisation> findAllOrganisations();

    List<DocType> findAllDocTypes();

    Region findRegionById(Long regionId);

    Organisation findOrganisationById(Long organisationId);

    DocType findDocTypeById(Long docTypeId);

}
