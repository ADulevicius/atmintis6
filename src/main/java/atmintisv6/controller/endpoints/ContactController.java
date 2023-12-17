package atmintisv6.controller.endpoints;

import atmintisv4.dto.Contact;
import atmintisv4.dto.react.UpdateFromReact;
import atmintisv4.service.ContactAdditionService;
import atmintisv4.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for CRUD operations on contacts.
 */
@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private final ContactService contactService;
    private final ContactAdditionService contactAdditionService;

    /**
     * Constructor for ContactController.
     *
     * @param contactService         The service for handling contact-related operations.
     * @param contactAdditionService The service for adding new contacts.
     */
    public ContactController(ContactService contactService, ContactAdditionService contactAdditionService) {
        this.contactService = contactService;
        this.contactAdditionService = contactAdditionService;
    }

    /**
     * Retrieves all contacts.
     *
     * @return ResponseEntity with the list of contacts and HTTP status OK.
     */
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok().body(contactService.findAll());
    }

    /**
     * Retrieves a contact by its ID.
     *
     * @param id The ID of the contact to retrieve.
     * @return ResponseEntity with the contact and HTTP status OK.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        return ResponseEntity.ok().body(contactService.findContactById(id));
    }

    /**
     * Deletes a contact by its ID.
     *
     * @param id The ID of the contact to delete.
     * @return ResponseEntity with the deleted contact and HTTP status OK if successful, or HTTP status NOT_FOUND if not found.
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contact> deleteContact(@PathVariable Long id) {
        Contact deletedContact = contactService.deleteByContactId(id);
        return deletedContact != null ? ResponseEntity.ok().body(deletedContact) : ResponseEntity.notFound().build();
    }

    /**
     * Updates a contact by its ID.
     *
     * @param contactId       The ID of the contact to update.
     * @param updateFromReact The updated information for the contact.
     * @return ResponseEntity with the updated contact and HTTP status OK.
     */
    @PutMapping("/update/{contactId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contact> updateContact(@PathVariable Long contactId,
                                                 @RequestBody UpdateFromReact updateFromReact) {
        return ResponseEntity.ok().body(contactService.updateContactAndRelatedEntries(contactId, updateFromReact));
    }

    /**
     * Adds a new contact.
     *
     * @param updateFromReact The information for the new contact.
     * @return ResponseEntity with the added contact and HTTP status OK, or HTTP status INTERNAL_SERVER_ERROR if an exception occurs.
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contact> addContact(@RequestBody UpdateFromReact updateFromReact) {
        try {
            return ResponseEntity.ok().body(contactAdditionService.saveContactWithDetails(updateFromReact));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
