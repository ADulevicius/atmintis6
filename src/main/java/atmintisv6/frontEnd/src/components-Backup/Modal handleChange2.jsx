import React, { useState, useEffect } from 'react';
import './Modal.css';

export const Modal = (props) => {
  console.log('Iejo i Modal: ', props);
  //console.log('testRowValue.personId: ',props.testRowValue.personId)


  const [formStateTest, setFormStateTest] = useState(props.testRowValue);

  const [formState, setFormState] = useState({
    personId: props.testRowValue.personId,
    names: {
      firstName: props.testRowValue.names?.firstName,
      lastName: props.testRowValue.names?.lastName
    },
    phoneNumber: props.testRowValue.phoneNumbersList[0]?.phoneNumber,
    occupation: props.testRowValue.companiesList[0]?.companyName,

    // other properties
  });
  //    status: 'live',



  useEffect(() => {
    // Update form state when testRowValue prop changes
    setFormState((prevFormState) => ({
      ...prevFormState,
      ...props.testRowValue,
    }));
  }, [props.testRowValue]);

  
  const handleChange2 = (e) => {
    console.log("NEW HANGLECHANGE e:", e);
    setFormStateTest((prevFormState) => ({
      ...prevFormState,
      [props.testRowValue.names.firstName]: e.target.value, 
       
    }));
    console.log("handleCHANGE2:formState", formStateTest);
  }

  //PERRASYK SITA kad tiesiai pakeitimus darytu
  const handleChange = (e) => {
    console.log("HANDLECHANGE e: ", e);
    setFormState((prevFormState) => ({
      ...prevFormState,
      names: {
        ...prevFormState.names,
        [e.target.name]: e.target.value,
      },
      phoneNumber: e.target.name === 'phoneNumber' ? e.target.value : prevFormState.phoneNumber,
      occupation: e.target.name === 'occupation' ? e.target.value : prevFormState.occupation,    
    }));
    console.log("HANDLECHANGE: formState:", formState);
  };




  const handleSubmit = (e) => {
    e.preventDefault();
    //if(!validateForm()) return;
    props.onSubmit(formState);
    props.closeModal();
  };

//    const [errors,setErrors] = useState("");

/*     const validateForm = () => {
        if ( formState.name && formState.phoneNumber && formState.occupation) {
            //formState.personId &&
            // && formState.status
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
    } */

  return (
    <div className='model-container' onClick={(e) => {
        if(e.target.className === "model-container") props.closeModal();
    }}>
        <div className='model'>
            <form>
                <div className='form-group'>
                    <label htmlFor="pId" className='model-pId'>Person ID</label>
                    <textarea name="pId" value={formState.personId} onChange={handleChange}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="firstName" className='model-name'>Vardas</label>
                    <input name="firstName" value={formState?.names?.firstName} onChange={handleChange2}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="phoneNumber" className='model-phoneNumber'>Telefono Numeris</label>
                    <input name="phoneNumber" value={formState?.phoneNumber} onChange={handleChange}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="occupation" className='model-occupation'>Darboviete</label>
                    <textarea name="occupation" value={formState.occupation} onChange={handleChange}/>
                </div>
                {/* <div className='form-group'>
                    <label htmlFor="status" className='model-status'>Status</label>
                    <select name="status" value={formState.status} onChange={handleChange}>
                        <option value="live">Live</option>
                        <option value="draft">Draft</option>
                        <option value="error">Error</option>
                    </select>
                </div> */}
                {/* {errors && <div className='error'> {`Please include: ${errors}`};</div>} */}
                <button type="submit" className='btn' onClick={handleSubmit}>Submit</button>
            </form>
        </div>
    </div>
  )
}
