/**
 * Implementation of the RelatedPersonService interface providing functionality
 * for managing related person entities.
 */
package atmintisv6.service.implementation;

import atmintisv4.dto.RelatedPerson;
import atmintisv4.repository.RelatedPersonRepository;
import atmintisv4.service.RelatedPersonService;
import org.springframework.stereotype.Service;

@Service
public class RelatedPersonServiceImpl implements RelatedPersonService {

    /**
     * Repository for handling data access operations for related person entities.
     */
    private final RelatedPersonRepository relatedPersonRepository;

    /**
     * Constructs an instance of RelatedPersonServiceImpl with the specified RelatedPersonRepository.
     *
     * @param relatedPersonRepository The repository for handling data access operations for related person entities.
     */
    public RelatedPersonServiceImpl(RelatedPersonRepository relatedPersonRepository) {
        this.relatedPersonRepository = relatedPersonRepository;
    }

    /**
     * Saves the provided related person entity to the repository.
     *
     * @param newRelatedPerson The RelatedPerson object to be saved.
     */
    public void save(RelatedPerson newRelatedPerson) {
        relatedPersonRepository.save(newRelatedPerson);
    }
}
