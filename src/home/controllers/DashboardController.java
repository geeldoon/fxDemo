package home.controllers;

import home.dao.StudentDAO;
import home.models.Student;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    StudentDAO studentDAO = new StudentDAO();
    @FXML
    private Label lblStudents;

    @FXML
    private Label lblTeachers;

    @FXML
    private Label lblClasses;

    @FXML
    private Label lblSubjects;

    @FXML
    private Button btnAddNew;

    @FXML
    private TableView<Student> tbvStudents;

    @FXML
    private TableColumn<Student, String> tcStudentName;

    @FXML
    private TableColumn<Student, Integer> tcStudentID;

    @FXML
    private TableColumn<Student, String> tcStudentLevel;

    @FXML
    private TableColumn<Student, String> tcStudentClass;

    @FXML
    private PieChart chStudentsChart;

    @FXML
    private Button btnDelete;

    @FXML
    void delete(ActionEvent event) throws SQLException {
        try {
            Student student = tbvStudents.getSelectionModel().getSelectedItem();
            int i = studentDAO.deleteStudent(student);
            if (i > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, i + " of rows affected");
                alert.show();
            }
        }catch (NullPointerException ne){
            Alert alert = new Alert(Alert.AlertType.ERROR, " Please select the row to delete");
            alert.show();
        }
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/fxml/add_student.fxml"));
        Stage addNew = new Stage();
        addNew.setTitle("Hello School Login");
        addNew.setScene(new Scene(root, 800, 600));
        addNew.getIcons().add(new Image("home/images/icon.png"));
        addNew.show();
    }


    @FXML
    void editCLass(TableColumn.CellEditEvent event) throws SQLException {
        Student editedStudent = tbvStudents.getSelectionModel().getSelectedItem();
        System.out.println("OLD CLASS " + editedStudent.getClassName());
        editedStudent.setClassName(event.getNewValue().toString());
        System.out.println("NEW CLASS "+editedStudent.getClassName());

        int r = studentDAO.updateStudent(editedStudent);
        if(r >0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, r +" of rows affected");
            alert.show();
        }
    }

    @FXML
    void editID(TableColumn.CellEditEvent event) throws SQLException {
        Student editedStudent = tbvStudents.getSelectionModel().getSelectedItem();
        editedStudent.setId(Integer.parseInt(event.getNewValue().toString()));

        int r = studentDAO.updateStudent(editedStudent);
        if(r >0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, r +" of rows affected");
            alert.show();
        }
    }

    @FXML
    void editLevel(TableColumn.CellEditEvent event) throws SQLException {
        //tbvStudents.getSelectionModel().getSelectedItem().setLevel(event.getNewValue().toString());
        Student editedStudent = tbvStudents.getSelectionModel().getSelectedItem();
        editedStudent.setLevel(event.getNewValue().toString());

        int r = studentDAO.updateStudent(editedStudent);
        if(r >0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, r +" of rows affected");
            alert.show();
        }
    }

    @FXML
    void editName(TableColumn.CellEditEvent event) throws SQLException {
        Student editedStudent = tbvStudents.getSelectionModel().getSelectedItem();
        editedStudent.setName(event.getNewValue().toString());

        int r = studentDAO.updateStudent(editedStudent);
        if(r >0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, r +" of rows affected");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Student> students = new ArrayList<>();

        try {
            students = studentDAO.readStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tcStudentName.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        tcStudentClass.setCellValueFactory(new PropertyValueFactory<Student, String>("className"));
        tcStudentID.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        tcStudentLevel.setCellValueFactory(new PropertyValueFactory<Student, String>("level"));

        //making columns editable
        tcStudentClass.setCellFactory(TextFieldTableCell.forTableColumn());
        tcStudentLevel.setCellFactory(TextFieldTableCell.forTableColumn());
        tcStudentName.setCellFactory(TextFieldTableCell.forTableColumn());
        tcStudentID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //making the table editable
        tbvStudents.setEditable(true);
        loadChart(students);
        lblStudents.setText(String.valueOf(students.size()));
        tbvStudents.setItems(FXCollections.observableList(students));


    }


    private void loadChart(ArrayList<Student> students){
        int form1 = 0, form2=0, form3=0, form4 =0;
        for (Student student: students) {
            switch (student.getLevel()){
                case "Form 1":
                    form1++;
                    break;
                case "Form 2":
                    form2++;
                    break;
                case "Form 3":
                    form3++;
                    break;
                case "form 4":
                    form4++;
            }

        }
        chStudentsChart.getData().add(new PieChart.Data("Form 1", form1));
        chStudentsChart.getData().add(new PieChart.Data("Form 3", form3));
        chStudentsChart.getData().add(new PieChart.Data("Form 2", form2));
        chStudentsChart.getData().add(new PieChart.Data("Form 4", form4));
    }
}
