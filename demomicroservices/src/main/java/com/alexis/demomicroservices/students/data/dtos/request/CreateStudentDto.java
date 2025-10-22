package com.alexis.demomicroservices.students.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto {
    private String name;
    private String university;
    private String email;
    private Boolean active;
}
