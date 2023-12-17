package atmintisv6.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a related person associated with a contact.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Related_Persons")
public class RelatedPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Related_Person_Id")
    private long relatedPersonId;

    @Column(name = "RelatedPerson")
    private String relatedPerson;

    @Column(name = "Label")
    private String label;

    @Column(name = "Person_Id")
    private long personId;

    // Galima pakeisti i Many-to-Many
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="contact", nullable=false)
    private Contact contact;
}
