/**
 * Implementation of the EmailService interface providing functionality
 * for managing email entities.
 */
package atmintisv6.service.implementation;

import atmintisv4.dto.Email;
import atmintisv4.repository.EmailRepository;
import atmintisv4.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    /**
     * Repository for handling data access operations for email entities.
     */
    private final EmailRepository emailRepository;

    /**
     * Constructs an instance of EmailServiceImpl with the specified EmailRepository.
     *
     * @param emailRepository The repository for handling data access operations for email entities.
     */
    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    /**
     * Saves the provided email entity to the repository.
     *
     * @param newEmail The Email object to be saved.
     */
    public void save(Email newEmail) {
        emailRepository.save(newEmail);
    }
}
