import { Component } from '@angular/core';
import { Student } from '../features/student/pages/student/student';

@Component({
  selector: 'app-layout',
  imports: [Student],
  templateUrl: './layout.html',
  styleUrl: './layout.css'
})
export class Layout {

}
