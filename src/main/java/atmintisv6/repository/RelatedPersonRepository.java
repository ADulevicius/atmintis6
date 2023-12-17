package atmintisv6.repository;

import atmintisv4.dto.RelatedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on the RelatedPerson entity.
 */
@Repository
public interface RelatedPersonRepository extends JpaRepository<RelatedPerson, Long> {
}
