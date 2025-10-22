package com.alexis.demomicroservices.students.data.repositories;

import com.alexis.demomicroservices.students.data.entities.StudentEntity;
import com.alexis.demomicroservices.students.data.mappers.StudentMapper;
import com.alexis.demomicroservices.students.domian.entities.Student;
import com.alexis.demomicroservices.students.domian.repositories.IStudenRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class StudentRepositoryAdapter implements IStudenRepository {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentRepositoryAdapter(
            StudentRepository studentRepository,
            @org.springframework.beans.factory.annotation.Qualifier("studentDataMapper") StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public Student save(Student student) {
        StudentEntity studentEntity = studentMapper.toEntity(student);
        StudentEntity savedEntity = studentRepository.save(studentEntity);
        return studentMapper.toDomain(savedEntity);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteById(UUID id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }
}
