package com.creativehazio.controller;

import com.creativehazio.user.User;
import com.creativehazio.user.UserDAOInterface;
import com.creativehazio.user.UserDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController{

    private Parent root;
    private Scene scene;
    private Stage stage;

    private UserDAOInterface udb;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackToLogin;

    @FXML
    private Label failedOrSuccess;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpFirstname;

    @FXML
    private TextField signUpLastname;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private TextField signUpUsername;

    @FXML
    private Button setupDatabaseBtn;

    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            User newUser = new User(signUpFirstname.getText(),signUpLastname.getText(),
                                                    signUpUsername.getText(),signUpPassword.getText());
            udb = new UserDatabase();
            boolean createUsersTable = udb.createUsersTable();
            if (createUsersTable){
                boolean save = udb.save(newUser);
                if (save)
                    failedOrSuccess.setText("SUCCESS");
                else
                    failedOrSuccess.setText("FAILED");
            }else
                System.out.println("error");
        });
        BackToLogin.setOnAction(event -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/com/creativehazio/view/login.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){}
        });
        setupDatabaseBtn.setOnAction(event -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/com/creativehazio/view/database_setup.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){}
        });
    }
}
