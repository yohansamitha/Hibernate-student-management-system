package lk.royalInstitute.dao.custom;

import lk.royalInstitute.dao.CrudDAO;
import lk.royalInstitute.entity.User;

public interface UserDAO extends CrudDAO<User, String> {
    String getLastID();

    Object[] getUser(String userName);
}
