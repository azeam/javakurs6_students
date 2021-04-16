import React, { useState } from 'react';

export const CreateStudent = (props) => {

    const [name, setName] = useState('');
    const [lastName, setLastName] = useState('');
    const [age, setAge] = useState('');
    
    const [isChecked, setIsChecked] = useState(false)
    const updateCheck = () => setIsChecked(!isChecked)

    const submitHandler = (e) => {
        e.preventDefault();

        const fetchData = async () => {
            const response = await fetch('http://localhost:8080/student/', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    age: age,
                    lastName: lastName,
                    name: name,
                    present: isChecked
                })
            })
            const student = await response.json()
            props.btnSave(student)
        }
        fetchData()
    }

    return(
        <form className="Student" onSubmit={submitHandler}>
            <label className="From">First name: </label>
            <input className="From" type="text" id="fname" name="fname" placeholder="First name..." onChange={e => setName(e.target.value)} value={name}></input>
            <label className="From">Last name: </label>
            <input className="From" type="text" id="lname" name="lname" placeholder="Last name..." onChange={e => setLastName(e.target.value)} value={lastName}></input>
            <label className="From">Age: </label>
            <input className="From" type="text" id="age" name="age" placeholder="Age..." onChange={e => setAge(e.target.value)} value={age}></input>
            <input type="checkbox" checked={isChecked} onChange={updateCheck}></input>
            <label>Present</label>
            <button className="From" type="submit">Save</button>
            <button className="From" onClick={props.btnBack}>Back</button>
        </form>
    )
}