import React, { useState, useEffect } from 'react';
import './Modal.css';

export const Modal = (props) => {
  console.log('Iejo i Modal: ', props);
  console.log('testRowValue.personId: ',props.testRowValue.personId)

  const [formState, setFormState] = useState({
    personId: '',
    name: '',
    phoneNumber: '',
    occupation: '',
    status: 'live',
  });

  const [formState2, setFormState2] = useState(props.testRowValue);
  console.log('testRowValue.personId: ',formState2.personId)

  const [formState3, setFormState3] = useState({
    personId: props.testRowValue.personId,
    names: {
      firstName: props.testRowValue.names?.firstName,
      lastName: props.testRowValue.names?.lastName
    },
    phoneNumber: props.testRowValue.phoneNumbersList[0]?.phoneNumber,
    occupation: '',
    status: 'live',
    // other properties
  });



  useEffect(() => {
    // Update form state when defaultValue prop changes
    setFormState((prevFormState) => ({
      ...prevFormState,
      ...props.defaultValue,
    }));
  }, [props.defaultValue]);

  const handleChange = (e) => {
    console.log(e);
    setFormState3((prevFormState) => ({
      ...prevFormState,
      names: {
        ...prevFormState.names,
        [e.target.name]: e.target.value,
      },
      phoneNumber: e.target.name === 'phoneNumber' ? e.target.value : formState3.phoneNumber,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if(!validateForm()) return;
    props.onSubmit(formState2);
    props.closeModal();
  };

    const [errors,setErrors] = useState("");

    const validateForm = () => {
        if ( formState.name && formState.phoneNumber && formState.occupation && formState.status) {
            //formState.personId &&
            setErrors("");
            return true;
        } else {
            let errorFields = [];
            for(const [key,value] of Object.entries(formState))
                if(!value) {
                    errorFields.push(key)
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
                    <label htmlFor="pId" className='model-pId'>Person ID</label>
                    <textarea name="pId" value={formState3.personId} onChange={handleChange}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="firstName" className='model-name'>Vardas</label>
                    <input name="firstName" value={formState3?.names?.firstName} onChange={handleChange}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="phoneNumber" className='model-phoneNumber'>Telefono Numeris</label>
                    <input name="phoneNumber" value={formState3?.phoneNumber} onChange={handleChange}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="occupation" className='model-occupation'>Darboviete</label>
                    <textarea name="occupation" value={formState.occupation} onChange={handleChange}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="status" className='model-status'>Status</label>
                    <select name="status" value={formState.status} onChange={handleChange}>
                        <option value="live">Live</option>
                        <option value="draft">Draft</option>
                        <option value="error">Error</option>
                    </select>
                </div>
                {errors && <div className='error'> {`Please include: ${errors}`};</div>}
                <button type="submit" className='btn' onClick={handleSubmit}>Submit</button>
            </form>
        </div>
    </div>
  )
}
