import React, { useEffect, useState } from 'react'
import { StudentList } from './StudentList'
import { Student } from './Student'
import { CreateStudent } from './CreateStudent'
import './View.css'

export const View = () => {

    const [view, setView] = useState('')
    const [response, setResponse] = useState()
    const [studentList, setStudentList] = useState([])
    const [student, setStudent] = useState()
    
    const URL = "http://localhost:8080/student/"
    
    useEffect(() => {
        const fetchData = async () => { 
            const response = await fetch(URL)
            const data = await response.json()
            setStudentList(data)
        }
        fetchData()
    }, [response, student])

    const studentPage = (students) => {

        // Enters the Student view
        setStudent(students)
        setView('student')
    }

    const CreatePage = () => {

        // Enters the create view
        setView('create')
    }

    const mainPage = (response) => {
        
        // back to ListView
        setResponse(response)
        setView('')
    }

    console.log("x")

    switch(view){
        
        case 'student':
            return(
                <div>
                    <Student name={student.name} lastName={student.lastName} age={student.age} sid={student.sid} present={student.present} btnDelete={mainPage} btnEdit={mainPage} onClick={() => mainPage()}></Student>
                </div>
            );

        case 'create':
            return(
                <div>
                    <CreateStudent btnSave={mainPage} btnBack={mainPage}></CreateStudent>
                </div>
            )

        default:
            return(
                <div>
                    <div className="Header">
                        <h4>Student Names</h4>
                        <h4 className="Test">Present check</h4>
                    </div>
                    <div className="Students">
                        {studentList.map((students) => {
                            return(
                                <div key={students.sid} className="List">
                                    <StudentList name={students.name} lastName={students.lastName} savePresent={mainPage} sid={students.sid} onClick={() => studentPage(students)} present={students.present}/>
                                </div>
                            )
                        })}
                    </div>
                    <button className="Create" onClick={() => CreatePage()}>Create Student</button>
                </div>
            );
    }
}