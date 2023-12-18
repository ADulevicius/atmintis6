import {useState} from "react";
import axios from "axios";
import './App.css';
import { Table } from './components/Table';
import { Model } from './components/Modal';
import api from './api/axiosConfig';
import {useEffect} from 'react';

function App() {


  const [test, setTest] = useState();

  const getTest = async() =>{
    try{
      const response = await api.get("/users");

      setTest(response.data);
      console.log(response.data)
    }
    catch(err)
    {
      console.log("test");
    }
  }
  useEffect(() => {
    getTest();
  }, []); // The empty dependency array ensures that this effect runs only once after the initial render

  const [modelOpen,setModelOpen] = useState(false);

  const [rows,setRows] = useState([
    { pId: "1111", name: "Andrius", phoneNumber: "+370 612 34567", occupation: "INVL", status: "live" },
    { pId: "2222", name: "Valdas", phoneNumber: "+370 676 54321", occupation: "home", status: "draft" },
    { pId: "3333", name: "Petras", phoneNumber: "+999 999 99999", occupation: "petrava", status: "error" }
  ]);

  const [rowToEdit, setRowToEdit] = useState(null);

  const handleEditRow = (idx) => {
    setRowToEdit(idx);

    setModelOpen(true);
  }
  
  const handleDeleteRow = (targetIndex) => {
    setRows(rows.filter((_,idx) => idx !== targetIndex))
  }

  const handleSubmit = (newRow) => {
    rowToEdit === null
      ? setRows ([...rows,newRow])
      : setRows(
          rows.map((currRow,idx) => {
            if (idx !== rowToEdit) return currRow;

            return newRow;
          })
        );
  };

  return (
    <div className="App">
      <Table rows={rows} deleteRow={handleDeleteRow} editRow={handleEditRow}/>
      <button className='btn' onClick={()=>setModelOpen(true)}>Add</button>
      {modelOpen && (
        <Model 
          closeModel={()=>{
            setModelOpen(false);
            setRowToEdit(null);
          }} 
          onSubmit={handleSubmit}
          defaultValue = {rowToEdit !== null && rows[rowToEdit]}
          />
        )}
    </div>
  );
}

export default App;