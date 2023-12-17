package atmintisv6.controller.security;

import atmintisv4.service.api.google.GglPeopleApiImplSTATIC;
import atmintisv4.service.api.google.LoadGglToSQL;
import com.google.api.services.people.v1.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * Controller class for handling OAuth security-related requests.
 */
@Controller
@RequestMapping("/login")
public class OauthSecurityController {

    private final LoadGglToSQL loadGglToSQL;

    /**
     * Constructor for OauthSecurityController.
     *
     * @param loadGglToSQL The service for loading Google connections to SQL.
     */
    public OauthSecurityController(LoadGglToSQL loadGglToSQL) {
        this.loadGglToSQL = loadGglToSQL;
    }

    /**
     * Handles the OAuth authentication request.
     *
     * @param user The authenticated user principal.
     */
    @GetMapping("/auth")
    public void handleAuthRequest(Principal user) {
        try {
            // Fetch Google People API connections
            List<Person> listGglConnections = GglPeopleApiImplSTATIC.fetchGooglePeopleData().getConnections();

            // Load Google connections to SQL
            loadGglToSQL.loadConnToSQL(listGglConnections);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
