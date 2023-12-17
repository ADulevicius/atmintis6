package atmintisv6.dto.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for handling user-related operations after OAuth login.
 */
@Service
public class UserServiceImpl {

    private final UserRepository repo;

    /**
     * Constructs a new instance of UserServiceImpl.
     *
     * @param repo The UserRepository used for interacting with user data.
     */
    @Autowired
    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    /**
     * Processes user information after a successful OAuth login.
     *
     * @param email The email address obtained from OAuth login.
     */
    public void processOAuthPostLogin(String email) {
        User existUser = repo.getUserByEmail(email);

        if (existUser == null) {
            // Create a new user if not already in the database
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setProvider(Provider.GOOGLE);
            newUser.setVerified(true);

            repo.save(newUser);
        }
    }
}
