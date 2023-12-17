package atmintisv6.controller.endpoints;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getLoggedInUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ( (OAuth2User) authentication.getPrincipal() )
                        .getAttribute("email");
        return ResponseEntity.ok().body(email);
    }
}
