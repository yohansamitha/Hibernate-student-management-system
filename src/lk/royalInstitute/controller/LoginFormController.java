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
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFormController {
    public AnchorPane root;
    public JFXPasswordField pwdPassword;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public JFXRadioButton rdoShow;
    UserBO userBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.UserBOImpl);

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {


        try {

            if (!txtUserName.getText().isEmpty()) {
                String userName = txtUserName.getText();
                String tempPassword = null;

                if (pwdPassword.isVisible()) {
                    tempPassword = pwdPassword.getText();
                } else if (txtPassword.isVisible()) {
                    tempPassword = txtPassword.getText();
                }
                String reversed = new StringBuilder(tempPassword).reverse().toString();
                byte[] bytes = reversed.getBytes();
                String password = Base64.getEncoder().encodeToString(bytes);
                if (userBO.getValidateUser(userName, password)) {
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/DashBoardForm.fxml"))));
                    stage.centerOnScreen();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please check your username!");
                    alert.showAndWait();
                    txtPassword.clear();
                    txtPassword.setVisible(false);
                    pwdPassword.clear();
                    pwdPassword.setVisible(true);
                    pwdPassword.requestFocus();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please enter username!");
                alert.showAndWait();
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("something went wrong!");
            alert.showAndWait();
        }
    }

    public boolean checkRegEx(String pattern, String text) {
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(text);
        return matcher.matches();
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
