import { Component, inject, OnInit } from '@angular/core';
import { StudentForm } from '../../components/student-form/student-form';
import { StudentApiService } from '../../../../data/api/student-api.service';
import { Table } from '../../components/table/table';
import { Student } from '../../../../domain/entities/Student.entity';

@Component({
  selector: 'app-student',
  standalone: true,
  imports: [StudentForm, Table],
  templateUrl: './student.html',
  styleUrl: './student.css',
})
export class StudentComponent implements OnInit {
  private studentService = inject(StudentApiService);

  selectedStudent: Student = {
    id: '',
    name: '',
    university: '',
    email: '',
    active: false,
  };

  name = '';

  ngOnInit(): void {
    this.studentService.getAuthor().subscribe({
      next: (response) => {
        this.name = response;
      },
      error: (err) => {
        console.log(`Ocurrió un error: ${err}`);
      },
    });
  }

  onSelectedStudentEdit(selectedStudent: Student): void {
    this.selectedStudent = selectedStudent;
  }
}
