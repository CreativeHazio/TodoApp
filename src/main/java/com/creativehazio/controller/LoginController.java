package com.creativehazio.controller;

import com.creativehazio.todo.SlicesDAOInterface;
import com.creativehazio.todo.SlicesDatabase;
import com.creativehazio.user.User;
import com.creativehazio.user.UserDAOInterface;
import com.creativehazio.user.UserDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    Parent root;
    Scene scene;
    Stage stage;

    private UserDAOInterface udb;
    private SlicesDAOInterface sdb;

    private String currentUsername;
    private String currentUserPassword;
    private static LoginController loginController = null;



    public LoginController() {
        getCurrentUsername();
        getCurrentUserPassword();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label loginLabel;

    @FXML
    private Button SignUpButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField loginUsername;

    @FXML
    void initialize() {
        loginButton.setOnAction(event ->{
            udb = new UserDatabase();
            sdb = new SlicesDatabase();

            User previousUser = udb.getUser(loginUsername.getText(),loginPassword.getText());

            if (previousUser!=null){
                try {
                    loginController = LoginController.getInstance();
                    loginController.setCurrentUsername(loginUsername.getText());
                    loginController.setCurrentUserPassword(loginPassword.getText());
                    root = FXMLLoader.load(getClass().getResource("/com/creativehazio/view/add_slices.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
//                    TextArea previousUserSlices = (TextArea) root.get;   //TODO : Find a way to get add_slices textarea
//                    previousUserSlices.setText(sdb.getAllSlice(previousUser);
                }catch (IOException e){}
            }
            else
                loginLabel.setText("User not found!!");
        });
        SignUpButton.setOnAction(event -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/com/creativehazio/view/sign_up.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){}
        });
    }

    public static LoginController getInstance(){
        if (loginController == null)
            loginController = new LoginController();
        return loginController;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getCurrentUserPassword() {
        return currentUserPassword;
    }

    public void setCurrentUserPassword(String currentUserPassword) {
        this.currentUserPassword = currentUserPassword;
    }
}
