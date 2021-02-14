package lk.royalInstitute.bo.custom.impl;

import lk.royalInstitute.bo.custom.StudentBO;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.custom.StudentDAO;
import lk.royalInstitute.dto.StudentDTO;
import lk.royalInstitute.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.StudentDAOImpl);

    @Override
    public boolean addStudent(StudentDTO studentDTO) {
        return studentDAO.add(new Student(studentDTO.getStudent_ID(), studentDTO.getStudentName(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDob(), studentDTO.getGender(), new ArrayList<>()));
    }

    @Override
    public String getStudentID() {
        return studentDAO.getLastID();
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() {
        List<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudentDTOs = new ArrayList<>();
        for (Student student : all) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudent_ID(student.getStudent_ID());
            studentDTO.setStudentName(student.getStudentName());
            studentDTO.setAddress(student.getAddress());
            studentDTO.setContact(student.getContact());
            studentDTO.setDob(student.getDob());
            studentDTO.setGender(student.getGender());
            allStudentDTOs.add(studentDTO);
        }
        return allStudentDTOs;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        return studentDAO.update(new Student(studentDTO.getStudent_ID(), studentDTO.getStudentName(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDob(), studentDTO.getGender(), new ArrayList<>()));
    }

    @Override
    public boolean deleteStudent(String studentID) {
        return studentDAO.delete(studentID);
    }
}
