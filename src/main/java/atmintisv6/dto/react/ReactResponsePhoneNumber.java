package atmintisv6.dto.react;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entity class representing a phone number in the React response.
 */
@Data
@Entity
public class ReactResponsePhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reactResponsePhoneNumberId;

    private String phoneNumber;

    private Long phoneNumberId;
}
