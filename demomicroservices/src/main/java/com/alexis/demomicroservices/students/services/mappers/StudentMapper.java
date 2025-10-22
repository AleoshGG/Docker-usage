package com.alexis.demomicroservices.students.services.mappers;

import com.alexis.demomicroservices.students.data.dtos.response.StudentResponseDto;
import com.alexis.demomicroservices.students.data.entities.StudentEntity;
import org.springframework.stereotype.Component;

@Component("studentServiceMapper")
public class StudentMapper {

    public StudentResponseDto toStudentResponseDto(StudentEntity student){
        if(student == null){
            return null;
        }

        StudentResponseDto dto = new StudentResponseDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setUniversity(student.getUniversity());
        dto.setEmail(student.getEmail());
        dto.setActive(student.getActive());
        return dto;
    }
}
