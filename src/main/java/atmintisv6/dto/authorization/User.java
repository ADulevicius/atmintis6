package atmintisv6.dto.authorization;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Entity class representing a user in the application.
 */
@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "User_Id")
    private long userId;

    private String name;
    private String family_name;
    private String email;
    private Boolean verified;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private Collection<? extends GrantedAuthority> authorities;

}
