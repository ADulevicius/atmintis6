/**
 * Interface defining the contract for managing contact entities and related operations.
 */
package atmintisv6.service;

import atmintisv6.dto.Company;
import atmintisv6.dto.Contact;
import atmintisv6.dto.Name;
import atmintisv6.dto.PhoneNumber;
import atmintisv6.dto.react.UpdateFromReact;

import java.util.List;

public interface ContactService {

    /**
     * Retrieves a list of all contacts.
     *
     * @return List of Contact objects.
     */
    List<Contact> findAll();

    /**
     * Retrieves a contact by its ID.
     *
     * @param id The ID of the contact to retrieve.
     * @return The Contact object, or null if not found.
     */
    Contact findContactById(long id);

    /**
     * Retrieves a contact by person ID.
     *
     * @param personId The person ID of the contact to retrieve.
     * @return The Contact object, or null if not found.
     */
    Contact findContactByPersonId(Long personId);

    /**
     * Retrieves names associated with a contact by its ID.
     *
     * @param contactId The ID of the contact to retrieve names for.
     * @return The Name object, or null if not found.
     */
    Name findNamesByContactId(long contactId);

    /**
     * Retrieves the first name associated with a person by their ID.
     *
     * @param personId The person ID to retrieve the first name for.
     * @return The first name, or null if not found.
     */
    String findFirstNameByPersonId(long personId);

    /**
     * Retrieves the last name associated with a person by their ID.
     *
     * @param personId The person ID to retrieve the last name for.
     * @return The last name, or null if not found.
     */
    String findLastNameByPersonId(long personId);

    /**
     * Retrieves the latest phone number associated with a person by their ID.
     *
     * @param personId The person ID to retrieve the latest phone number for.
     * @return The PhoneNumber object, or null if not found.
     */
    PhoneNumber findLatestPhoneNumberByPersonId(long personId);

    /**
     * Retrieves the latest company associated with a person by their ID.
     *
     * @param personId The person ID to retrieve the latest company for.
     * @return The Company object, or null if not found.
     */
    Company findLatestCompanyByPersonId(long personId);

    /**
     * Deletes a contact by its ID.
     *
     * @param contactId The ID of the contact to delete.
     * @return The deleted Contact object, or null if not found.
     */
    Contact deleteByContactId(long contactId);

    /**
     * Updates a contact and its related entries based on the provided UpdateFromReact object.
     *
     * @param contactId The ID of the contact to update.
     * @param updateFromReact The UpdateFromReact object containing the updated contact information.
     * @return The updated Contact object.
     */
    Contact updateContactAndRelatedEntries(Long contactId, UpdateFromReact updateFromReact);
}
