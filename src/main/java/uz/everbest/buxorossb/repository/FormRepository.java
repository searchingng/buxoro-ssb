package uz.everbest.buxorossb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.everbest.buxorossb.entity.Form;

import java.util.Optional;

public interface FormRepository extends JpaRepository<Form, Long> {

    Optional<Form> findByCode(String code);

    boolean existsByCode(String code);

    Page<Form> findByDoctorId(Long doctorId, Pageable pageable);

    boolean existsByIdAndDoctorId(Long id, Long doctorId);

}
