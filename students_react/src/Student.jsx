import React, { useState } from 'react';

export const Student = (props) => {

    const [name, setName] = useState(props.name)
    const [lastName, setLastName] = useState(props.lastName)
    const [age, setAge] = useState(props.age)

    const [isChecked, setIsChecked] = useState(props.present)
    const updateCheck = () => setIsChecked(!isChecked)

    if(isChecked === true){
        const fetchData = async () => {
            const response = await fetch(`http://localhost:8080/student/${props.sid}`, {
                method: "PUT",
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    present: true
                })
            })
        }
        fetchData()
    }else if(isChecked === false){
        const fetchData = async () => {
            const response = await fetch(`http://localhost:8080/student/${props.sid}`, {
                method: "PUT",
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    present: false
                })
            })
        }
        fetchData()
    }

    const editHandler = (e) => {
        e.preventDefault()

        const fetchData = async () => {
            const response = await fetch(`http://localhost:8080/student/${props.sid}`, {
                method: "PUT",
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    age: age,
                    lastName: lastName,
                    name: name
                })
            })
            const student = await response.json()
            const status = await response.status
            console.log(status)
            props.btnEdit(student)
        }
        fetchData()
    }

    const deleteHandler = (e) => {
        e.preventDefault()

        const fetchData = async () => {
            const response = await fetch(`http://localhost:8080/student/${props.sid}`, {
                method: "Delete",
                headers: {'Content-Type': 'application/json'}
            })
            const student = await response.status
            const status = await response.status
            console.log(status)
            props.btnDelete(student)
        }
        fetchData()
    }

    return(
        <div className="Student" >
            <label className="From">First name: </label>
            <input className="From" type="text" id="fname" name="fname" onChange={e => setName(e.target.value)} value={name}></input>
            <label className="From">Last name: </label>
            <input className="From" type="text" id="lname" name="lname" onChange={e => setLastName(e.target.value)} value={lastName}></input>
            <label className="From">Age: </label>
            <input className="From" type="text" id="age" name="age" onChange={e => setAge(e.target.value)} value={age}></input>
            <input className="CheckBox" type="checkbox" checked={isChecked} onChange={updateCheck}></input>
            <label >Present</label>
            <button className="From" onClick={editHandler}>Edit</button>
            <button className="From" onClick={deleteHandler}>Delete</button>
            <button className="From" onClick={props.onClick}>Back</button>
        </div>
    )
}