package atmintisv6.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a custom field associated with a contact.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "CustomField")
public class CustomField {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Custom_Field_Id")
    private long customFieldId;

    @Column(name = "Custom_Field")
    private String customField;

    @Column(name = "Label")
    private String label;

    @Column(name = "Person_Id")
    private long personId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "contact", nullable=false)
    private Contact contact;

}
