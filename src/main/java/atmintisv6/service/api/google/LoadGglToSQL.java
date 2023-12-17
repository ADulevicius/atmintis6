package atmintisv6.service.api.google;

import atmintisv6.dto.*;
import atmintisv6.dto.Address;
import atmintisv6.dto.Name;
import atmintisv6.dto.PhoneNumber;
import atmintisv6.service.implementation.*;
import com.google.api.services.people.v1.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoadGglToSQL {
    List<Contact> aListContacts = new ArrayList<>();
    private final ContactServiceImpl contactServiceImpl;
    private final AddressServiceImpl addressServiceImpl;
    private final CompanyServiceImpl companyServiceImpl;
    private final CustomFieldServiceImpl customFieldServiceImpl;
    private final EmailServiceImpl emailServiceImpl;
    private final NameServiceImpl nameServiceImpl;
    private final PhoneNumberServiceImpl phoneNumberServiceImpl;
    private final RelatedPersonServiceImpl relatedPersonServiceImpl;
    private final SignificantDateServiceImpl significantDateServiceImpl;

    public LoadGglToSQL(ContactServiceImpl contactServiceImpl,
                        AddressServiceImpl addressServiceImpl, CompanyServiceImpl companyServiceImpl,
                        CustomFieldServiceImpl customFieldServiceImpl, EmailServiceImpl emailServiceImpl,
                        NameServiceImpl nameServiceImpl, PhoneNumberServiceImpl phoneNumberServiceImpl,
                        RelatedPersonServiceImpl relatedPersonServiceImpl,
                        SignificantDateServiceImpl significantDateServiceImpl) {
        this.contactServiceImpl = contactServiceImpl;
        this.addressServiceImpl = addressServiceImpl;
        this.companyServiceImpl = companyServiceImpl;
        this.customFieldServiceImpl = customFieldServiceImpl;
        this.emailServiceImpl = emailServiceImpl;
        this.nameServiceImpl = nameServiceImpl;
        this.phoneNumberServiceImpl = phoneNumberServiceImpl;
        this.relatedPersonServiceImpl = relatedPersonServiceImpl;
        this.significantDateServiceImpl = significantDateServiceImpl;
    }

    public boolean loadConnToSQL(List<Person> listPerson) {
        if (listPerson != null && !listPerson.isEmpty()) {
            for (Person person : listPerson) {
                loadContactToSQL(person);
                loadCompaniesToSQL(person);
                loadAddressToSQL(person);
                loadCustomFieldsToSQL(person);
                loadEmailsToSQL(person);
                loadNamesToSQL(person);
                loadPhoneNumbersToSQL(person);
                loadRelatedPersonsToSQL(person);
                loadSignificantDatesToSQL(person);
            }
        }
        return true;
    }

    public boolean loadContactToSQL(Person person){
        boolean savedContacts;
        Contact contact = new Contact(person.getResourceName()
                                            .substring(person.getResourceName().indexOf("/c") + 2) );
            aListContacts.add(contact);
            contactServiceImpl.save(contact);
        return true;
    }

    public boolean loadAddressToSQL(Person person){
        boolean savedAddresses;
        List<com.google.api.services.people.v1.model.Address> listAddress = person.getAddresses();
        if (listAddress != null && !listAddress.isEmpty()) {
            long personId = Long.parseLong(person.getResourceName()
                                            .substring(person.getResourceName().indexOf("/c") + 2));
            for (com.google.api.services.people.v1.model.Address personAddress : listAddress) {
                savedAddresses = saveAdress(personAddress, personId);
            }
            return true;
        }else
            return false;
    }

    public boolean saveAdress(com.google.api.services.people.v1.model.Address personAddress, long personId) {
        try {
            Address addressSQLEntry = new Address();
            addressSQLEntry.setPersonId(personId);
            addressSQLEntry.setCountry(personAddress.getCountry());
            addressSQLEntry.setStreetAddress(personAddress.getStreetAddress());
            addressSQLEntry.setStreetAddress2(personAddress.getExtendedAddress());
            addressSQLEntry.setPostTown(personAddress.getCity());
            addressSQLEntry.setPostcode(personAddress.getPostalCode());
            addressSQLEntry.setPoBox(personAddress.getPoBox());
            addressSQLEntry.setLabel(personAddress.getType());
            addressSQLEntry.setContact(aListContacts.getLast());
            aListContacts.getLast()
                    .getAddressList()
                    .add(addressSQLEntry);
            addressServiceImpl.save(addressSQLEntry);
            return true;
        } catch (IllegalArgumentException e)  {
            System.out.println("IllegalArgumentException:");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadCompaniesToSQL(Person person){
        boolean savedCompanies;
        List<Organization> listOrganization = person.getOrganizations();
        if (listOrganization != null && !listOrganization.isEmpty()) {
            long personId = Long.parseLong(person.getResourceName()
                                            .substring(person.getResourceName().indexOf("/c") + 2));
            for (Organization personOrganization : listOrganization) {
                savedCompanies = saveCompanies(personOrganization, personId);
            }
            return true;
        }else
            return false;
    }


    // NOTE: GALIMA IDETI DAUK DAUGIAU LAUKU, DATA IR PAN; PRIDETI KAI BUS VISA KITA UZBAIGTA
    public boolean saveCompanies(Organization personCompanies, long personId) {
        try {
            Company companySQLEntry = new Company();
            companySQLEntry.setPersonId(personId);
            companySQLEntry.setCompanyName(personCompanies.getName());
            companySQLEntry.setJobTitle(personCompanies.getTitle());
            companySQLEntry.setDepartment(personCompanies.getDepartment());
            companySQLEntry.setContact(aListContacts.getLast());
            aListContacts.getLast()
                    .getCompanyList()
                    .add(companySQLEntry);
            companyServiceImpl.save(companySQLEntry);
            return true;
        } catch (IllegalArgumentException e)  {
            System.out.println("IllegalArgumentException:");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadCustomFieldsToSQL(Person person){
        boolean savedCustomFields;
        List<UserDefined> listCustomFields = person.getUserDefined();
        if (listCustomFields != null && !listCustomFields.isEmpty()) {
            long personId = Long.parseLong(person.getResourceName()
                                            .substring(person.getResourceName().indexOf("/c") + 2));
            for (UserDefined personCustomFields : listCustomFields) {
                savedCustomFields = saveCustomFields(personCustomFields, personId);
            }
            return true;
        }else
            return false;
    }
    public boolean saveCustomFields(UserDefined personUserDefined, long personId) {
        try {
            CustomField customFieldSQLEntry = new CustomField();
            customFieldSQLEntry.setPersonId(personId);
            customFieldSQLEntry.setLabel(personUserDefined.getKey());
            customFieldSQLEntry.setCustomField(personUserDefined.getValue());
            customFieldSQLEntry.setContact(aListContacts.getLast());
            aListContacts.getLast()
                    .getCustomFieldList()
                    .add(customFieldSQLEntry);
            customFieldServiceImpl.save(customFieldSQLEntry);
            return true;
        } catch (IllegalArgumentException e)  {
            System.out.println("IllegalArgumentException:");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadEmailsToSQL(Person person){
        boolean savedEmails;
        List<EmailAddress> listEmails = person.getEmailAddresses();
        if (listEmails != null && !listEmails.isEmpty()) {
            long personId = Long.parseLong(person.getResourceName()
                                        .substring(person.getResourceName().indexOf("/c") + 2));
            for (EmailAddress personEmails : listEmails) {
                savedEmails = saveEmails(personEmails, personId);
            }
            return true;
        }else
            return false;
    }
    public boolean saveEmails(EmailAddress personEmails, long personId) {
        try {
            Email emailSQLEntry = new Email();
            emailSQLEntry.setPersonId(personId);
            emailSQLEntry.setEmail(personEmails.getValue());
            emailSQLEntry.setLabel(personEmails.getType());
            emailSQLEntry.setContact(aListContacts.getLast());
            aListContacts.getLast()
                    .getEmailList()
                    .add(emailSQLEntry);
            emailServiceImpl.save(emailSQLEntry);
            return true;
        } catch (IllegalArgumentException e)  {
            System.out.println("IllegalArgumentException:");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadNamesToSQL(Person person){
        boolean savedNames;
        String fileAs = "";
        String nickname = "";
        List<com.google.api.services.people.v1.model.Name> listNames = person.getNames();
        if (listNames != null && !listNames.isEmpty()) {
            long personId = Long.parseLong(person.getResourceName()
                                        .substring(person.getResourceName().indexOf("/c") + 2));
            for (com.google.api.services.people.v1.model.Name personName : listNames) {
                if (person.getFileAses() != null) {
                    fileAs = person.getFileAses().getLast().getValue();
                }
                if (person.getNicknames() != null){
                    nickname = person.getNicknames().getLast().getValue();
                }
                savedNames = saveNames(personName, personId, fileAs ,nickname);
            }
            return true;
        }else
            return false;
    }

    public boolean saveNames(com.google.api.services.people.v1.model.Name personName, long personId, String fileAs, String personLastNickname) {
        try {
            Name nameSQLEntry = new Name();
            nameSQLEntry.setPersonId(personId);
            if (!fileAs.isBlank())
                nameSQLEntry.setFileAs(fileAs);
            nameSQLEntry.setFirstName(personName.getGivenName());
            nameSQLEntry.setMiddleName(personName.getMiddleName());
            if (!personLastNickname.isBlank())
                nameSQLEntry.setNickname(personLastNickname);
            nameSQLEntry.setPhoneticFirstName(personName.getPhoneticGivenName());
            nameSQLEntry.setPhoneticMiddleName(personName.getPhoneticMiddleName());
            nameSQLEntry.setPhoneticSurname(personName.getPhoneticFamilyName());
            nameSQLEntry.setPrefix(personName.getHonorificPrefix());
            nameSQLEntry.setSuffix(personName.getHonorificSuffix());
            nameSQLEntry.setSurname(personName.getFamilyName());
            nameSQLEntry.setContact(aListContacts.getLast());
            aListContacts
                    .getLast()
                    .setName(nameSQLEntry);
            nameServiceImpl.save(nameSQLEntry);
            return true;
        } catch (IllegalArgumentException e)  {
            System.out.println("IllegalArgumentException:");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadPhoneNumbersToSQL(Person person){
        boolean savedPhoneNumber;
        List<com.google.api.services.people.v1.model.PhoneNumber> listPhoneNumbers = person.getPhoneNumbers();
        if (listPhoneNumbers != null && !listPhoneNumbers.isEmpty()) {
            long personId = Long.parseLong(person.getResourceName()
                                        .substring(person.getResourceName().indexOf("/c") + 2));
            for (com.google.api.services.people.v1.model.PhoneNumber personPhoneNumber : listPhoneNumbers) {
                savedPhoneNumber = savePhoneNumbers(personPhoneNumber, personId);
            }
            return true;
        }else
            return false;
    }

    public boolean savePhoneNumbers(com.google.api.services.people.v1.model.PhoneNumber personPhoneNumber, long personId) {
        try {
            PhoneNumber phoneNumberSQLEntry = new PhoneNumber();
            phoneNumberSQLEntry.setPersonId(personId);
            phoneNumberSQLEntry.setPhoneNumber(personPhoneNumber.getValue());
            phoneNumberSQLEntry.setLabel(personPhoneNumber.getType());
            phoneNumberSQLEntry.setContact(aListContacts.getLast());
            aListContacts.getLast()
                    .getPhoneNumberList()
                    .add(phoneNumberSQLEntry);
            phoneNumberServiceImpl.save(phoneNumberSQLEntry);
            return true;
        } catch (IllegalArgumentException e)  {
            System.out.println("IllegalArgumentException:");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadRelatedPersonsToSQL(Person person){
        boolean savedRelatedPerson;
        List<Relation> listRelatedPersons = person.getRelations();
        if (listRelatedPersons != null && !listRelatedPersons.isEmpty()) {
            long personId = Long.parseLong(person.getResourceName()
                                        .substring(person.getResourceName().indexOf("/c") + 2));
            for (Relation personRelation : listRelatedPersons) {
                savedRelatedPerson = saveRelation(personRelation, personId);
            }
            return true;
        }else
            return false;
    }

    public boolean saveRelation(Relation personRelation, long personId) {
        try {
            RelatedPerson relatedPersonSQLEntry = new RelatedPerson();
            relatedPersonSQLEntry.setPersonId(personId);
            relatedPersonSQLEntry.setRelatedPerson(personRelation.getPerson());
            relatedPersonSQLEntry.setLabel(personRelation.getType());
            relatedPersonSQLEntry.setContact(aListContacts.getLast());
            aListContacts.getLast()
                    .getRelatedPersonList()
                    .add(relatedPersonSQLEntry);
            relatedPersonServiceImpl.save(relatedPersonSQLEntry);
            return true;
        } catch (IllegalArgumentException e)  {
            System.out.println("IllegalArgumentException:");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadSignificantDatesToSQL(Person person){
        boolean savedSignificantDate;
        List<Event> listSignificantDates = person.getEvents();
        if (listSignificantDates != null && !listSignificantDates.isEmpty()) {
            long personId = Long.parseLong(person.getResourceName()
                                        .substring(person.getResourceName().indexOf("/c") + 2));
            for (Event personEvent : listSignificantDates) {
                savedSignificantDate = saveSignificantDate(personEvent, personId);
            }
            return true;
        }else
            return false;
    }

    public boolean saveSignificantDate(Event personEvent, long personId) {
        try {
            SignificantDate significantDateSQLEntry = new SignificantDate();
            significantDateSQLEntry.setPersonId(personId);
            significantDateSQLEntry.setSignificantDay(personEvent.getDate().getDay());
            significantDateSQLEntry.setSignificantMonth(personEvent.getDate().getMonth());
            significantDateSQLEntry.setSignificantYear(personEvent.getDate().getYear());
            significantDateSQLEntry.setLabel(personEvent.getType());
            significantDateSQLEntry.setContact(aListContacts.getLast());
            aListContacts.getLast()
                    .getSignificantDateList()
                    .add(significantDateSQLEntry);
            significantDateServiceImpl.save(significantDateSQLEntry);
            return true;
        } catch (IllegalArgumentException e)  {
            System.out.println("IllegalArgumentException:");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
