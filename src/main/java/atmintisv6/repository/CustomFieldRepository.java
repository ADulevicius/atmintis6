package atmintisv6.repository;

import atmintisv6.dto.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on the CustomField entity.
 */
@Repository
public interface CustomFieldRepository extends JpaRepository<CustomField, Long> {
}
