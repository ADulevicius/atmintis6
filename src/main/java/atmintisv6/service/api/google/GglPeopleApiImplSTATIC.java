package atmintisv6.service.api.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.PeopleServiceScopes;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the {@link GglPeopleApi} interface for interacting with the Google People API.
 */
@Component
public class GglPeopleApiImplSTATIC implements GglPeopleApi {
    private static final String APPLICATION_NAME = "Atmintis2";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Arrays.asList(PeopleServiceScopes.CONTACTS);
    // change location of google credentials file (get it from google console) to retrieve google PEOPLE data
    private static final String CREDENTIALS_FILE_PATH = "/home/ad/IdeaProjects/atmintisv4/src/main/resources/credentialsVEIKIA.json";
    private static final int NUM_OF_INITIAL_CONTACTS_TO_TAKE = 260;
    private static final String ALL_PERSON_FIELDS = "addresses,ageRanges,biographies,birthdays,calendarUrls,clientData,coverPhotos,emailAddresses,events,externalIds,genders,imClients,interests,locales,locations,memberships,metadata,miscKeywords,names,nicknames,occupations,organizations,phoneNumbers,photos,relations,sipAddresses,skills,urls,userDefined";

    /**
     * Runs the Google People API to retrieve a list of connections.
     *
     * @return ListConnectionsResponse containing the connections retrieved from the API.
     */
    public ListConnectionsResponse runGglPeopleAPI() {
        return fetchGooglePeopleData();
    }

    /**
     * Retrieves the OAuth credentials for accessing the Google People API.
     *
     * @return Credential for accessing the API.
     */
    private static Credential getCredentials() {
        try {
            InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
            if (in == null) {
                throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
            }
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
            GoogleAuthorizationCodeFlow flow = getGoogleAuthCodeFlow(clientSecrets);
            VerificationCodeReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
            return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException:");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException:");
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Fetches data from the Google People API.
     *
     * @return ListConnectionsResponse containing the fetched connections.
     */
    public static ListConnectionsResponse fetchGooglePeopleData() {
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            ListConnectionsResponse response = getConnResponse(getPeopleService(HTTP_TRANSPORT));
            return response;
        } catch (GeneralSecurityException e) {
            System.out.println("GeneralSecurityException:");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException:");
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves the Google Authorization Code Flow for OAuth.
     *
     * @param clientSecrets Google API client secrets.
     * @return GoogleAuthorizationCodeFlow for OAuth.
     * @throws IOException if an I/O error occurs.
     */
    private static GoogleAuthorizationCodeFlow getGoogleAuthCodeFlow(GoogleClientSecrets clientSecrets) throws IOException {
        return new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(), JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
    }

    /**
     * Creates a PeopleService for interacting with the Google People API.
     *
     * @param http_transport HTTP transport.
     * @return PeopleService for the API.
     */
    private static PeopleService getPeopleService(NetHttpTransport http_transport) {
        return new PeopleService.Builder(http_transport, JSON_FACTORY, getCredentials())
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Retrieves the connections response from the PeopleService.
     *
     * @param service PeopleService for the API.
     * @return ListConnectionsResponse containing the fetched connections.
     * @throws IOException if an I/O error occurs.
     */
    private static ListConnectionsResponse getConnResponse(PeopleService service) throws IOException {
        return service.people().connections()
                .list("people/me")
                .setPageSize(NUM_OF_INITIAL_CONTACTS_TO_TAKE)
                .setPersonFields(ALL_PERSON_FIELDS)
                .execute();
    }
}
