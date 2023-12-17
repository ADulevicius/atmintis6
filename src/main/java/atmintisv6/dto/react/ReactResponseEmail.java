package atmintisv6.dto.react;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entity class representing an email in the React response.
 */
@Data
@Entity
public class ReactResponseEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reactResponseEmailId;

    private String email;

    private Long emailId;
}
