package atmintisv6.repository;

import atmintisv4.dto.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on the PhoneNumber entity.
 */
@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
}
