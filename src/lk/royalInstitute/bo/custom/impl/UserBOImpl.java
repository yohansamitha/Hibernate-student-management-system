package lk.royalInstitute.bo.custom.impl;

import lk.royalInstitute.bo.custom.UserBO;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.custom.UserDAO;
import lk.royalInstitute.dto.UserDTO;
import lk.royalInstitute.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.UserDAOImpl);

    @Override
    public String getUserID() {
        return userDAO.getLastID();
    }

    @Override
    public boolean addUser(UserDTO userDTO) throws RuntimeException {
        return userDAO.add(new User(userDTO.getUserID(), userDTO.getUserName(), userDTO.getPassword()));
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() {
        List<User> all = userDAO.getAll();
        ArrayList<UserDTO> allUsers = new ArrayList<>();
        for (User user : all) {
            allUsers.add(new UserDTO(user.getUserID(), user.getUserName(), user.getPassword()));
        }
        return allUsers;
    }

    @Override
    public boolean UpdateUser(UserDTO userDTO) {
        return userDAO.update(new User(userDTO.getUserID(), userDTO.getUserName(), userDTO.getPassword()));
    }

    @Override
    public boolean deleteUser(String userID) {
        return userDAO.delete(userID);
    }

    @Override
    public boolean getValidateUser(String userName, String password) {
        Object[] user = userDAO.getUser(userName);
        return user[0].equals(userName) && user[1].equals(password);
    }
}
