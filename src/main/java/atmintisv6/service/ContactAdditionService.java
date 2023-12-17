/**
 * Interface defining the contract for adding and saving contact details.
 */
package atmintisv6.service;

import atmintisv4.dto.Contact;
import atmintisv4.dto.react.UpdateFromReact;

public interface ContactAdditionService {

    /**
     * Saves a new contact with details from the provided UpdateFromReact object.
     *
     * @param updateFromReact The UpdateFromReact object containing contact details to be saved.
     * @return The saved Contact object.
     */
    Contact saveContactWithDetails(UpdateFromReact updateFromReact);
}
