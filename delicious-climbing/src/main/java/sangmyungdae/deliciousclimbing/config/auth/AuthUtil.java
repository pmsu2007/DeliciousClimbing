package sangmyungdae.deliciousclimbing.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUtil {

    public static Authentication getCurrentAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        return context.getAuthentication();
    }

    public static Object getCurrentDetails() {
        Authentication auth = getCurrentAuthentication();
        if (auth == null) {
            return null;
        }
        return auth.getPrincipal();
    }

    public static String getAuthUser() {
        Object details = getCurrentDetails();
        if (details instanceof UserDetails) {
            return ((UserDetails) details).getUsername();
        }
        return details != null ? details.toString() : null;
    }
}