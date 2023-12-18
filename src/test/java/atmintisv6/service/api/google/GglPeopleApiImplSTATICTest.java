package atmintisv6.service.api.google;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GglPeopleApiImplSTATICTest {

    @Mock
    private PeopleService peopleService;

    @InjectMocks
    private GglPeopleApiImplSTATIC gglPeopleApi;

    @Test
    void runGglPeopleAPI() throws Exception{
        when(peopleService.people().connections().list(anyString())
                .setPageSize(anyInt())
                .setPersonFields(anyString())
                .execute())
                .thenReturn(new ListConnectionsResponse());

        ListConnectionsResponse result = gglPeopleApi.runGglPeopleAPI();

        assertNotNull(result);
    }

    @Test
    void fetchGooglePeopleData() throws Exception{
        when(peopleService.people().connections().list(anyString())
                .setPageSize(anyInt())
                .setPersonFields(anyString())
                .execute())
                .thenReturn(new ListConnectionsResponse());

        ListConnectionsResponse result = GglPeopleApiImplSTATIC.fetchGooglePeopleData();

        assertNotNull(result);
    }
}