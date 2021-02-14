package lk.royalInstitute.bo.custom.impl;

import lk.royalInstitute.bo.custom.CourseBO;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.custom.CourseDAO;
import lk.royalInstitute.dto.CourseDTO;
import lk.royalInstitute.entity.Course;
import lk.royalInstitute.entity.Registration;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {
    CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CourseDAOImpl);

    @Override
    public boolean addCourse(CourseDTO courseDTO) throws RuntimeException {
        return courseDAO.add(new Course(courseDTO.getCourse_code(), courseDTO.getCourseName(), courseDTO.getDuration(), courseDTO.getCourseFee(), new ArrayList<Registration>()));
    }

    @Override
    public String getLastID() throws RuntimeException {
        return courseDAO.getLastID();
    }

    @Override
    public ArrayList<CourseDTO> getAllCourse() {
        List<Course> all = courseDAO.getAll();
        ArrayList<CourseDTO> allCourses = new ArrayList<>();
        for (Course course : all) {
            allCourses.add(new CourseDTO(course.getCourse_code(), course.getCourseName(), course.getDuration(), course.getCourseFee()));
        }
        return allCourses;
    }

    @Override
    public boolean deleteCourse(String courseCode) {
        return courseDAO.delete(courseCode);
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) {
        return courseDAO.update(new Course(courseDTO.getCourse_code(), courseDTO.getCourseName(), courseDTO.getDuration(), courseDTO.getCourseFee(), new ArrayList<Registration>()));
    }
}
