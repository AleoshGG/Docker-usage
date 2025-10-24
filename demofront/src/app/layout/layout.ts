import { Component } from '@angular/core';
import { StudentComponent } from '../features/student/pages/student/student';

@Component({
  selector: 'app-layout',
  imports: [StudentComponent],
  templateUrl: './layout.html',
  styleUrl: './layout.css'
})
export class Layout {

}
