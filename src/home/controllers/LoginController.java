package home.controllers;

import com.sun.source.tree.TryTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txfUsername;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private Hyperlink hylForgotPassword;

    @FXML
    private Label lblWrongCredentials;


    @FXML
    void signUp(ActionEvent event) {

    }

    @FXML
    void singIn(ActionEvent event) {
        if(txfUsername.getText().equals("admin") && password.getText().equals("admin")) {
            try {
                //create Stage instance
                Stage daschboardStage = new Stage();
                daschboardStage.setTitle("Dashboard");
                daschboardStage.getIcons().add(new Image("/home/images/icon.png"));

                Parent root = FXMLLoader.load(getClass().getResource("/home/fxml/dashboard.fxml"));
                //Scene scene = new Scene(root, 1000, 600);
                daschboardStage.setScene(new Scene(root, 1000, 600));

                //get the login window to close

                txfUsername.getScene().getWindow().hide();
                daschboardStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else
            lblWrongCredentials.setVisible(true);
        System.out.println("Login button pressed");
    }

}
