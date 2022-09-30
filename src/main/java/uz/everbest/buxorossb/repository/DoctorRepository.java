package uz.everbest.buxorossb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.everbest.buxorossb.entity.Doctor;
import uz.everbest.buxorossb.entity.enums.DoctorStatus;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByStatus(DoctorStatus status, Pageable pageable);

    @Query(value = "SELECT d.* FROM doctor d WHERE d.status = 'ACTIVE' AND d.region_id = ?1", nativeQuery = true)
    List<Doctor> findByRegionId(Long regionId);

    @Query(value = "SELECT d.* FROM doctor d WHERE d.status = 'ACTIVE' AND d.organisation_id = ?1", nativeQuery = true)
    List<Doctor> findByOrganisationId(Long organisationId);

    @Query(value = "SELECT d.* FROM doctor d WHERE d.status = 'ACTIVE' AND d.user_id = ?1", nativeQuery = true)
    Optional<Doctor> findByUserId(Long userId);

}
