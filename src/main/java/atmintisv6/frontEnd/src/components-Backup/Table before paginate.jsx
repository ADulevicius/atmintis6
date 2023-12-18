import React from 'react';
import { BsFillTrashFill, BsFillPencilFill } from 'react-icons/bs';
import './Table.css';

export const Table = ({ rows, deleteRow, editRow }) => {
  return (
    <div className="table-wrapper">
      <table className="table">
        <thead>
          <tr>
            <th>Person ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone Numbers</th>
            <th>Emails</th>
            <th>Occupation</th>
          </tr>
        </thead>
        <tbody>
          {rows.map((row, idx) => (
            <tr key={idx}>
              <td>{row.personId}</td>
              <td>{row.names?.firstName || '-'}</td>
              <td>{row.names?.surname || '-'}</td>
              <td>{renderPhoneNumbers(row.phoneNumbersList)}</td>
              <td>{renderEmails(row.emailsList)}</td>
              <td>{renderOccupation(row.companiesList)}</td>
              <td>
                <span className="actions">
                  <BsFillTrashFill className="delete-btn" onClick={() => deleteRow(idx)} />
                  <BsFillPencilFill onClick={() => editRow(idx)} />
                </span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

// Helper function to render phone numbers in descending order by phoneNumberId
const renderPhoneNumbers = (phoneNumbersList) => {
    if (phoneNumbersList.length === 0) {
      return <div>-</div>;
    }
  
    const uniquePhoneNumbers = Array.from(
      new Map(phoneNumbersList.map((phoneNumber) => [phoneNumber.phoneNumber, phoneNumber])).values()
    );
  
    const sortedPhoneNumbers = uniquePhoneNumbers
      .slice()
      .sort((a, b) => b.phoneNumberId - a.phoneNumberId);
  
    return sortedPhoneNumbers.map((phoneNumber) => (
      <div key={phoneNumber.phoneNumberId}>{phoneNumber.phoneNumber}</div>
    ));
  };
  

// Helper function to render emails
const renderEmails = (emailsList) => {
    if (emailsList.length === 0) {
        return <div>-</div>;
      }
    
      const sortedEmailsList = emailsList
        .slice()
        .sort((a, b) => b.emailId - a.emailId);
  return sortedEmailsList.map((email) => <div key={email.emailId}>{email.email}</div>);
};

// Helper function to render companies in descending order by companyId
const renderOccupation = (companiesList) => {
  if (companiesList.length === 0) {
    return <div>-</div>;
  }

  const sortedCompanies = companiesList
    .slice()
    .sort((a, b) => b.companyId - a.companyId);

  return sortedCompanies.map((company) => (
    <div key={company.companyId}>{company.companyName}</div>
  ));
};
