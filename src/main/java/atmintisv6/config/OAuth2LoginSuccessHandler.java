package atmintisv6.config;

import atmintisv6.dto.authorization.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom success handler for handling successful OAuth2 login.
 */
@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(OAuth2LoginSuccessHandler.class);

    private final UserServiceImpl userServiceImpl;

    /**
     * Constructs an instance of OAuth2LoginSuccessHandler.
     *
     * @param userServiceImpl The UserServiceImpl used to process user details.
     */
    public OAuth2LoginSuccessHandler(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        Object principal = authentication.getPrincipal();
        if (principal instanceof OAuth2User oauthUser) {
            // Extract necessary information from OAuth2User
            String email = oauthUser.getAttribute("email");
            userServiceImpl.processOAuthPostLogin(email);
        }

        // Set the default target URL for successful authentication
        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl("http://localhost:3000");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
