import { Observable } from 'rxjs';
import { Student } from '../entities/Student.entity';

export abstract class IStudentRepository {
  abstract createStudent(student: Student): Observable<Student>;
  abstract listAllStudents(): Observable<Student[]>;
  abstract getAuthor(): Observable<string>;
  abstract updateStudent(student: Student): Observable<Student>;
  abstract deleteStudent(id: string): Observable<boolean>;
}
