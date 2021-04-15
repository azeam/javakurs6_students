import React from 'react';

export const StudentList = (props) => {

    const name = props.name + " " + props.lastName

    return(
        <div className="Studentx" onClick={props.onClick}>
            <p>{name}</p>
        </div>
    )
}