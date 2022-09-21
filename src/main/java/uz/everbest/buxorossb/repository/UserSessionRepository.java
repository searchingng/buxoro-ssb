package uz.everbest.buxorossb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.everbest.buxorossb.entity.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    Page<UserSession> findAllByUserId(Long user_id, Pageable pageable);
}
