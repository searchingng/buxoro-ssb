package uz.everbest.buxorossb.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.everbest.buxorossb.entity.User;
import uz.everbest.buxorossb.errors.UserNotFoundException;
import uz.everbest.buxorossb.repository.UserRepository;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        return userRepository.findByUsername(login)
                .map(user -> createSpringSecurityUser(login, user))
                .orElseThrow(() -> new UserNotFoundException("User with username " + login + " was not found in the database"));

    }

    public User loadUsersId(Long id) {
        return userRepository.findById(id)
                .map(user -> createSpringSecurityUser(String.valueOf(id), user))
                .orElseThrow(() -> new UsernameNotFoundException("User with userId " + id + " was not found in the database"));
    }

    private User createSpringSecurityUser(String login, User user) {
        if (!user.isEnabled()) {
            throw new UserNotFoundException("User " + login + " was not activated");
        }

        return user;
    }
}
