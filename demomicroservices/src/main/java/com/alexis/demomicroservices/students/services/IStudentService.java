package com.alexis.demomicroservices.students.services;

import com.alexis.demomicroservices.students.data.dtos.request.CreateStudentDto;
import com.alexis.demomicroservices.students.data.dtos.request.UpdateStudentDto;
import com.alexis.demomicroservices.students.data.dtos.response.StudentResponseDto;

import java.util.List;
import java.util.UUID;

public interface IStudentService {
    StudentResponseDto createStudent(CreateStudentDto createStudentDto);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto updateStudent(UpdateStudentDto updateStudentDto);
    Void delete(UUID studentId);
}
