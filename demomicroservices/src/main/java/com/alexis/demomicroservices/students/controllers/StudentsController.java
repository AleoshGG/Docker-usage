package com.alexis.demomicroservices.students.controllers;

import com.alexis.demomicroservices.core.dtos.response.BaseResponse;
import com.alexis.demomicroservices.students.data.dtos.request.CreateStudentDto;
import com.alexis.demomicroservices.students.data.dtos.request.UpdateStudentDto;
import com.alexis.demomicroservices.students.data.dtos.response.AuthorResponseDto;
import com.alexis.demomicroservices.students.data.dtos.response.StudentResponseDto;
import com.alexis.demomicroservices.students.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentsController {
    private final IStudentService studentService;

    @Autowired
    public StudentsController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<StudentResponseDto>> createStudent(
            @RequestBody CreateStudentDto createStudentDto
    ) {
        StudentResponseDto student = studentService.createStudent(createStudentDto);

        BaseResponse<StudentResponseDto> res = new BaseResponse<>(
                true, student, "Estudiante guardado exitosamente", HttpStatus.CREATED
        );

        return res.buildResponseEntity();
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<StudentResponseDto>>> getAllStudents() {
        List<StudentResponseDto> students = studentService.getAllStudents();

        BaseResponse<List<StudentResponseDto>> res = new BaseResponse<>(
                true, students, "Recuperación exitosa", HttpStatus.OK
        );

        return res.buildResponseEntity();
    }

    @GetMapping(path = "/guzman")
    public ResponseEntity<BaseResponse<AuthorResponseDto>> getAuthor() {
        BaseResponse<AuthorResponseDto> res = new BaseResponse<>(
                true, new AuthorResponseDto(), "Recuperación exitosa", HttpStatus.OK
        );
        return res.buildResponseEntity();
    }

    @PutMapping
    public ResponseEntity<BaseResponse<StudentResponseDto>> updateStudent(
            @RequestBody UpdateStudentDto updateStudentDto
    ) {
        StudentResponseDto student = studentService.updateStudent(updateStudentDto);

        BaseResponse<StudentResponseDto> res = new BaseResponse<>(
                true, student, "Datos actualizados exitosamente", HttpStatus.OK
        );

        return res.buildResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<StudentResponseDto>> deleteStudent(
            @RequestParam UUID id
    ) {
        studentService.delete(id);
        BaseResponse<StudentResponseDto> res = new BaseResponse<>(
                true, null, "Estudiante eliminado exitosamente", HttpStatus.OK
        );

        return res.buildResponseEntity();
    }

}
