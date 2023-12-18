import React, { useState } from 'react';
import { BsFillTrashFill, BsFillPencilFill } from 'react-icons/bs';
import ReactPaginate from 'react-paginate';
import { AiFillLeftCircle, AiFillRightCircle } from "react-icons/ai";
import { IconContext } from "react-icons";
import './Table.css';

export const Table = ({ rows, deleteRow, editRow }) => {
    const contactsPerPage = 20;
    const [currentPage, setCurrentPage] = useState(0);
    const handlePageChange = (selectedPage) => {
        setCurrentPage(selectedPage.selected);
    };
    const startIndex = currentPage * contactsPerPage;
    const endIndex = startIndex + contactsPerPage;
    const visibleRows = rows?.slice(startIndex, endIndex);

    return (
        <div className="table-wrapper">
            <table className="table-hover">
                <thead className="table-thead">
                <tr>
                    <th>Vardas</th>
                    <th>Pavarde</th>
                    <th>Telefino Nr.</th>
                    <th>El. pastas</th>
                    <th>Darbovete</th>
                    <th>Veiksmai</th>
                </tr>
                </thead>
                <tbody>
                    {Array.isArray(visibleRows) && visibleRows?.map((row, idx) => (
                        <tr key={idx}>
                            <td>{row.name?.firstName || '-'}</td>
                            <td>{row.name?.surname || '-'}</td>
                            <td>{renderPhoneNumbers(row.phoneNumberList) || '-'}</td>
                            <td>{renderEmails(row.emailList) || '-'}</td>
                            <td>{renderOccupation(row.companyList || '-')}</td>
                            <td>
                                <span className="actions">
                                    <BsFillTrashFill 
                                        className="delete-btn" 
                                        onClick={() => deleteRow(row.contactId)} 
                                    />
                                    <BsFillPencilFill 
                                        className="edit-btn" 
                                        onClick={() => editRow(row)} 
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

const renderPhoneNumbers = (phoneNumbersList) => {
    if (!Array.isArray(phoneNumbersList) || phoneNumbersList.length === 0) {
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

const renderEmails = (emailsList) => {
  if (!Array.isArray(emailsList) || !emailsList?.length) {
      return <div>-</div>;
  }

  const sortedEmailsList = emailsList
      .slice()
      .sort((a, b) => b?.emailId - a?.emailId);

  return sortedEmailsList.map((email) => <div key={email?.emailId}>{email?.email}</div>);
};

const renderOccupation = (companiesList) => {
    if (!Array.isArray(companiesList) || companiesList.length === 0) {
      return <div>-</div>;
    }
  
    const sortedCompanies = companiesList
      .slice()
      .sort((a, b) => (a?.companyName || '').localeCompare(b?.companyName || ''));
  
    return sortedCompanies.map((company) => (
      <div key={company?.companyId}>{company?.companyName}</div>
    ));
  };