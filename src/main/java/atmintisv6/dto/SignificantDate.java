package atmintisv6.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a significant date associated with a contact.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Significant_Dates")
public class SignificantDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Significant_Date_Id")
    private long significantDateId;

    @Column(name = "Significant_Day")
    private Integer significantDay;

    @Column(name = "Significant_Month")
    private Integer significantMonth;

    @Column(name = "Significant_Year")
    private Integer significantYear;

    @Column(name = "Label")
    private String label;

    @Column(name = "Person_Id")
    private long personId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="contact", nullable=false)
    private Contact contact;
}
