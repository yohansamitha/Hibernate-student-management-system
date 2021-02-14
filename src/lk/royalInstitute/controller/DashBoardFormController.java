package lk.royalInstitute.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashBoardFormController implements Initializable {

    public Label dateTime;
    public AnchorPane context;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeClock();
    }

    private void initializeClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void btnAddStudentOnAction(ActionEvent actionEvent) {
        setUI("AddNewStudentForm");
    }

    private void setUI(String destination) {
        try {
            context.getChildren().clear();
            context.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/" + destination + ".fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnAddRegistrationOnAction(ActionEvent actionEvent) {
        setUI("RegistrationForm");
    }

    public void btnAddCourseOnAction(ActionEvent actionEvent) {
        setUI("AddCourseForm");
    }

    public void btnCourseWiseStudentOnAction(ActionEvent actionEvent) {
        setUI("courseWiseStudentForm");
    }

    public void btnUserAccountOnAction(ActionEvent actionEvent) {
        setUI("UserAccountForm");
    }
}
