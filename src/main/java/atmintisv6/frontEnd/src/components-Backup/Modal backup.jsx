import React, { useState, useEffect} from 'react';
import './Modal.css';

export const Modal = (props) => {
  const [formState, setFormState] = useState(
    props.defaultValue || {
      personId: "",
      name: "",
      phoneNumber: "",
      occupation: "",
      status: "live",
    }
  );

  // useEffect(() => {
  //   console.log('Modal component rendered');
  //   return () => {
  //     console.log('Modal component unmounted');
  //   };
  // }, []);

  console.log('Modal rendering with props:', props);

  const handleChange = (e) => {
    setFormState({
      ...formState,
      [e.target.name]: e.target.value,
    });
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

  const closeModal = () => {
    console.log("closeModal function called");
    // Additional logic, if needed, before closing the modal
    console.log("closeModal: FORMSTATE:")
    console.log(formState)
    props.closeModal(); // Ensure this is being called correctly
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!validateForm()) return;
    console.log("handleSubmit: FORMSTATE:")
    console.log(formState)
    props.onSubmit(formState);
    closeModal(); // Close the modal after submitting
  };

  return (
    <div
      className="modal-container"
      onClick={(e) => {
        if (e.target.className === "modal-container") closeModal();
      }}
    >
      <div className="modal">
        <p>Static content</p>
        {/* <form>
          <div className="form-group">
            <label htmlFor="personId" className="modal-personId">
              Person ID
            </label>
            <input name="personId" value={formState.personId} onChange={handleChange} />
          </div>
          <div className="form-group">
            <label htmlFor="name" className="modal-name">
              Vardas
            </label>
            <input name="name" value={formState.name} onChange={handleChange} />
          </div>
          <div className="form-group">
            <label htmlFor="phoneNumber" className="modal-phoneNumber">
              Telefono Numeris
            </label>
            <input name="phoneNumber" value={formState.phoneNumber} onChange={handleChange} />
          </div>
          <div className="form-group">
            <label htmlFor="occupation" className="modal-occupation">
              Darboviete
            </label>
            <input name="occupation" value={formState.occupation} onChange={handleChange} />
          </div>
          <div className="form-group">
            <label htmlFor="status" className="modal-status">
              Status
            </label>
            <select name="status" value={formState.status} onChange={handleChange}>
              <option value="live">Live</option>
              <option value="draft">Draft</option>
              <option value="error">Error</option>
            </select>
          </div>
          {errors && <div className="error"> {`Please include: ${errors}`}</div>}
          <button type="submit" className="btn" onClick={handleSubmit}>
            Submit
          </button>
        </form> */}
      </div>
    </div>
  );
};
