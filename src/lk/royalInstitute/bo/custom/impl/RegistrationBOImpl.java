package lk.royalInstitute.bo.custom.impl;

import lk.royalInstitute.bo.custom.RegistrationBO;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.custom.CourseDAO;
import lk.royalInstitute.dao.custom.RegistrationDAO;
import lk.royalInstitute.dao.custom.StudentDAO;
import lk.royalInstitute.dto.RegistrationDTO;
import lk.royalInstitute.entity.Course;
import lk.royalInstitute.entity.Registration;
import lk.royalInstitute.entity.Student;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;

public class RegistrationBOImpl implements RegistrationBO {
    StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.StudentDAOImpl);
    CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CourseDAOImpl);
    RegistrationDAO registrationDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RegistrationDAOImpl);

    @Override
    public String getRegistrationID() {
        return registrationDAO.getLastID();
    }

    @Override
    public boolean placeRegistration(RegistrationDTO registrationDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Student student = session.load(Student.class, registrationDTO.getStudent_ID());
        Course course = session.load(Course.class, registrationDTO.getCourse_code());
        session.close();

        Registration registration = new Registration();
        registration.setRegNo(registrationDTO.getRegNo());
        registration.setRegDate(registrationDTO.getRegDate());
        registration.setRegFee(registrationDTO.getRegFee());
        registration.setStudent(student);
        registration.setCourse(course);
        return registrationDAO.add(registration);
    }
}
