package atmintisv6.repository;

import atmintisv6.dto.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on the Company entity.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
