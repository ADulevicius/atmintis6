package atmintisv6.repository;

import atmintisv6.dto.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on the Address entity.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
