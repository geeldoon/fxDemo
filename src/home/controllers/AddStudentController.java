package home.controllers;

import home.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {

    @FXML
    private Button btnRegister;

    @FXML
    private Label lblWrongCredentials;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtID;

    @FXML
    private ComboBox<String> cboLevel;

    @FXML
    private ComboBox<String> cboClass;

    @FXML
    private RadioButton rbMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private CheckBox chbArabic;

    @FXML
    private CheckBox chbEnglish;

    @FXML
    void registerStudent(ActionEvent event) {
        try {
            String name = txtName.getText().toString();
            String level = cboLevel.getSelectionModel().getSelectedItem().toString();
            String className = cboClass.getSelectionModel().getSelectedItem().toString();
            int id = Integer.parseInt(txtID.getText().toString());
            String gender = this.gender.getSelectedToggle().getUserData().toString();

            String[] languages = new String[2];
            Student student = new Student(name, id, level, className);
            if (chbArabic.isSelected()) {
                languages[0] = "Arabic";
            }

            if (chbEnglish.isSelected()) {
                languages[1] = "English";
            }

            student.setLanguages(languages);
            student.setGender(gender);

            System.out.println("Student Name: " + student.getName());
            System.out.println("Student ID " + student.getId());
            System.out.println("Student Class "+ student.getClassName());
            System.out.println("Student Level: "+ student.getLevel());
            System.out.println("Student Languages" + student.getLanguages()[0]);
            System.out.println("Student Languages" + student.getLanguages()[1]);
            System.out.println("Student Gender" + student.getGender());
        }catch (NumberFormatException nfe){
            System.err.println(nfe);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Enter number only in the ID field");
            alert.showAndWait();
        }catch (NullPointerException npe){
            System.err.println(npe);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select the level and Class");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cboClass.getItems().addAll("Class A", "Class B", "Class C");
        cboLevel.getItems().addAll("Form 1", "Form 2", "Form 3", "Form 4");
        rbFemale.setUserData("Female");
        rbMale.setUserData("Male");
    }
}
