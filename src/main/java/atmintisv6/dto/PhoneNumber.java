package atmintisv6.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a phone number associated with a contact.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Phone_Numbers")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Phone_Number_Id")
    private long phoneNumberId;

    @Column(name = "Country")
    private String country;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Label")
    private String label;

    @Column(name = "Person_Id")
    private long personId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="contact", nullable=false)
    private Contact contact;
}
