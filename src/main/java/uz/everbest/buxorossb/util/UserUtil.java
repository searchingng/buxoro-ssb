package uz.everbest.buxorossb.util;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.entity.User;

public class UserUtil {

    public static User currentUser(){
        try {
            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            return (User) authentication.getPrincipal();
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized user");
        }
    }

}
