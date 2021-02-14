package lk.royalInstitute.controller;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.custom.QueryDAO;
import lk.royalInstitute.entity.CustomEntity;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseWiseStudentFormController implements Initializable {
    public TableView tblCourseWisesStudent;
    public TableColumn colRegNo;
    public TableColumn colRegDate;
    public TableColumn colRegFee;
    public TableColumn colStudent_ID;
    public TableColumn colStudentName;
    public TableColumn colContact;
    public TableColumn colDob;
    public TableColumn colCourse_code;
    public TableColumn colCourseName;
    public TableColumn colCourseFee;
    public TableColumn colCourseDuration;
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QueryDAOImpl);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        colRegFee.setCellValueFactory(new PropertyValueFactory<>("regFee"));
        colStudent_ID.setCellValueFactory(new PropertyValueFactory<>("student_ID"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colCourse_code.setCellValueFactory(new PropertyValueFactory<>("course_code"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCourseFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
        colCourseDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        loadStudent();
    }

    private void loadStudent() {
        ArrayList<CustomEntity> courseWiseStudent = queryDAO.getCourseWiseStudent();
        tblCourseWisesStudent.setItems(FXCollections.observableArrayList(courseWiseStudent));
    }
}
