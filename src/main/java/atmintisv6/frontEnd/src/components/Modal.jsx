import React, { useState, useEffect } from 'react';
import './Modal.css';
  
export const Modal = (props) => {
  const [formState, setFormState] = useState({
    contactId: props.testRowValue?.contactId || '',
    personId: props.testRowValue?.personId || '',
    name: {
      firstName: props.testRowValue?.name?.firstName || '',
      surname: props.testRowValue?.name?.surname || '',
    },
    phoneNumberList: {
      phoneNumberId: props.testRowValue?.phoneNumberList[0]?.phoneNumberId || '',
      phoneNumber: props.testRowValue?.phoneNumberList[0]?.phoneNumber || '',
    },
    companyList: {
    companyId: props.testRowValue?.companyList[0]?.companyId || '',
    occupation: props.testRowValue?.companyList[0]?.companyName || '',
    },
    emailList: {
    emailId: props.testRowValue?.emailList[0]?.emailId || '',
    email: props.testRowValue?.emailList[0]?.email || '',
    },
  });

  const handleChange = (e) => {

    const { name, value } = e.target;
    setFormState((prevFormState) => {
      const updatedFormState = { ...prevFormState };
      if (name === 'firstName' || name === 'surname') {
        updatedFormState.name = {
          ...prevFormState.name,
          [name]: value,
        };
      }
      if (name === 'phoneNumber') {
        updatedFormState.phoneNumberList = {
          ...prevFormState.phoneNumberList, 
          [name]: value,
        };
      }
      if (name === 'occupation') {
        updatedFormState.companyList = {
          ...prevFormState.companyList, 
          [name]: value,
        };
      }
      if (name === 'email') {
        updatedFormState.emailList = {
          ...prevFormState.emailList, 
          [name]: value,
        };
      }
      return updatedFormState;
    });
  };


  const handleSubmit = (e) => {
    e.preventDefault();
    if(!validateForm()) return;
    props.onSubmit(formState);
    props.closeModal();
  };

  const [errors,setErrors] = useState("");

  const validateForm = () => {
    if (formState.name.firstName) {
        setErrors("");
        return true;
    } else {
        let errorFields = [];
        for(const [key,value] of Object.entries(formState.name))
            if(!value) {
                errorFields.push(key);
            }
            setErrors(errorFields.join(", "));
        return false;
    }
  }

  return (
    <div className='model-container' onClick={(e) => {
        if(e.target.className === "model-container") props.closeModal();
    }}>
        <div className='model'>
            <form>
                <div className='form-group'>
                    <label htmlFor="firstName" className='model-name'>*Vardas</label>
                    <input name="firstName" value={formState?.name?.firstName} onChange={handleChange}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="surname" className='model-surname'>*Pavarde</label>
                    <input name="surname" value={formState?.name?.surname} onChange={handleChange}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="phoneNumber" className='model-phoneNumber'>Telefono Numeris</label>
                    <input name="phoneNumber" value={formState?.phoneNumberList?.phoneNumber} onChange={handleChange}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="email" className='model-email'>Email</label>
                    <input name="email" value={formState?.emailList?.email} onChange={handleChange}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="occupation" className='model-occupation'>Darboviete</label>
                    <input name="occupation" value={formState?.companyList?.occupation} onChange={handleChange}/>
                </div>
                {errors && <div className='error'> {`Neuzpildyti butini laukai: ${errors}`};</div>}
                <button type="submit" className='btn-modal-submit' onClick={handleSubmit}>Submit</button>
            </form>
        </div>
    </div>
  )
}