package lk.royalInstitute.bo.custom;

import lk.royalInstitute.bo.SuperBO;
import lk.royalInstitute.dto.CourseDTO;

import java.util.ArrayList;

public interface CourseBO extends SuperBO {
    boolean addCourse(CourseDTO courseDTO) throws RuntimeException;

    String getLastID() throws RuntimeException;

    ArrayList<CourseDTO> getAllCourse();

    boolean deleteCourse(String courseCode);

    boolean updateCourse(CourseDTO courseDTO);
}
