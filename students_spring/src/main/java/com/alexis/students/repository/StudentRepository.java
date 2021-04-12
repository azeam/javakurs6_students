package com.alexis.students.repository;

import java.util.Optional;

import com.alexis.students.entity.StudentEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {

    Optional<StudentEntity> findBySid(String sid);

}
