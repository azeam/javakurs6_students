package com.alexis.students.service.impl;

import java.util.Optional;
import java.util.stream.StreamSupport;

import com.alexis.students.dto.StudentDto;
import com.alexis.students.entity.StudentEntity;
import com.alexis.students.model.request.StudentDetailsRequestModel;
import com.alexis.students.repository.StudentRepository;
import com.alexis.students.service.StudentService;
import com.alexis.students.service.util.Encryption;
import com.alexis.students.service.util.Filter;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public StudentDto getStudent(StudentDto studentDtoIn) {
        Optional<StudentEntity> studentFound = studentRepository.findBySid(studentDtoIn.getSid());
        
        if (!studentFound.isPresent()) {
            throw new ResponseStatusException (
                HttpStatus.NOT_FOUND, "Student not found"
            );
        }
        
        StudentDto studentDtoOut = new StudentDto();
        BeanUtils.copyProperties(studentFound.get(), studentDtoOut);
        return studentDtoOut;
    }

    @Override
    public void deleteStudent(StudentDto studentDtoIn) {
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(studentDtoIn, studentEntity);
        studentRepository.delete(studentEntity); // 404 will be thrown earlier if not found
    }

    @Override
    public StudentDto createStudent(StudentDto studentDtoIn) {
        // name needs to be set, otherwise return 400
        if (studentDtoIn.getName() == null) {
            throw new ResponseStatusException (
                HttpStatus.BAD_REQUEST, "No student name set"
            );
        }

        // set generated sid to entity and save to db
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(studentDtoIn, studentEntity);
        Encryption enc = new Encryption();
        
        studentEntity.setSid(enc.generateHash(studentDtoIn.getName()));
        StudentEntity studentEntityOut = studentRepository.save(studentEntity);

        // return response as dto
        StudentDto studentDtoOut = new StudentDto();
        BeanUtils.copyProperties(studentEntityOut, studentDtoOut);
        return studentDtoOut;
    }

    @Override
    public StudentDto[] getStudents() {
        Iterable<StudentEntity> studentsFound = studentRepository.findAll();
        StudentDto[] studentsArray = new StudentDto[(int) StreamSupport.stream(studentsFound.spliterator(), false).count()];
        
        // build dto array from iterable
        int count = 0;
        for (StudentEntity studentEntity : studentsFound) {
            StudentDto studentDtoOut = new StudentDto();
            BeanUtils.copyProperties(studentEntity, studentDtoOut);
            studentsArray[count] = studentDtoOut;
            count++;
        }
        return studentsArray;
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDtoIn, StudentDetailsRequestModel studentDetailsModel) {
        StudentEntity studentEntity = new StudentEntity();
        // set old data to entity
        BeanUtils.copyProperties(studentDtoIn, studentEntity);

        // set new data where not null (only fields that should be updated)
        Filter filter = new Filter();
        BeanUtils.copyProperties(studentDetailsModel, studentEntity, filter.getNullPropertyNames(studentDetailsModel));

        // save new data
        StudentEntity studentEntityOut = studentRepository.save(studentEntity);

        // return response as dto
        StudentDto studentDtoOut = new StudentDto();
        BeanUtils.copyProperties(studentEntityOut, studentDtoOut);
        return studentDtoOut;
    }

    

}
