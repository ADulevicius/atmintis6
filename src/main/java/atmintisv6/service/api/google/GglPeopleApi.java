package atmintisv6.service.api.google;

import com.google.api.services.people.v1.model.ListConnectionsResponse;

/**
 * Interface for interacting with the Google People API.
 */
public interface GglPeopleApi {

    /**
     * Runs the Google People API to retrieve a list of connections.
     *
     * @return ListConnectionsResponse containing the connections retrieved from the API.
     */
    ListConnectionsResponse runGglPeopleAPI();
}
