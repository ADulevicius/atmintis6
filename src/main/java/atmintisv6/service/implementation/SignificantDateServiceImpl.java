/**
 * Implementation of the SignificantDateService interface providing functionality
 * for managing significant date entities.
 */
package atmintisv6.service.implementation;

import atmintisv6.dto.SignificantDate;
import atmintisv6.repository.SignificantDateRepository;
import atmintisv6.service.SignificantDateService;
import org.springframework.stereotype.Service;

@Service
public class SignificantDateServiceImpl implements SignificantDateService {

    /**
     * Repository for handling data access operations for significant date entities.
     */
    private final SignificantDateRepository significantDateRepository;

    /**
     * Constructs an instance of SignificantDateServiceImpl with the specified SignificantDateRepository.
     *
     * @param significantDateRepository The repository for handling data access operations for significant date entities.
     */
    public SignificantDateServiceImpl(SignificantDateRepository significantDateRepository) {
        this.significantDateRepository = significantDateRepository;
    }

    /**
     * Saves the provided significant date entity to the repository.
     *
     * @param newSignificantDate The SignificantDate object to be saved.
     */
    public void save(SignificantDate newSignificantDate) {
        significantDateRepository.save(newSignificantDate);
    }
}
