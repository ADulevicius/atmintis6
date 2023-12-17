/**
 * Implementation of the CustomFieldService interface providing functionality
 * for managing custom fields.
 */
package atmintisv6.service.implementation;

import atmintisv6.dto.CustomField;
import atmintisv6.repository.CustomFieldRepository;
import atmintisv6.service.CustomFieldService;
import org.springframework.stereotype.Service;

@Service
public class CustomFieldServiceImpl implements CustomFieldService {

    /**
     * Repository for handling data access operations for custom fields.
     */
    private final CustomFieldRepository customFieldRepository;

    /**
     * Constructs an instance of CustomFieldServiceImpl with the specified CustomFieldRepository.
     *
     * @param customFieldRepository The repository for handling data access operations for custom fields.
     */
    public CustomFieldServiceImpl(CustomFieldRepository customFieldRepository) {
        this.customFieldRepository = customFieldRepository;
    }

    /**
     * Saves the provided custom field to the repository.
     *
     * @param newCustomField The CustomField object to be saved.
     */
    public void save(CustomField newCustomField) {
        customFieldRepository.save(newCustomField);
    }
}
