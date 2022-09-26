package uz.everbest.buxorossb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.everbest.buxorossb.entity.Region;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {

    List<Region> findAllByOrderByName();

}
