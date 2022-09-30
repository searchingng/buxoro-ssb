package uz.everbest.buxorossb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.everbest.buxorossb.entity.Organisation;

import java.util.List;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    List<Organisation> findAllByOrderByName();

}
