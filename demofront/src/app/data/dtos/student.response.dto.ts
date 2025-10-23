//import { Student } from "../../domain/entities/Student.entity";

export interface StudentBaseResponseDto<T> {
  success: boolean;
  data: T;
  message: string;
  status: string;
}

/* export type StudentListAllResponse = StudentBaseResponseDto<Student[]>;

// Para un solo estudiante (crear/actualizar)
export type StudentUpsertResponse = StudentBaseResponseDto<Student>;

// Para delete (sin data)
export type StudentDeleteResponse = StudentBaseResponseDto<boolean>;

// Para getAuthor (data con estructura especial)
export type StudentGetAuthorResponse = StudentBaseResponseDto<{ fullName: string }>; */
