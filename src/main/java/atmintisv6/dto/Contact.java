package atmintisv6.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Entity class representing a contact.
 */
@Entity
@AllArgsConstructor
@Data
@Table(name = "Contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Contact Id")
    public long contactId;

    @Column(name = "Person Id")  // ResourceName in contacts.google.com API
    public long personId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Name name;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Company> companyList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Email> emailList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addressList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumber> phoneNumberList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelatedPerson> relatedPersonList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SignificantDate> significantDateList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomField> customFieldList;

    /**
     * Constructor for creating a contact with a specified resourceName.
     *
     * @param resourceName ResourceName in contacts.google.com API.
     */
    public Contact(String resourceName){
        this.personId = Long.parseLong(resourceName);
        initializeCollections();
    }

    /**
     * Default constructor for creating a contact.
     */
    public Contact(){
        initializeCollections();
    }

    private void initializeCollections() {
        this.addressList = new ArrayList<>();
        this.companyList = new ArrayList<>();
        this.customFieldList = new ArrayList<>();
        this.emailList = new ArrayList<>();
        this.phoneNumberList = new ArrayList<>();
        this.relatedPersonList = new ArrayList<>();
        this.significantDateList = new ArrayList<>();
    }

    /**
     * Calculates the hash code of the contact.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + calculateCollectionHashCode(addressList);
        return result;
    }

    private int calculateCollectionHashCode(Collection<?> collection) {
        int result = 1;
        for (Object element : collection) {
            result = 31 * result + (element == null ? 0 : element.hashCode());
        }
        return result;
    }

    /**
     * Returns a string representation of the contact.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", personId=" + personId +
                ", name=" + name +
                ", companyList size=" + companyList.size() + // Print size instead of calling toString
                ", emailList size=" + emailList.size() + // Print size instead of calling toString
                ", addressList size=" + addressList.size() + // Print size instead of calling toString
                ", phoneNumberList size=" + phoneNumberList.size() + // Print size instead of calling toString
                ", relatedPersonList size=" + relatedPersonList.size() + // Print size instead of calling toString
                ", significantDateList size=" + significantDateList.size() + // Print size instead of calling toString
                ", customFieldList size=" + customFieldList.size() + // Print size instead of calling toString
                '}';
    }
}
