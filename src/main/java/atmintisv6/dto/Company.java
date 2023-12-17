package atmintisv6.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a company.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Company_Id")
    private long companyId;

    @Column(name = "Company_Name")
    private String companyName;

    @Column(name = "Job_title")
    private String jobTitle;

    @Column(name = "Department")
    private String department;

    @Column(name = "Person_Id")
    private long personId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "contact", nullable = false)
    private Contact contact;
}
