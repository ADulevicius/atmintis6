import { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';
import { Table } from './components/Table';
import { Modal } from './components/Modal';

function App() {
  const [modalOpen, setModalOpen] = useState(false);
  const [rows, setRows] = useState([]);
  const [rowToEdit, setRowToEdit] = useState(null);
  const [userEmail, setUserEmail] = useState(null);

  useEffect(() => {
    const fetchUserEmail = async () => {
      try {
        const response =  await axios.get(`http://localhost:8080/user/email`);
        setUserEmail(response.data);
      }catch (error) {
        console.error('Error getting UserEmail:', error);
      }
    };
    fetchUserEmail();
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/v1/contacts/all');
      setRows(response.data);
      console.log(rows);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  const handleRefresh =  () => {
    fetchData();
  }

  const handleEditRow = (idx) => {
    setRowToEdit(idx);
    setModalOpen(true);
  };

  const handleDeleteRow2 = async (targetPersonId) => {
    try {
      await axios.delete(`http://localhost:8080/api/v1/contacts/delete/${targetPersonId}`);
      fetchData();
    }catch (error) {
      console.error('Error deleting row:', error);
    }
  };

  const handleUpdateRow = async (targetContactId, updatedData) => {
    try{
      await axios.put(`http://localhost:8080/api/v1/contacts/update/${targetContactId}`,updatedData);
      fetchData();
    }catch (error) {
      console.error('Error updating row:', error);
    }
  }

  const handleAddRow = async (Data) => {
    
    try{
      await axios.post(`http://localhost:8080/api/v1/contacts/add`,Data);
      fetchData();
    }catch (error) {
      console.error('Error adding row:', error);
    }
  }

  const handleSubmit = (newRow) => {
    if (rowToEdit === null) {
      console.log("Adding a new row", newRow);
      handleAddRow(newRow);
    } else {
      console.log("UPDATING ROW: ", newRow.name.firstName, " with new data:", newRow);
      handleUpdateRow(newRow.contactId,  newRow);
      console.log("UPDATED ROWS:", rows);
    }
  setRowToEdit(null);
  setModalOpen(false);
};
  
  return (
    <div className="App">
      <div className="App-logedInEmail">
        {userEmail && <h5>User: {userEmail}</h5>}
        <div> <button className="btn-refresh" onClick={() => handleRefresh()}>Refresh</button>  </div>
      </div>
      <Table rows={rows} deleteRow={handleDeleteRow2} editRow={handleEditRow} />
        <div>
        <button className="btn-add" onClick={() => setModalOpen(true)}>
          Add
        </button>
        </div>
      {modalOpen && (
        <Modal
          closeModal={() => {
            setModalOpen(false);
            setRowToEdit(null);
          }}
          onSubmit={handleSubmit}
          testRowValue= {rowToEdit}
        />
      )}
    </div>
  );
}

export default App;