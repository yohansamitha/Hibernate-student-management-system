package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.royalInstitute.bo.BOFactory;
import lk.royalInstitute.bo.custom.UserBO;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane root;
    public JFXPasswordField pwdPassword;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public JFXRadioButton rdoShow;
    UserBO userBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.UserBOImpl);

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String userName = txtUserName.getText();
        String password = null;

        if (pwdPassword.isVisible()) {
            password = pwdPassword.getText();
        } else if (txtPassword.isVisible()) {
            password = txtPassword.getText();
        }

        try {

//        if (userName.equals("admin") && password.equals("1234")) {
            if (userBO.getValidateUser(userName, password)) {
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/DashBoardForm.fxml"))));
                stage.centerOnScreen();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("something went wrong!");
                alert.showAndWait();
                txtPassword.clear();
                txtPassword.setVisible(false);
                pwdPassword.clear();
                pwdPassword.setVisible(true);
                pwdPassword.requestFocus();
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("something went wrong!");
            alert.showAndWait();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        txtUserName.clear();
        txtUserName.requestFocus();
        txtPassword.clear();
        txtPassword.setVisible(false);
        pwdPassword.clear();
        pwdPassword.setVisible(true);
    }

    public void rdoShowButtonOnAction(ActionEvent actionEvent) {
        if (rdoShow.isSelected()) {
            String coverPassword = pwdPassword.getText();
            pwdPassword.clear();
            pwdPassword.setVisible(false);
            txtPassword.setVisible(true);
            txtPassword.setText(coverPassword);
            txtPassword.requestFocus();
            txtPassword.end();
        } else {
            String clearPassword = txtPassword.getText();
            txtPassword.clear();
            txtPassword.setVisible(false);
            pwdPassword.setVisible(true);
            pwdPassword.setText(clearPassword);
            pwdPassword.requestFocus();
            pwdPassword.end();
        }
    }
}
