package atmintisv6.repository;

import atmintisv6.dto.SignificantDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on the SignificantDate entity.
 */
@Repository
public interface SignificantDateRepository extends JpaRepository<SignificantDate, Long> {
}
