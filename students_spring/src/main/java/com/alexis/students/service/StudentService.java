package com.alexis.students.service;

import com.alexis.students.dto.StudentDto;
import com.alexis.students.model.request.StudentDetailsRequestModel;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDtoIn); 

    StudentDto getStudent(StudentDto studentDtoIn);

    StudentDto updateStudent(StudentDto studentDtoIn, StudentDetailsRequestModel studentDetailsModel);

    void deleteStudent(StudentDto studentDtoIn);

    StudentDto[] getStudents();
}
