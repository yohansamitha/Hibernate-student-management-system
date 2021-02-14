package lk.royalInstitute.dao;

import lk.royalInstitute.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case StudentDAOImpl:
                return (T) new StudentDAOImpl();
            case CourseDAOImpl:
                return (T) new CourseDAOImpl();
            case RegistrationDAOImpl:
                return (T) new RegistrationDAOImpl();
            case UserDAOImpl:
                return (T) new UserDAOImpl();
            case QueryDAOImpl:
                return (T) new QueryDAOImpl();
        }
        return null;
    }

    public enum DAOTypes {
        StudentDAOImpl,
        CourseDAOImpl,
        RegistrationDAOImpl,
        UserDAOImpl,
        QueryDAOImpl
    }
}
