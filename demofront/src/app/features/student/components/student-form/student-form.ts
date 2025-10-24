import { Component, inject, Input } from '@angular/core';
import { ChangeDetectionStrategy } from '@angular/core';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButton } from '@angular/material/button';
import { MatTooltip } from '@angular/material/tooltip';
import { FormsModule } from '@angular/forms';
import { Student } from '../../../../domain/entities/Student.entity';
import { StudentApiService } from '../../../../data/api/student-api.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-student-form',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButton,
    MatTooltip,
    FormsModule,
  ],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './student-form.html',
  styleUrl: './student-form.css',
})
export class StudentForm {
  private studentService = inject(StudentApiService);

  @Input() student: Student = {
    id: '',
    name: '',
    university: '',
    email: '',
    active: false,
  };

  isSaving = false;

  get isInvalid(): boolean {
    // Es inválido si alguno de estos está vacío
    return this.student.name === '' || this.student.university === '' || this.student.email === '';
  }

  validIntputs() {
    if (this.student.name != '' && this.student.university != '' && this.student.email != '') {
      return false;
    }
    return true;
  }

  onSave(): void {
    if (this.isInvalid) {
      return;
    }
    this.isSaving = true;

    const newStudent: Student = {
      id: this.student.id,
      name: this.student.name,
      university: this.student.university,
      email: this.student.email,
      active: this.student.active,
    };

    let saveOperation: Observable<Student>;
    if (newStudent.id.length > 0) {
      saveOperation = this.studentService.updateStudent(newStudent);
    } else {
      saveOperation = this.studentService.createStudent(newStudent);
    }

    saveOperation.subscribe({
      next: (savedStudent) => {
        console.log('¡Estudiante guardado!', savedStudent);

        this.student = {
          id: '',
          name: '',
          university: '',
          email: '',
          active: false,
        };
      },
      error: (err) => {
        console.error('Error guardando:', err);
        this.isSaving = false;
      },
      complete: () => {
        this.isSaving = false;
      },
    });
  }
}
