package com.alexis.demomicroservices.students.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
    private UUID id;
    private String name;
    private String university;
    private String email;
    private Boolean active;
}
