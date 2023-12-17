package atmintisv6.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a name associated with a contact.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Name")
public class Name {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Name_Id")
    private long nameId;

    @Column(name = "Prefix")
    private String prefix;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "Middle_Name")
    private String middleName;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Suffix")
    private String suffix;

    @Column(name = "Phonetic_First")
    private String phoneticFirstName;

    @Column(name = "Phonetic_Middle_Name")
    private String phoneticMiddleName;

    @Column(name = "Phonetic_Surname")
    private String phoneticSurname;

    @Column(name = "Nickname")
    private String nickname;

    @Column(name = "File_as")
    private String fileAs;

    @Column(name = "Person_Id")
    private long personId;

    @OneToOne(mappedBy = "name")
    @JoinColumn(name = "contact")
    @JsonIgnore
    private Contact contact;

    @Override
    public String toString() {
        return "Name{" +
                "nameId='" + nameId + '\'' +
                ", prefix='" + prefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surname='" + surname + '\'' +
                ", suffix='" + suffix + '\'' +
                ", phoneticFirstName='" + phoneticFirstName + '\'' +
                ", phoneticMiddleName='" + phoneticMiddleName + '\'' +
                ", phoneticSurname='" + phoneticSurname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", fileAs='" + fileAs + '\'' +
                ", personId='" + personId + '\'' +
                '}';
    }
}
