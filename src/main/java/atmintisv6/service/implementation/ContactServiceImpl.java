/**
 * Implementation of the ContactService interface providing functionality
 * for managing and retrieving contact information.
 */
package atmintisv6.service.implementation;

import atmintisv4.dto.*;
import atmintisv4.dto.react.UpdateFromReact;
import atmintisv4.repository.*;
import atmintisv4.service.ContactService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    /**
     * Logger for logging messages and events.
     */
    Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    /**
     * Repository for handling data access operations for contacts.
     */
    private final ContactRepository contactRepository;

    /**
     * Constructs an instance of ContactServiceImpl with the specified ContactRepository.
     *
     * @param contactRepository The repository for handling data access operations for contacts.
     */
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Saves the provided contact to the repository.
     *
     * @param contact The Contact object to be saved.
     */
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    /**
     * Finds a contact by the specified ID.
     *
     * @param id The ID of the contact to be found.
     * @return The Contact object if found, otherwise null.
     */
    @Override
    public Contact findContactById(long id) {
        return contactRepository.findById(id).orElse(null);
    }

    /**
     * Finds a contact by the specified person ID.
     *
     * @param personId The person ID of the contact to be found.
     * @return The Contact object if found, otherwise null.
     */
    @Override
    public Contact findContactByPersonId(Long personId) {
        return contactRepository.findByPersonId(personId).orElse(null);
    }

    /**
     * Finds names associated with the contact identified by the specified contact ID.
     *
     * @param contactId The ID of the contact for which names are to be found.
     * @return The Name object if found, otherwise null.
     */
    @Override
    public Name findNamesByContactId(long contactId) {
        return contactRepository.findById(contactId)
                .map(Contact::getName)
                .orElse(null);
    }

    /**
     * Retrieves a list of all contacts.
     *
     * @return List of Contact objects representing all contacts.
     */
    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    /**
     * Finds the first name associated with the contact identified by the specified person ID.
     *
     * @param personId The person ID of the contact for which the first name is to be found.
     * @return The first name if found, otherwise null.
     */
    @Override
    public String findFirstNameByPersonId(long personId) {
        return contactRepository.findFirstNameByPersonId(personId);
    }

    /**
     * Finds the last name associated with the contact identified by the specified person ID.
     *
     * @param personId The person ID of the contact for which the last name is to be found.
     * @return The last name if found, otherwise null.
     */
    @Override
    public String findLastNameByPersonId(long personId) {
        return contactRepository.findLastNameByPersonId(personId);
    }

    /**
     * Finds the latest phone number associated with the contact identified by the specified person ID.
     *
     * @param personId The person ID of the contact for which the latest phone number is to be found.
     * @return The PhoneNumber object if found, otherwise null.
     */
    @Override
    public PhoneNumber findLatestPhoneNumberByPersonId(long personId) {
        return contactRepository.findLatestPhoneNumberByPersonId(personId).orElse(null);
    }

    /**
     * Finds the latest company associated with the contact identified by the specified person ID.
     *
     * @param personId The person ID of the contact for which the latest company is to be found.
     * @return The Company object if found, otherwise null.
     */
    @Override
    public Company findLatestCompanyByPersonId(long personId) {
        return contactRepository.findLatestCompanyByPersonId(personId).orElse(null);
    }

    /**
     * Deletes the contact identified by the specified contact ID.
     *
     * @param contactId The ID of the contact to be deleted.
     * @return The deleted Contact object if found, otherwise null.
     */
    @Transactional
    @Override
    public Contact deleteByContactId(long contactId) {
        Optional<Contact> contactToDelete = contactRepository.findById(contactId);

        if (contactToDelete.isPresent()) {
            contactRepository.deleteByContactId(contactId);
            return contactToDelete.get();
        } else {
            logger.warn("Contact not found. ID: {}", contactId);
            return null;
        }
    }

    /**
     * Updates the contact and related entries based on the provided UpdateFromReact object.
     *
     * @param contactId       The ID of the contact to be updated.
     * @param updateFromReact The UpdateFromReact object containing information for the update.
     * @return The updated Contact object.
     */
    @Transactional
    @Override
    public Contact updateContactAndRelatedEntries(Long contactId, UpdateFromReact updateFromReact) {
        Contact oldContact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + contactId));

        if (updateFromReact.getName().getFirstName() != null && !updateFromReact.getName().getFirstName().isEmpty())
            oldContact.getName().setFirstName(updateFromReact.getName().getFirstName());
        if (updateFromReact.getName().getSurname() != null && !updateFromReact.getName().getSurname().isEmpty())
            oldContact.getName().setSurname(updateFromReact.getName().getSurname());

        if (updateFromReact.getPhoneNumberList().getPhoneNumber() != null && !updateFromReact.getPhoneNumberList().getPhoneNumber().isEmpty())
            updatePhoneNumber(oldContact, updateFromReact);
        if (updateFromReact.getCompanyList().getOccupation() != null && !updateFromReact.getCompanyList().getOccupation().isEmpty())
            updateCompany(oldContact, updateFromReact);
        if (updateFromReact.getEmailList().getEmail() != null && !updateFromReact.getEmailList().getEmail().isEmpty())
            updateEmail(oldContact, updateFromReact);

        return contactRepository.save(oldContact);
    }

    /**
     * Updates the phone number of the specified contact based on the provided UpdateFromReact object.
     *
     * @param existingContact The Contact object to be updated.
     * @param updateFromReact The UpdateFromReact object containing the phone number information for the update.
     */
    @Transactional
    private void updatePhoneNumber(Contact existingContact, UpdateFromReact updateFromReact) {
        if (!Objects.isNull(updateFromReact.getPhoneNumberList().getPhoneNumberId())) {
            Optional<PhoneNumber> phoneNumberToUpdate = existingContact.getPhoneNumberList().stream()
                    .filter(e -> e.getPhoneNumberId() == updateFromReact.getPhoneNumberList().getPhoneNumberId())
                    .findFirst();
            phoneNumberToUpdate.ifPresent(phoneNumber -> phoneNumber.setPhoneNumber(updateFromReact.getPhoneNumberList().getPhoneNumber()));
        } else {
            PhoneNumber newPhoneNumber = new PhoneNumber();
            newPhoneNumber.setPhoneNumber(updateFromReact.getPhoneNumberList().getPhoneNumber());
            newPhoneNumber.setContact(existingContact);
            newPhoneNumber.setPersonId(updateFromReact.getPersonId());
            existingContact.getPhoneNumberList().add(newPhoneNumber);
        }
    }

    /**
     * Updates the company of the specified contact based on the provided UpdateFromReact object.
     *
     * @param existingContact The Contact object to be updated.
     * @param updateFromReact The UpdateFromReact object containing the company information for the update.
     */
    @Transactional
    private void updateCompany(Contact existingContact, UpdateFromReact updateFromReact) {
        if (!Objects.isNull(updateFromReact.getCompanyList().getCompanyId())) {
            existingContact.getCompanyList().stream()
                    .filter(company -> company.getCompanyId() == (updateFromReact.getCompanyList().getCompanyId()))
                    .findFirst()
                    .ifPresent(company -> company.setCompanyName(updateFromReact.getCompanyList().getOccupation()));
        } else {
            Company newCompany = new Company();
            newCompany.setCompanyName(updateFromReact.getCompanyList().getOccupation());
            newCompany.setContact(existingContact);
            newCompany.setPersonId(updateFromReact.getPersonId());
            existingContact.getCompanyList().add(newCompany);
        }
    }

    /**
     * Updates the email of the specified contact based on the provided UpdateFromReact object.
     *
     * @param existingContact The Contact object to be updated.
     * @param updateFromReact The UpdateFromReact object containing the email information for the update.
     */
    @Transactional
    private void updateEmail(Contact existingContact, UpdateFromReact updateFromReact) {
        if (!Objects.isNull(updateFromReact.getEmailList().getEmailId())) {
            existingContact.getEmailList().stream()
                    .filter(email -> email.getEmailId() == (updateFromReact.getEmailList().getEmailId()))
                    .findFirst()
                    .ifPresent(email -> email.setEmail(updateFromReact.getEmailList().getEmail()));
        } else {
            Email newEmail = new Email();
            newEmail.setEmail(updateFromReact.getEmailList().getEmail());
            newEmail.setContact(existingContact);
            newEmail.setPersonId(updateFromReact.getPersonId());
            existingContact.getEmailList().add(newEmail);
        }
    }
}
