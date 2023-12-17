package atmintisv6.dto.react;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing data received from React for updating contact information.
 */
@Entity
@Data
public class UpdateFromReact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long updateFromReactId;

    private long contactId;

    private long personId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ReactResponseName name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ReactResponsePhoneNumber phoneNumberList;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ReactResponseCompany companyList;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ReactResponseEmail emailList;
}
