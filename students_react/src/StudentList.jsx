import React from 'react';

export const StudentList = (props) => {

    const name = props.name + " " + props.lastName
    const isChecked = props.present

    const updateCheck = () => {
        saveCheck();
    }

    async function saveCheck() {
        const response = await fetch(`http://localhost:8080/student/${props.sid}`, {
            method: "PUT",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                present: !isChecked
            })  
        })
        const student = await response.json()
        props.savePresent(student)
    }
    
    return(
        <div>
            <div className="Studentx" onClick={props.onClick}>
                <p>{name}</p>
            </div>
            <input className="CheckBox" type="checkbox" checked={isChecked} onChange={updateCheck} />
        </div>
    )
}