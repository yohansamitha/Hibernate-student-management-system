package lk.royalInstitute.bo;

import lk.royalInstitute.bo.custom.impl.CourseBOImpl;
import lk.royalInstitute.bo.custom.impl.RegistrationBOImpl;
import lk.royalInstitute.bo.custom.impl.StudentBOImpl;
import lk.royalInstitute.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case StudentBOImpl:
                return (T) new StudentBOImpl();
            case CourseBOImpl:
                return (T) new CourseBOImpl();
            case RegistrationBOImpl:
                return (T) new RegistrationBOImpl();
            case UserBOImpl:
                return (T) new UserBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        StudentBOImpl,
        CourseBOImpl,
        RegistrationBOImpl,
        UserBOImpl,
    }
}
