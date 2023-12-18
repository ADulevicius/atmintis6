import React, { useState } from 'react';
import './Modal.css'; // Assuming you have a Modal.css file for styling

export const Modal = (props) => {
  const [formState, setFormState] = useState({
    personId: '',
    name: '',
    phoneNumber: '',
    occupation: '',
    status: 'live',
  });

  const handleChange = (e) => {
    setFormState({
      ...formState,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if(!validateForm) return;
    // Perform any validation if needed
    props.onSubmit(formState);
    props.closeModal();
  };

  const [errors, setErrors] = useState("");

  const validateForm = () => {
    const requiredFields = ["personId", "name", "phoneNumber", "occupation", "status"];
    const errorFields = requiredFields.filter((field) => !formState[field]);

    if (errorFields.length === 0) {
      setErrors("");
      return true;
    } else {
      setErrors(errorFields.join(", "));
      return false;
    }
  };

  // const closeModal = () => {
  //   console.log("closeModal function called");
  //   // Additional logic, if needed, before closing the modal
  //   console.log("closeModal: FORMSTATE:")
  //   console.log(formState)
  //   props.closeModal(); // Ensure this is being called correctly
  // };

  return (
    <div className="modal-container" onClick={props.closeModal}>
      <div className="modal">
        <p>Static content</p>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="personId">Person ID</label>
            <input
              type="text"
              name="personId"
              value={formState.personId}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="name">Name</label>
            <input
              type="text"
              name="name"
              value={formState.name}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="phoneNumber">Phone Number</label>
            <input
              type="text"
              name="phoneNumber"
              value={formState.phoneNumber}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="occupation">Occupation</label>
            <input
              type="text"
              name="occupation"
              value={formState.occupation}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="status">Status</label>
            <select
              name="status"
              value={formState.status}
              onChange={handleChange}
            >
              <option value="live">Live</option>
              <option value="draft">Draft</option>
              <option value="error">Error</option>
            </select>
          </div>
          <button type="submit" className="btn">
            Submit
          </button>
        </form>
        <button className="btn" onClick={props.closeModal}>
          Close
        </button>
      </div>
    </div>
  );
};

export default Modal;
