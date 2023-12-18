// Assuming you have Axios installed in your project
// You can install it using: npm install axios

import axios from 'axios';

// Replace 'YOUR_BACKEND_BASE_URL' with the actual base URL of your Spring Boot backend
const BASE_URL = 'http://localhost:8080';

// Function to get contact data by ID
const getContactById = async (id) => {
  try {
    const response = await axios.get(`${BASE_URL}/contacts/${id}`);
    
    // Assuming the response data is an array of ContactsNew objects
    const contacts = response.data;
    
    // Do something with the retrieved data
    console.log('Contacts:', contacts);
    
    return contacts;
  } catch (error) {
    // Handle error
    console.error('Error fetching contact data:', error);
    throw error;
  }
};

// Example usage
const contactId = 123; // Replace with the actual contact ID you want to retrieve
getContactById(contactId);