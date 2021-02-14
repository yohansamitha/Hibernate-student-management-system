package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.royalInstitute.bo.BOFactory;
import lk.royalInstitute.bo.custom.CourseBO;
import lk.royalInstitute.bo.custom.RegistrationBO;
import lk.royalInstitute.bo.custom.StudentBO;
import lk.royalInstitute.dto.CourseDTO;
import lk.royalInstitute.dto.RegistrationDTO;
import lk.royalInstitute.dto.StudentDTO;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrationFormController implements Initializable {
    public Label lblRegistrationID;
    public JFXTextField txtRegDate;
    public JFXComboBox cmbStudentID;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentContact;
    public JFXComboBox cmbCourseCode;
    public JFXTextField txtRegFee;
    RegistrationBO registrationBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.RegistrationBOImpl);
    StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.StudentBOImpl);
    CourseBO courseBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CourseBOImpl);
    ArrayList<StudentDTO> allStudent;
    ArrayList<CourseDTO> allCourse;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadRegistrationID();
        loadAllCourses();
        loadAllStudent();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtRegDate.setText(LocalDateTime.now().format(formatter));
    }

    private void loadRegistrationID() {
        try {
            String studentID = registrationBO.getRegistrationID();
            int newId = Integer.parseInt(studentID.substring(1)) + 1;
            if (newId < 10) {
                lblRegistrationID.setText("R00" + newId);
            } else if (newId < 100) {
                lblRegistrationID.setText("R0" + newId);
            } else {
                lblRegistrationID.setText("R" + newId);
            }
        } catch (NullPointerException e) {
            lblRegistrationID.setText("R001");
        }
    }

    private void loadAllStudent() {
        allStudent = studentBO.getAllStudent();
        ArrayList<String> allStudentName = new ArrayList<>();
        for (StudentDTO studentDTO : allStudent) {
            allStudentName.add(studentDTO.getStudent_ID());
            System.out.println(studentDTO.getStudentName());
        }
        cmbStudentID.setItems(FXCollections.observableArrayList(allStudentName));
    }

    private void loadAllCourses() {
        allCourse = courseBO.getAllCourse();
        ArrayList<String> allCourseName = new ArrayList<>();
        for (CourseDTO courseDTO : allCourse) {
            allCourseName.add(courseDTO.getCourse_code());
        }
        cmbCourseCode.setItems(FXCollections.observableArrayList(allCourseName));
    }

    public void cmbCourseCodeOnAction(ActionEvent actionEvent) {
        String courseCode = (String) cmbCourseCode.getValue();
        for (CourseDTO courseDTO : allCourse) {
            if (courseDTO.getCourse_code().equals(courseCode)) {
                txtRegFee.setText(String.valueOf(courseDTO.getCourseFee()));
            }
        }
    }

    public void cmbStudentIDOnAction(ActionEvent actionEvent) {
        String studentID = (String) cmbStudentID.getValue();
        for (StudentDTO studentDTO : allStudent) {
            if (studentDTO.getStudent_ID().equals(studentID)) {
                txtStudentName.setText(studentDTO.getStudentName());
                txtStudentContact.setText(studentDTO.getContact());
            }
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String id = lblRegistrationID.getText();
        String date = txtRegDate.getText();
        String studentID = (String) cmbStudentID.getValue();
//        txtStudentName.getText();
//        txtStudentContact.getText();
        String courseCode = (String) cmbCourseCode.getValue();
        double regFee = Double.parseDouble(txtRegFee.getText());
        RegistrationDTO registrationDTO = new RegistrationDTO(id, date, regFee, studentID, courseCode);
        boolean b = registrationBO.placeRegistration(registrationDTO);
        if (b) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("operation is success!");
            alert.showAndWait();
            clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("something went wrong!");
            alert.showAndWait();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        clear();
    }

    private void clear() {
        txtStudentContact.clear();
        txtStudentName.clear();
        txtRegFee.clear();
        cmbStudentID.getSelectionModel().clearSelection();
        cmbCourseCode.getSelectionModel().clearSelection();
        loadRegistrationID();
    }
}
