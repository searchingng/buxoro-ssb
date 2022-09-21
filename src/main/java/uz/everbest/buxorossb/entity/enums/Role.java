package uz.everbest.buxorossb.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, MODERATOR, EMPLOYEE, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
