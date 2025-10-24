import { Component, ViewChild, AfterViewInit, inject, EventEmitter, Output } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';

import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { StudentApiService } from '../../../../data/api/student-api.service';
import { Student } from '../../../../domain/entities/Student.entity';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-table',
  imports: [
    MatProgressSpinnerModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
  ],
  templateUrl: './table.html',
  styleUrl: './table.css',
})
export class Table implements AfterViewInit {
  private studentService = inject(StudentApiService);
  @Output() saveSuccess = new EventEmitter<Student>();

  displayedColumns: string[] = ['Nombre', 'Universidad', 'Email', 'Estado', 'Eliminar', 'Editar'];
  dataSource = new MatTableDataSource<Student>();

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  ngAfterViewInit() {
    this.studentService.listAllStudents().subscribe((data) => {
      this.isLoadingResults = false;
      this.isRateLimitReached = data === null;

      if (data) {
        this.dataSource.data = data;
        this.resultsLength = data.length;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    });
  }

  onEdit(id: string): void {
    this.dataSource.data.forEach((student) => {
      if (student.id === id) {
        this.saveSuccess.emit(student);
      }
    });
  }

  onDelete(id: string) {
    this.studentService.deleteStudent(id).subscribe({
      next: (res) => {
        if (res) {
          alert(`El alumno ${id} fue eliminado`);
          this.dataSource.data = this.dataSource.data.filter((student) => student.id !== id);
        }
      },
      error: (err) => {
        alert(`Ocurrio un error`);
        console.error(err);
      },
    });
  }
}
