package uz.everbest.buxorossb.service;

import uz.everbest.buxorossb.entity.Region;
import uz.everbest.buxorossb.entity.enums.Organisation;

import java.util.List;

public interface RegionService {

    Region createRegion(Region region);

    Organisation createOrganisation(Organisation organisation);

    List<Region> findAllRegions();

    List<Organisation> findAllOrganisations();

    Region findRegionById(Long regionId);

    Organisation findOrganisationById(Long organisationId);

}
