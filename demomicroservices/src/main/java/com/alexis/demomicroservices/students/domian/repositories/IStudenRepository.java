package com.alexis.demomicroservices.students.domian.repositories;

import com.alexis.demomicroservices.students.domian.entities.Student;

import java.util.List;
import java.util.UUID;

public interface IStudenRepository {
    Student save(Student student);
    List<Student> findAll();
    Boolean deleteById(UUID id);
}
