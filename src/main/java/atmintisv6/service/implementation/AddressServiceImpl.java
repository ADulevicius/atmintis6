/**
 * Implementation of the AddressService interface providing functionality
 * related to managing addresses.
 */
package atmintisv6.service.implementation;

import atmintisv6.dto.Address;
import atmintisv6.repository.AddressRepository;
import atmintisv6.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    /**
     * Address repository to handle data access operations for addresses.
     */
    private final AddressRepository addressRepository;

    /**
     * Constructs an instance of AddressServiceImpl with the specified AddressRepository.
     *
     * @param addressRepository The repository for handling data access operations for addresses.
     */
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Retrieves a list of all addresses stored in the repository.
     *
     * @return List of Address objects representing all addresses.
     */
    public List<Address> listAllAddresses() {
        return addressRepository.findAll();
    }

    /**
     * Saves the provided address to the repository.
     *
     * @param address The Address object to be saved.
     */
    public void save(Address address) {
        addressRepository.save(address);
    }
}
