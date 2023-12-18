import React from 'react';

import {BsFillTrashFill, BsFillPencilFill} from "react-icons/bs";

import "./Table.css";

export const Table = ({ rows, deleteRow, editRow }) => {
    return <div className="table-wrapper">
            <table className="table">
                <thead>
                    <tr>
                        <th>Person ID</th>
                        <th>Vardas</th>
                        <th className='expand'>Telefono Numeriai</th>
                        <th>Darbovietes</th>
                        <th>Statusas</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        
                        rows.map((row,idx) => {
                            const statusText = row.status.charAt(0).toUpperCase() + row.status.slice(1);
                            return <tr key={idx}>
                                <td>{row.personId}</td>
                                <td>{row.names.firstName}</td>
                                <td className='expand'>{row.phoneNumber}</td>
                                <td>{row.occupation}</td>
                                <td>
                                    <span className={`label label-${row.status}`}>{statusText}</span>
                                </td>

                                <td>
                                    <span className="actions">
                                    <BsFillTrashFill className="delete-btn" onClick={()=> deleteRow(idx)}/>
                                    <BsFillPencilFill onClick={() => editRow(idx)}/>
                                    </span>
                                </td>
                            </tr>
                        })
                    }
                </tbody>
            </table>           
        </div>
}