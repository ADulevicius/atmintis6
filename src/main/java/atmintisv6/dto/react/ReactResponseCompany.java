package atmintisv6.dto.react;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entity class representing a company in the React response.
 */
@Data
@Entity
public class ReactResponseCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reactResponseCompanyId;

    private String occupation;

    private Long companyId;
}
