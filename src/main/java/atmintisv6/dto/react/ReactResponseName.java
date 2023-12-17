package atmintisv6.dto.react;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entity class representing a name in the React response.
 */
@Data
@Entity
public class ReactResponseName {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reactResponseNameId;

    private String firstName;

    private String surname;
}
