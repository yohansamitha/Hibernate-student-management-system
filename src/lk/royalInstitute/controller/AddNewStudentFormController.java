package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import lk.royalInstitute.bo.custom.StudentBO;
import lk.royalInstitute.dto.StudentDTO;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewStudentFormController implements Initializable {
    public JFXTextField txtFullName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXDatePicker txtDOB;
    public JFXComboBox cblGender;
    public Label lblStudentID;
    public TableColumn colStudentID;
    public TableColumn colFullName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;
    public TableView tblStudent;
    StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.StudentBOImpl);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("student_ID"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        loadStudentID();
        loadAllStudents();
    }

    private void loadAllStudents() {
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
        tblStudent.setItems(FXCollections.observableArrayList(allStudent));
    }

    private void loadStudentID() {
        try {
            String studentID = studentBO.getStudentID();
            int newId = Integer.parseInt(studentID.substring(1, 4)) + 1;
            if (newId < 10) {
                lblStudentID.setText("S00" + newId);
            } else if (newId < 100) {
                lblStudentID.setText("S0" + newId);
            } else {
                lblStudentID.setText("S" + newId);
            }
        } catch (NullPointerException e) {
            lblStudentID.setText("S001");
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = new StudentDTO(lblStudentID.getText(), txtFullName.getText(), txtAddress.getText(), txtContact.getText(), Date.valueOf(txtDOB.getValue()), String.valueOf(cblGender.getValue()));
        try {
            boolean b = studentBO.addStudent(studentDTO);
            System.out.println(b);
            if (b) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("operation is success!");
                alert.showAndWait();
                clear();
                loadStudentID();
                loadAllStudents();
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

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (!lblStudentID.getText().isEmpty()) {
            String studentID = lblStudentID.getText();
            boolean b = studentBO.deleteStudent(studentID);
            if (b) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("operation is success!");
                alert.showAndWait();
                clear();
                loadStudentID();
                loadAllStudents();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("something went wrong!");
                alert.showAndWait();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            boolean b = studentBO.updateStudent(new StudentDTO(lblStudentID.getText(), txtFullName.getText(), txtAddress.getText(), txtContact.getText(), Date.valueOf(txtDOB.getValue()), String.valueOf(cblGender.getValue())));
            System.out.println(b);
            if (b) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("operation is success!");
                alert.showAndWait();
                clear();
                loadStudentID();
                loadAllStudents();
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

    public void btnCancelOnAction(ActionEvent actionEvent) {
        clear();
    }

    private void clear() {
        txtFullName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDOB.getEditor().clear();
        cblGender.getSelectionModel().clearSelection();
        loadStudentID();
        loadAllStudents();
    }

    public void tblStudentOnReleased(MouseEvent mouseEvent) {
        StudentDTO selectedItem = (StudentDTO) tblStudent.getSelectionModel().getSelectedItem();
        lblStudentID.setText(selectedItem.getStudent_ID());
        txtFullName.setText(selectedItem.getStudentName());
        txtAddress.setText(selectedItem.getAddress());
        txtContact.setText(selectedItem.getContact());
        txtDOB.setValue(LocalDate.parse(selectedItem.getDob() + ""));
        cblGender.getSelectionModel().select(selectedItem.getGender());

    }
}
