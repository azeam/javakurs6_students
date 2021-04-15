package com.alexis.students.controller;

import com.alexis.students.dto.StudentDto;
import com.alexis.students.model.request.StudentDetailsRequestModel;
import com.alexis.students.model.response.StudentResponseModel;
import com.alexis.students.service.StudentService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value="/{sid}")
    public ResponseEntity<StudentResponseModel> getStudent(@PathVariable String sid) {
        StudentDto studentDtoIn = new StudentDto();
        studentDtoIn.setSid(sid);

        // pass dto in to service layer
        StudentDto studentDtoOut = studentService.getStudent(studentDtoIn);

        // copy dto out from service layer to response
        StudentResponseModel response = new StudentResponseModel();
        BeanUtils.copyProperties(studentDtoOut, response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<StudentResponseModel[]> getStudents() {
        StudentDto[] studentDtoOut = studentService.getStudents();
        StudentResponseModel[] students = new StudentResponseModel[studentDtoOut.length];
        
        // build array with models from dtos
        int count = 0;
        for (StudentDto studentDto: studentDtoOut) {
            StudentResponseModel response = new StudentResponseModel();
            BeanUtils.copyProperties(studentDto, response);    
            students[count] = response;
            count++;
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentResponseModel> createStudent(@RequestBody StudentDetailsRequestModel studentDetailsModel) {
        // copy json to dto in
        StudentDto studentDtoIn = new StudentDto();
        BeanUtils.copyProperties(studentDetailsModel, studentDtoIn);

        // pass dto in to service layer
        StudentDto studentDtoOut = studentService.createStudent(studentDtoIn);
 
        // copy dto out from service layer to response
        StudentResponseModel response = new StudentResponseModel();
        BeanUtils.copyProperties(studentDtoOut, response);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    } 

    @PutMapping(value="/{sid}")
    public ResponseEntity<StudentResponseModel> updateStudent(@PathVariable String sid, @RequestBody StudentDetailsRequestModel studentDetailsModel) {
        StudentDto studentDtoIn = new StudentDto();
        studentDtoIn.setSid(sid);
        
        // get dto of student with id
        StudentDto studentDtoToEdit = studentService.getStudent(studentDtoIn);

        // pass dto to edit and new data to service
        StudentDto studentDtoOut = studentService.updateStudent(studentDtoToEdit, studentDetailsModel);

        // copy dto out from service layer to response, show updated object
        StudentResponseModel response = new StudentResponseModel();
        BeanUtils.copyProperties(studentDtoOut, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
        
    }

    @DeleteMapping(value="/{sid}")
    public ResponseEntity<String> deleteStudent(@PathVariable String sid) {
        StudentDto studentDtoIn = new StudentDto();
        studentDtoIn.setSid(sid);
        
        // get dto of student with id
        StudentDto studentDtoToEdit = studentService.getStudent(studentDtoIn);

        // pass dto to edit and new data to service
        studentService.deleteStudent(studentDtoToEdit);
        return new ResponseEntity<>("Student deleted", HttpStatus.OK);
    }

}
