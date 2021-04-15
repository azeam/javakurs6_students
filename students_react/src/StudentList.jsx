import React, { useState } from 'react';

export const StudentList = (props) => {

    const name = props.name + " " + props.lastName

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
            const check = await response.status
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
            const check = await response.status
        }
        fetchData()
    }
    

    return(
        <div>
            <div className="Studentx" onClick={props.onClick}>
                <p>{name}</p>
            </div>
            <input className="CheckBox" type="checkbox" checked={isChecked} onChange={updateCheck}></input>
        </div>
    )
}