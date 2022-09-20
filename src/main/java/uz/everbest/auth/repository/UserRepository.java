package uz.everbest.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.everbest.auth.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, Long id);

}
