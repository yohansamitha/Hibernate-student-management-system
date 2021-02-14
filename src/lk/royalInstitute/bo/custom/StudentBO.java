package lk.royalInstitute.bo.custom;

import lk.royalInstitute.bo.SuperBO;
import lk.royalInstitute.dto.StudentDTO;

import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    boolean addStudent(StudentDTO studentDTO);

    String getStudentID();

    ArrayList<StudentDTO> getAllStudent();

    boolean updateStudent(StudentDTO studentDTO);

    boolean deleteStudent(String studentID);
}
