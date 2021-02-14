package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.royalInstitute.bo.BOFactory;
import lk.royalInstitute.bo.custom.CourseBO;
import lk.royalInstitute.dto.CourseDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCourseFormController implements Initializable {
    public JFXTextField txtCourseID;
    public JFXTextField txtCourseName;
    public JFXTextField txtDuration;
    public JFXTextField txtRegFee;
    public JFXTextField txtSearch;
    public TableView tblAllCourse;
    public TableColumn colCourseID;
    public TableColumn colCourseName;
    public TableColumn colDuration;
    public TableColumn colRegistrationFee;
    CourseBO courseBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CourseBOImpl);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllCourses();
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("course_code"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colRegistrationFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
    }

    private void loadAllCourses() {
        ArrayList<CourseDTO> allCourse = courseBO.getAllCourse();
        tblAllCourse.setItems(FXCollections.observableArrayList(allCourse));
    }

    public void btnAddCourseOnAction(ActionEvent actionEvent) {
        try {
            String courseID = txtCourseID.getText();
            String courseName = txtCourseName.getText();
            String duration = txtDuration.getText();
            double regFee = Double.parseDouble(txtRegFee.getText());
            boolean b = courseBO.addCourse(new CourseDTO(courseID, courseName, duration, regFee));
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
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("something went wrong!");
            alert.showAndWait();
        }
    }

    public void btnDeleteCourseOnAction(ActionEvent actionEvent) {
        if (!txtCourseID.getText().isEmpty()) {
            String courseCode = txtCourseID.getText();
            boolean b = courseBO.deleteCourse(courseCode);
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
    }

    public void btnUpdateCourseOnAction(ActionEvent actionEvent) {
        String courseID = txtCourseID.getText();
        String courseName = txtCourseName.getText();
        String duration = txtDuration.getText();
        double regFee = Double.parseDouble(txtRegFee.getText());
        boolean b = courseBO.updateCourse(new CourseDTO(courseID, courseName, duration, regFee));
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

    public void txtSearchOnKeyRelease(KeyEvent keyEvent) {

    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void tblAllCourseMouseReleased(MouseEvent mouseEvent) {
        CourseDTO selectedItem = (CourseDTO) tblAllCourse.getSelectionModel().getSelectedItem();
        txtCourseID.setText(selectedItem.getCourse_code());
        txtCourseName.setText(selectedItem.getCourseName());
        txtDuration.setText(selectedItem.getDuration());
        txtRegFee.setText(String.valueOf(selectedItem.getCourseFee()));
    }

    private void clear() {
        txtCourseID.clear();
        txtCourseName.clear();
        txtDuration.clear();
        txtRegFee.clear();
        loadAllCourses();
    }
}
