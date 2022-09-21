package uz.everbest.buxorossb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.everbest.buxorossb.entity.User;
import uz.everbest.buxorossb.entity.enums.Role;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByRole(Role role);

    boolean existsByUsernameAndIdNot(String username, Long id);

}
