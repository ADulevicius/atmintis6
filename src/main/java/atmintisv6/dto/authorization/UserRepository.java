package atmintisv6.dto.authorization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user.
     * @return The user with the specified email address, or null if not found.
     */
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmail(@Param("email") String email);

    /**
     * Retrieves an optional user by their email address.
     *
     * @param userEmail The email address of the user.
     * @return An optional containing the user with the specified email address, or an empty optional if not found.
     */
    Optional<User> findByEmail(String userEmail);
}
