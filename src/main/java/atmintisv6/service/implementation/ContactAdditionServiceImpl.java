/**
 * Implementation of the ContactAdditionService interface providing functionality
 * for adding contacts with detailed information.
 */
package atmintisv6.service.implementation;

import atmintisv4.dto.*;
import atmintisv4.dto.react.UpdateFromReact;
import atmintisv4.repository.*;
import atmintisv4.service.ContactAdditionService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactAdditionServiceImpl implements ContactAdditionService {

    /**
     * Logger for logging messages and events.
     */
    Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    /**
     * Repositories for handling data access operations for various entities.
     */
    private final ContactRepository contactRepository;
    private final NameRepository nameRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final EmailRepository emailRepository;
    private final CompanyRepository companyRepository;

    /**
     * Constructs an instance of ContactAdditionServiceImpl with the specified repositories.
     *
     * @param contactRepository      Repository for handling data access operations for contacts.
     * @param nameRepository         Repository for handling data access operations for names.
     * @param phoneNumberRepository  Repository for handling data access operations for phone numbers.
     * @param emailRepository        Repository for handling data access operations for emails.
     * @param companyRepository      Repository for handling data access operations for companies.
     */
    public ContactAdditionServiceImpl(ContactRepository contactRepository,
                                      NameRepository nameRepository,
                                      PhoneNumberRepository phoneNumberRepository,
                                      EmailRepository emailRepository,
                                      CompanyRepository companyRepository) {
        this.contactRepository = contactRepository;
        this.nameRepository = nameRepository;
        this.phoneNumberRepository = phoneNumberRepository;
        this.emailRepository = emailRepository;
        this.companyRepository = companyRepository;
    }

    /**
     * Saves a contact with detailed information extracted from the provided UpdateFromReact object.
     *
     * @param updateFromReact The UpdateFromReact object containing information to be saved.
     * @return The Contact object representing the added contact with details.
     */
    @Transactional
    public Contact saveContactWithDetails(UpdateFromReact updateFromReact) {
        // Create and Save the main entity (Contact)
        Contact addedContact = contactRepository.save(new Contact());

        // Save related entities and establish relationships
        addNewNames(addedContact, updateFromReact);
        addNewPhoneNumber(addedContact, updateFromReact);
        addNewEmail(addedContact, updateFromReact);
        addNewCompany(addedContact, updateFromReact);

        return addedContact;
    }

    /**
     * Adds new name information to the provided Contact.
     *
     * @param addedContact    The Contact object to which the name information will be added.
     * @param updateFromReact The UpdateFromReact object containing the name information.
     */
    @Transactional
    private void addNewNames(Contact addedContact, UpdateFromReact updateFromReact) {
        if (updateFromReact.getName() != null) {
            Name addedName = new Name();
            if (updateFromReact.getName().getFirstName() != null && !updateFromReact.getName().getFirstName().isEmpty())
                addedName.setFirstName(updateFromReact.getName().getFirstName());
            if (updateFromReact.getName().getSurname() != null && !updateFromReact.getName().getSurname().isEmpty())
                addedName.setSurname(updateFromReact.getName().getSurname());
            addedContact.setName(addedName);
            nameRepository.save(addedName);
        }
    }

    /**
     * Adds new phone number information to the provided Contact.
     *
     * @param addedContact    The Contact object to which the phone number information will be added.
     * @param updateFromReact The UpdateFromReact object containing the phone number information.
     */
    @Transactional
    private void addNewPhoneNumber(Contact addedContact, UpdateFromReact updateFromReact) {
        if (updateFromReact.getPhoneNumberList() != null) {
            PhoneNumber addedPhoneNumber = new PhoneNumber();
            if (updateFromReact.getPhoneNumberList().getPhoneNumber() != null) {
                addedPhoneNumber.setPhoneNumber(updateFromReact.getPhoneNumberList().getPhoneNumber());
                addedPhoneNumber.setContact(addedContact);
            }
            addedContact.getPhoneNumberList().add(addedPhoneNumber);
            phoneNumberRepository.save(addedPhoneNumber);
        }
    }

    /**
     * Adds new email information to the provided Contact.
     *
     * @param addedContact    The Contact object to which the email information will be added.
     * @param updateFromReact The UpdateFromReact object containing the email information.
     */
    @Transactional
    private void addNewEmail(Contact addedContact, UpdateFromReact updateFromReact) {
        if (updateFromReact.getEmailList() != null) {
            Email addedEmail = new Email();
            if (updateFromReact.getEmailList().getEmail() != null) {
                addedEmail.setEmail(updateFromReact.getEmailList().getEmail());
                addedEmail.setContact(addedContact);
            }
            addedContact.getEmailList().add(addedEmail);
            emailRepository.save(addedEmail);
        }
    }

    /**
     * Adds new company information to the provided Contact.
     *
     * @param addedContact    The Contact object to which the company information will be added.
     * @param updateFromReact The UpdateFromReact object containing the company information.
     */
    @Transactional
    private void addNewCompany(Contact addedContact, UpdateFromReact updateFromReact) {
        if (updateFromReact.getCompanyList() != null) {
            Company addedCompany = new Company();
            if (updateFromReact.getCompanyList().getOccupation() != null) {
                addedCompany.setCompanyName(updateFromReact.getCompanyList().getOccupation());
                addedCompany.setContact(addedContact);
            }
            addedContact.getCompanyList().add(addedCompany);
            companyRepository.save(addedCompany);
        }
    }
}
