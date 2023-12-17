package atmintisv6.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing an email associated with a contact.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Email_Id")
    private long emailId;

    @Column(name = "Email")
    private String email;

    @Column(name = "Label")
    private String label;

    @Column(name = "Person_Id")
    private long personId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="contact", nullable=false)
    private Contact contact;

}
