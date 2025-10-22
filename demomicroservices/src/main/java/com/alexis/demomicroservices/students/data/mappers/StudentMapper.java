package com.alexis.demomicroservices.students.data.mappers;

import com.alexis.demomicroservices.students.data.entities.StudentEntity;
import com.alexis.demomicroservices.students.domian.entities.Student;
import org.springframework.stereotype.Component;

@Component("studentDataMapper")
public class StudentMapper {
    public Student toDomain(StudentEntity entity) {
        if (entity == null) {
            return null;
        }

        return Student.builder()
                .id(entity.getId())
                .name(entity.getName())
                .university(entity.getUniversity())
                .email(entity.getEmail())
                .active(entity.getActive())
                .build();
    }

    public StudentEntity toEntity(Student domain) {
        if (domain == null) {
            return null;
        }

        StudentEntity entity = new StudentEntity();
        entity.setName(domain.getName());
        entity.setUniversity(domain.getUniversity());
        entity.setEmail(domain.getEmail());
        entity.setActive(domain.getActive());
        return entity;
    }
}
