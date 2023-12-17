package atmintisv6.repository;

import atmintisv6.dto.Company;
import atmintisv6.dto.Contact;
import atmintisv6.dto.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on the Contact entity.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    /**
     * Retrieve all contacts.
     *
     * @return List of contacts.
     */
    List<Contact> findAll();

    /**
     * Find a contact by personId.
     *
     * @param personId The personId to search for.
     * @return Optional<Contact> containing the contact with the specified personId.
     */
    Optional<Contact> findByPersonId(long personId);

    /**
     * Find the first name of a contact by personId.
     *
     * @param personId The personId to search for.
     * @return The first name of the contact with the specified personId.
     */
    @Query("SELECT cn.name.firstName FROM Contact cn WHERE cn.personId = :personId")
    String findFirstNameByPersonId(@Param("personId") long personId);

    /**
     * Find the last name of a contact by personId.
     *
     * @param personId The personId to search for.
     * @return The last name of the contact with the specified personId.
     */
    @Query("SELECT cn.name.surname FROM Contact cn WHERE cn.personId = :personId")
    String findLastNameByPersonId(@Param("personId") long personId);

    /**
     * Find the latest phone number of a contact by personId.
     *
     * @param personId The personId to search for.
     * @return Optional<PhoneNumber> containing the latest phone number of the contact with the specified personId.
     */
    @Query("SELECT pn FROM Contact cn JOIN cn.phoneNumberList pn WHERE cn.personId = :personId ORDER BY pn.phoneNumberId DESC")
    Optional<PhoneNumber> findLatestPhoneNumberByPersonId(@Param("personId") long personId);

    /**
     * Find the latest company of a contact by personId.
     *
     * @param personId The personId to search for.
     * @return Optional<Company> containing the latest company of the contact with the specified personId.
     */
    @Query("SELECT c FROM Contact cn JOIN cn.companyList c WHERE cn.personId = :personId ORDER BY c.companyId DESC")
    Optional<Company> findLatestCompanyByPersonId(@Param("personId") long personId);

    /**
     * Delete a contact by contactId.
     *
     * @param contactId The contactId of the contact to be deleted.
     */
    void deleteByContactId(long contactId);
}
