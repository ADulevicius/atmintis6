import React, { useState } from 'react';
import { BsFillTrashFill, BsFillPencilFill } from 'react-icons/bs';
import ReactPaginate from 'react-paginate';
import { AiFillLeftCircle, AiFillRightCircle } from "react-icons/ai";
import { IconContext } from "react-icons";
import './Table.css';

export const Table = ({ rows, deleteRow, editRow }) => {
    const contactsPerPage = 15;
    const [currentPage, setCurrentPage] = useState(0);

    const handlePageChange = (selectedPage) => {
        setCurrentPage(selectedPage.selected);
    };

    const startIndex = currentPage * contactsPerPage;
    const endIndex = startIndex + contactsPerPage;
    //console.log("VISIBLEROWS SLICE CHECK")
    //console.log(rows)
    const visibleRows = rows?.slice(startIndex, endIndex);
    if(rows==null) { 
      return <div>Error: Rows data is null</div>
    };

    return (
        <div className="table-wrapper">
            <table className="table table-hover">
                <thead>
                <tr>
                    <th>Person ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone Numbers</th>
                    <th>Emails</th>
                    <th>Occupation</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                    {visibleRows?.map((row, idx) => (
                        <tr key={idx}>
                            <td>{row.personId}</td>
                            <td>{row.names?.firstName || '-'}</td>
                            <td>{row.names?.surname || '-'}</td>
                            <td>{renderPhoneNumbers(row.phoneNumbersList)}</td>
                            <td>{renderEmails(row.emailsList)}</td>
                            <td>{renderOccupation(row.companiesList)}</td>
                            <td>{idx}</td>
                            <td>
                                <span className="actions">
                                    <BsFillTrashFill 
                                        className="delete-btn" 
                                        onClick={() => deleteRow(row.personId)} 
                                    />
                                    <BsFillPencilFill 
                                        className="edit-btn" 
                                        onClick={() => editRow(idx)} 
                                    />
                                </span>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {rows.length > contactsPerPage && (
                <ReactPaginate
                    pageCount={Math.ceil(rows.length / contactsPerPage)}
                    pageRangeDisplayed={2}
                    marginPagesDisplayed={1}
                    onPageChange={handlePageChange}
                    containerClassName={'pagination'}
                    activeClassName={'active'}
                    breakLabel="..."
                    previousLabel={
                        <IconContext.Provider value={{ color: "#B8C1CC", size: "36px" }}>
                        <AiFillLeftCircle />
                        </IconContext.Provider>
                    }
                    nextLabel={
                        <IconContext.Provider value={{ color: "#B8C1CC", size: "36px" }}>
                        <AiFillRightCircle />
                        </IconContext.Provider>
                    }
                />
            )}
        </div>
    );
};

// Helper function to render phone numbers in descending order by phoneNumberId
const renderPhoneNumbers = (phoneNumbersList) => {
  if (!phoneNumbersList?.length) {
      return <div>-</div>;
  }

  const uniquePhoneNumbers = Array.from(
      new Map(phoneNumbersList.map((phoneNumber) => [phoneNumber?.phoneNumber, phoneNumber])).values()
  );

  const sortedPhoneNumbers = uniquePhoneNumbers
      .slice()
      .sort((a, b) => b?.phoneNumberId - a?.phoneNumberId);

  return sortedPhoneNumbers.map((phoneNumber) => (
      <div key={phoneNumber?.phoneNumberId}>{phoneNumber?.phoneNumber}</div>
  ));
};

// Helper function to render emails
const renderEmails = (emailsList) => {
  if (!emailsList?.length) {
      return <div>-</div>;
  }

  const sortedEmailsList = emailsList
      .slice()
      .sort((a, b) => b?.emailId - a?.emailId);

  return sortedEmailsList.map((email) => <div key={email?.emailId}>{email?.email}</div>);
};

// Helper function to render companies in descending order by companyId
const renderOccupation = (companiesList) => {
  if (!companiesList?.length) {
      return <div>-</div>;
  }

  const sortedCompanies = companiesList
      .slice()
      .sort((a, b) => b?.companyId - a?.companyId);

  return sortedCompanies.map((company) => (
      <div key={company?.companyId}>{company?.companyName}</div>
  ));
};

