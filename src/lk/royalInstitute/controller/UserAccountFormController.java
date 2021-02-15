package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.royalInstitute.bo.BOFactory;
import lk.royalInstitute.bo.custom.UserBO;
import lk.royalInstitute.dto.UserDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;

public class UserAccountFormController implements Initializable {
    public Label lblUserID;
    public JFXTextField txtUserName;
    public JFXPasswordField pwdNewPassword;
    public JFXPasswordField pwdReEnterPassword;
    public JFXButton btnRegister;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public JFXButton btnCancel;
    public TableView tblStudent;
    public TableColumn colStudentID;
    public TableColumn colFullName;
    public TableColumn colAddress;
    UserBO userBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.UserBOImpl);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("password"));
        loadUserID();
        loadAllUsers();
    }

    private void loadUserID() {
        try {
            String studentID = userBO.getUserID();
            int newId = Integer.parseInt(studentID.substring(1)) + 1;
            if (newId < 10) {
                lblUserID.setText("U00" + newId);
            } else if (newId < 100) {
                lblUserID.setText("U0" + newId);
            } else {
                lblUserID.setText("U" + newId);
            }
        } catch (NullPointerException e) {
            lblUserID.setText("U001");
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        try {
            if (pwdNewPassword.getText().equals(pwdReEnterPassword.getText())) {
                String reversed = new StringBuilder(pwdNewPassword.getText()).reverse().toString();
                byte[] bytes = reversed.getBytes();
                String password = Base64.getEncoder().encodeToString(bytes);
//                String password = pwdNewPassword.getText();
                UserDTO userDTO = new UserDTO(lblUserID.getText(), txtUserName.getText(), password);
                boolean b = userBO.addUser(userDTO);
                System.out.println(b);
                if (b) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("operation is success!");
                    alert.showAndWait();
                    clear();
                    loadUserID();
                    loadAllUsers();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("something went wrong!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("wrong password!");
                alert.showAndWait();
            }
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("something went wrong!");
            alert.showAndWait();
        }
    }

    private void clear() {
        txtUserName.clear();
        pwdNewPassword.clear();
        pwdReEnterPassword.clear();
        loadUserID();
        loadAllUsers();
    }

    private void loadAllUsers() {
        ArrayList<UserDTO> allUsers = userBO.getAllUsers();
        tblStudent.setItems(FXCollections.observableArrayList(allUsers));
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String userID = lblUserID.getText();
        boolean b = userBO.deleteUser(userID);
        if (b) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("operation is success!");
            alert.showAndWait();
            clear();
            loadUserID();
            loadAllUsers();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("something went wrong!");
            alert.showAndWait();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            if (pwdNewPassword.getText().equals(pwdReEnterPassword.getText())) {
                String reversed = new StringBuilder(pwdNewPassword.getText()).reverse().toString();
                byte[] bytes = reversed.getBytes();
                String password = Base64.getEncoder().encodeToString(bytes);
//                String password = pwdNewPassword.getText();
                UserDTO userDTO = new UserDTO(lblUserID.getText(), txtUserName.getText(), password);
                boolean b = userBO.UpdateUser(userDTO);
                System.out.println(b);
                if (b) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("operation is success!");
                    alert.showAndWait();
                    clear();
                    loadUserID();
                    loadAllUsers();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("something went wrong!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("wrong password!");
                alert.showAndWait();
            }
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("something went wrong!");
            alert.showAndWait();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        clear();
        loadAllUsers();
        loadUserID();
    }

    public void tblStudentOnReleased(MouseEvent mouseEvent) {
        UserDTO selectedItem = (UserDTO) tblStudent.getSelectionModel().getSelectedItem();
        lblUserID.setText(selectedItem.getUserID());
        txtUserName.setText(selectedItem.getUserName());
    }
}
