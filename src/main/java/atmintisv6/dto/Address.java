package atmintisv6.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing an address.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Address_Id")
    private long addressId;

    @Column(name = "Country")
    private String country;

    @Column(name = "Street_Address")
    private String streetAddress;

    @Column(name = "Street_Address_2")
    private String streetAddress2;

    @Column(name = "Post_Town")
    private String postTown;

    @Column(name = "Postcode")
    private String postcode;

    @Column(name = "PO_Box")
    private String poBox;

    @Column(name = "Label")
    private String label;

    @Column(name = "Person_id")
    private Long personId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "contact", nullable = false)
    private Contact contact;

    /**
     * Generates the hash code for this address based on the addressId.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (int) (addressId ^ (addressId >>> 32));
        return result;
    }

    /**
     * Checks if this address is equal to another object based on the addressId.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Address other = (Address) obj;
        return addressId == other.addressId;
    }
}
