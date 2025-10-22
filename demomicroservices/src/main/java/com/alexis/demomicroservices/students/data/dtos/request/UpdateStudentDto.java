package com.alexis.demomicroservices.students.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentDto {
    private UUID id;
    private String name;
    private String university;
    private String email;
    private Boolean active;
}
