package atmintisv6.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import atmintisv6.dto.Company;
import atmintisv6.dto.Contact;
import atmintisv6.repository.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;


@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findContactById() {
        // Arrange
        long contactId = 1L;
        Contact expectedContact = new Contact();
        when(contactRepository.findById(contactId)).thenReturn(Optional.of(expectedContact));

        // Act
        Contact result = contactService.findContactById(contactId);

        // Assert
        assertEquals(expectedContact, result);
    }

    @Test
    void findContactByPersonId() {
        // Arrange
        Long personId = 123L;
        Contact expectedContact = new Contact();
        when(contactRepository.findByPersonId(personId)).thenReturn(Optional.of(expectedContact));

        // Act
        Contact result = contactService.findContactByPersonId(personId);

        // Assert
        assertEquals(expectedContact, result);
    }

    @Test
    void testDeleteByContactId_ContactExists() {
        // Arrange
        long contactId = 1L;
        Contact expectedContact = new Contact();
        when(contactRepository.findById(contactId)).thenReturn(Optional.of(expectedContact));
        doNothing().when(contactRepository).deleteByContactId(contactId);

        // Act
        Contact result = contactService.deleteByContactId(contactId);

        // Assert
        verify(contactRepository, times(1)).deleteByContactId(contactId);
        assertEquals(expectedContact, result);
    }

    @Test
    void testDeleteByContactId_ContactNotFound() {
        // Arrange
        long contactId = 1L;
        when(contactRepository.findById(contactId)).thenReturn(Optional.empty());

        // Act
        Contact result = contactService.deleteByContactId(contactId);

        // Assert
        verify(contactRepository, never()).deleteByContactId(contactId);
        assertNull(result);
    }

    @Test


    void testFindAllContacts() {
        // Arrange
        List<Contact> expectedContacts = new ArrayList<>();
        when(contactRepository.findAll()).thenReturn(expectedContacts);

        // Act
        List<Contact> result = contactService.findAll();

        // Assert
        assertEquals(expectedContacts, result);
    }

    @Test
    void testFindLastNameByPersonId() {
        // Arrange
        long personId = 1L;
        String expectedLastName = "Doe";
        when(contactRepository.findLastNameByPersonId(personId)).thenReturn(expectedLastName);

        // Act
        String result = contactService.findLastNameByPersonId(personId);

        // Assert
        assertEquals(expectedLastName, result);
    }

    @Test
    void testFindLatestCompanyByPersonId() {
        // Arrange
        long personId = 1L;
        Company expectedCompany = new Company();
        when(contactRepository.findLatestCompanyByPersonId(personId)).thenReturn(Optional.of(expectedCompany));

        // Act
        Company result = contactService.findLatestCompanyByPersonId(personId);

        // Assert
        assertEquals(expectedCompany, result);
    }
}