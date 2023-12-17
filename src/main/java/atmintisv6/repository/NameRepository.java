package atmintisv6.repository;

import atmintisv4.dto.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on the Name entity.
 */
@Repository
public interface NameRepository extends JpaRepository<Name, Long> {
}
