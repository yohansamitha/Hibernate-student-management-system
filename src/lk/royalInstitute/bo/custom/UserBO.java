package lk.royalInstitute.bo.custom;

import lk.royalInstitute.bo.SuperBO;
import lk.royalInstitute.dto.UserDTO;

import java.util.ArrayList;

public interface UserBO extends SuperBO {
    String getUserID();

    boolean addUser(UserDTO userDTO) throws RuntimeException;

    ArrayList<UserDTO> getAllUsers();

    boolean UpdateUser(UserDTO userDTO);

    boolean deleteUser(String userID);

    boolean getValidateUser(String userName, String password);
}
