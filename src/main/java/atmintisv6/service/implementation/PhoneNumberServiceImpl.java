/**
 * Implementation of the PhoneNumberService interface providing functionality
 * for managing phone number entities.
 */
package atmintisv6.service.implementation;

import atmintisv4.dto.PhoneNumber;
import atmintisv4.repository.PhoneNumberRepository;
import atmintisv4.service.PhoneNumberService;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    /**
     * Repository for handling data access operations for phone number entities.
     */
    private final PhoneNumberRepository phoneNumberRepository;

    /**
     * Constructs an instance of PhoneNumberServiceImpl with the specified PhoneNumberRepository.
     *
     * @param phoneNumberRepository The repository for handling data access operations for phone number entities.
     */
    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    /**
     * Saves the provided phone number entity to the repository.
     *
     * @param newPhoneNumber The PhoneNumber object to be saved.
     */
    public void save(PhoneNumber newPhoneNumber) {
        phoneNumberRepository.save(newPhoneNumber);
    }
}
