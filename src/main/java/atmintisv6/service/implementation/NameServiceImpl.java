/**
 * Implementation of the NameService interface providing functionality
 * for managing name entities.
 */
package atmintisv6.service.implementation;

import atmintisv4.dto.Name;
import atmintisv4.repository.NameRepository;
import atmintisv4.service.NameService;
import org.springframework.stereotype.Service;

@Service
public class NameServiceImpl implements NameService {

    /**
     * Repository for handling data access operations for name entities.
     */
    private final NameRepository nameRepository;

    /**
     * Constructs an instance of NameServiceImpl with the specified NameRepository.
     *
     * @param nameRepository The repository for handling data access operations for name entities.
     */
    public NameServiceImpl(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    /**
     * Saves the provided name entity to the repository.
     *
     * @param newName The Name object to be saved.
     */
    public void save(Name newName) {
        nameRepository.save(newName);
    }
}
