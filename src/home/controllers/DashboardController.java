package home.controllers;

import home.models.Student;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label lblStudents;

    @FXML
    private Label lblTeachers;

    @FXML
    private Label lblClasses;

    @FXML
    private Label lblSubjects;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Student> students = new ArrayList<>();
        Student student = new Student("Moahmed Ahmed",88823, "Form 1", "Class A");
        students.add(student);
        student = new Student("Ali Warsame", 7733, "Form 2", "Class B");
        students.add(student);
        student = new Student("Abdillahi Abdirahman", 2324, "Form 1", "Class A");
        students.add(student);

        student = new Student("Abdillahi Abdirahman", 2324, "Form 3", "Class A");
        students.add(student);
        student = new Student("Abdillahi Abdirahman", 2324, "Form 4", "Class A");
        students.add(student);
        student = new Student("Abdillahi Abdirahman", 2324, "Form 1", "Class A");
        students.add(student);
        student = new Student("Abdillahi Abdirahman", 2324, "Form 2", "Class A");
        students.add(student);

        student = new Student("Abdillahi Abdirahman", 2324, "Form 3", "Class A");
        students.add(student);
        student = new Student("Abdillahi Abdirahman", 2324, "Form 3", "Class A");
        students.add(student);
        student = new Student("Abdillahi Abdirahman", 2324, "Form 3", "Class A");
        students.add(student);
        student = new Student("Abdillahi Abdirahman", 2324, "Form 3", "Class A");
        students.add(student);

        tcStudentName.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        tcStudentClass.setCellValueFactory(new PropertyValueFactory<Student, String>("className"));
        tcStudentID.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        tcStudentLevel.setCellValueFactory(new PropertyValueFactory<Student, String>("level"));

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
