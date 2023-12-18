import { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';
import { Table } from './components-oldVersion/Table';
import { Modal } from './components-oldVersion/Modal';

function App() {
  const [modalOpen, setModalOpen] = useState(false);
  const [rows, setRows] = useState([]);
  const [rowToEdit, setRowToEdit] = useState(null);

  // console.log("VEIKIA SENA VERSIJA")
  // console.log("rowToEdit: ", rowToEdit);


  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/v1/contacts/all'); // Replace with your actual API endpoint
        setRows(response.data);
        //console.log(rows);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchData();
  }, []);

  const handleEditRow = (idx) => {
    setRowToEdit(idx);
    setModalOpen(true);
  };

  const handleDeleteRow = (targetId) => {
    setRows((prevRows) => prevRows.filter((row) => row.personId !== targetId));
  };

const handleSubmit = (newRow) => {
  if (rowToEdit === null) {
    // Add a new row
    console.log("Adding a new row", newRow);
    setRows((prevRows) => [...prevRows, newRow]);
  } else {
    // Edit an existing row
    console.log("Editing an existing row", newRow);
    console.log("Editing an existing row -> rowToEdit", rowToEdit);
    setRows((prevRows) =>
      prevRows.map((currRow, idx) => (/* console.log("Idx:",idx,"currRow:",currRow, currRow.personId === rowToEdit.personId), */
        currRow.personId === rowToEdit.personId ? { ...currRow, ...newRow } : currRow
      ))
    );
    console.log("UPDATED ROWS:",rows);
    
  }

  //BACKUP
/* } else {
  // Edit an existing row
  console.log("Editing an existing row", newRow);
  console.log("Editing an existing row -> rowToEdit", rowToEdit);
  setRows((prevRows) =>
    prevRows.map((currRow, idx) => (console.log("Idx:",idx,"currRow:",currRow),
      idx === rowToEdit ? { ...currRow, ...newRow } : currRow
    ))
  );
  console.log("UPDATED ROWS:",rows)
} */




  //rowToEdit yra visa eilute
  //idx yra turbut indexas

  // Reset rowToEdit
  setRowToEdit(null);

  // Close the modal
  setModalOpen(false);
};
  
  return (
    <div className="App">
      <Table rows={rows} deleteRow={handleDeleteRow} editRow={handleEditRow} />
      <button className="btn" onClick={() => setModalOpen(true)}>
        Add
      </button>
      {modalOpen && (
        <Modal
          closeModal={() => {
            setModalOpen(false);
            setRowToEdit(null);
          }}
          onSubmit={handleSubmit}
          testRowValue= {rowToEdit}
          // defaultValue={
          //   rowToEdit !== null && rows.length > 0
          //   ? (console.log("defaultValue:rowToEdit:", rowToEdit), console.log("defaultValue:rows:", rows), {personId: '1', name: '2', phoneNumber: '3', occupation: '4', status: 'live'})
          //   : (console.log("defaultValue_KaiNULL:rowToEdit: ", rowToEdit),
          //     console.log("defaultValue:_KaiNULL:rows.length ", rows.length > rowToEdit),
          //    null)
            //rows[rowToEdit]
          //}
        />
      )}
    </div>
  );
}

export default App;



/* TODO:
1. Springe padaryk DTOske kad atiduoti tik duomenis kuriu reikia
    - gal pavyktu parasyti SQL query, kad isrinktu tuos duomenis? butu lengviau.
    
    
*/