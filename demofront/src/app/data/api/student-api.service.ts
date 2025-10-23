import { inject, Injectable } from '@angular/core';
import { IStudentRepository } from '../../domain/repositories/IStudent.repository';
import { map, Observable } from 'rxjs';
import { Student } from '../../domain/entities/Student.entity';
import { HttpClient } from '@angular/common/http';
import { StudentBaseResponseDto } from '../dtos/student.response.dto';

@Injectable({ providedIn: 'root' })
export class StudentApiService extends IStudentRepository {
  private http = inject(HttpClient);
  private url = 'http://localhost:8080';

  constructor() {
    super();
  }

  override createStudent(student: Student): Observable<Student> {
    return this.http
      .post<StudentBaseResponseDto<Student>>(`${this.url}/students`, student)
      .pipe(map((res) => res.data));
  }

  override listAllStudents(): Observable<Student[]> {
    return this.http
      .get<StudentBaseResponseDto<Student[]>>(`${this.url}/students`)
      .pipe(map((res) => res.data));
  }

  override getAuthor(): Observable<string> {
    return this.http
      .get<StudentBaseResponseDto<{ fullName: string }>>(`${this.url}/students/guzman`)
      .pipe(map((res) => res.data.fullName));
  }

  override updateStudent(student: Student): Observable<Student> {
    return this.http
      .post<StudentBaseResponseDto<Student>>(`${this.url}/students`, student)
      .pipe(map((res) => res.data));
  }

  override deleteStudent(id: string): Observable<boolean> {
    return this.http
      .delete<StudentBaseResponseDto<boolean>>(`${this.url}/student?id=${id}`)
      .pipe(map((res) => res.success));
  }
}
