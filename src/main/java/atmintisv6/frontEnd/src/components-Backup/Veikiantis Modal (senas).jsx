import React, {useState} from 'react'

import "./Modal.css"

export const Modal = (props) => {
    const[formState,setFormState] = useState(
        props.defaultValue || {
        personId:"",
        name:"",
        phoneNumber:"",
        occupation:"",
        status:"live",
    });

    const handleChange = (e) => {
        setFormState({
        ...formState,
        [e.target.name]:e.target.value
        })
    }

    const [errors,setErrors] = useState("");

    const validateForm = () => {
        if (formState.personId && formState.name && formState.phoneNumber && formState.occupation && formState.status) {
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

    const handleSubmit = (e) => {
        e.preventDefault();
        
        if(!validateForm()) return;
        
        props.onSubmit(formState)

        props.closeModel();
    }

  return (
    <div className='model-container' onClick={(e) => {
        if(e.target.className === "model-container") props.closeModel();
    }}>
        <div className='model'>
            <form>
                <div className='form-group'>
                    <label htmlFor="pId" className='model-pId'>Person ID</label>
                    <input name="pId222" value={formState.personId} onChange={handleChange}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="name" className='model-name'>Vardas</label>
                    <input name="name" value={formState.name} onChange={handleChange}/>
                </div>
                <div className='form-group'>
                    <label htmlFor="phoneNumber" className='model-phoneNumber'>Telefono Numeris</label>
                    <textarea name="phoneNumber" value={formState.phoneNumber} onChange={handleChange}/>
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