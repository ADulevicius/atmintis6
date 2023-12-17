package atmintisv6.repository;

import atmintisv4.dto.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on the Email entity.
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
}
