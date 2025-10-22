package com.alexis.demomicroservices.students.services.impl;

import com.alexis.demomicroservices.students.data.dtos.request.CreateStudentDto;
import com.alexis.demomicroservices.students.data.dtos.request.UpdateStudentDto;
import com.alexis.demomicroservices.students.data.dtos.response.StudentResponseDto;
import com.alexis.demomicroservices.students.data.entities.StudentEntity;
import com.alexis.demomicroservices.students.data.repositories.StudentRepository;
import com.alexis.demomicroservices.students.services.IStudentService;
import com.alexis.demomicroservices.students.services.mappers.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(
            @org.springframework.beans.factory.annotation.Qualifier("studentServiceMapper") StudentMapper studentMapper,
            StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponseDto createStudent(CreateStudentDto requestDto) {
        StudentEntity newStudent = new StudentEntity();
        newStudent.setName(requestDto.getName());
        newStudent.setUniversity(requestDto.getUniversity());
        newStudent.setEmail(requestDto.getEmail());
        newStudent.setActive(requestDto.getActive());

        StudentEntity savedStudent = studentRepository.save(newStudent);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDto updateStudent(UpdateStudentDto upDto) {
        StudentEntity student = new StudentEntity();
        student.setId(upDto.getId());
        student.setName(upDto.getName());
        student.setUniversity(upDto.getUniversity());
        student.setEmail(upDto.getEmail());
        student.setActive(upDto.getActive());

        StudentEntity savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    @Override
    public Void delete(UUID studentId) {
        studentRepository.deleteById(studentId);
        return null;
    }


}
