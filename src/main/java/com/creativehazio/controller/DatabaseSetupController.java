package com.creativehazio.controller;

import com.creativehazio.database.DB;
import com.creativehazio.database.DatabaseConnector;
import com.creativehazio.database.DatabaseDAO;
import com.creativehazio.database.DatabaseDB;
import com.creativehazio.user.User;
import com.creativehazio.user.UserDAOInterface;
import com.creativehazio.user.UserDatabase;
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

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DatabaseSetupController {

    private DatabaseConnector databaseConnector;
    private DatabaseDAO databaseDB;
    private DB dbModel;
    private UserDAOInterface udb;
    private LoginController loginController;

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backToSignUp;

    @FXML
    private Button connectToDBbutton;

    @FXML
    private Button connectWithDatabaseID;

    @FXML
    private PasswordField databaseID;

    @FXML
    private Label databaseIDLabel;

    @FXML
    private Label databaseIDisSuccess;

    @FXML
    private PasswordField mysqlPassword;

    @FXML
    private TextField mysqlURL;

    @FXML
    private TextField mysqlUsername;


    @FXML
    void initialize() {
        connectToDBbutton.setOnAction(event -> {
            databaseDB = new DatabaseDB();

            String url = mysqlURL.getText().trim();
            String root = mysqlUsername.getText().trim();
            String password = mysqlPassword.getText().trim();

            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.setUrl(url);
            databaseConnector.setRoot(root);
            databaseConnector.setPassword(password);
            Connection conn = DatabaseConnector.getConn();

            if (conn != null){
                dbModel = new DB(url,root,password);
                databaseDB.createTable();
                if (databaseDB.save(dbModel)){
                    databaseIDLabel.setText("Connected, Your ID is " +dbModel.getId());
                }
                try {
                    FileWriter wr = new FileWriter(dbModel.getId()+".dbi");
                    wr.write(dbModel.getUrl()+" ");
                    wr.write(dbModel.getRoot()+" ");
                    wr.write(dbModel.getPassword());
                    wr.close();
                }catch (Exception e){
                    System.out.println("Unable to write file"+ e.getMessage());
                }
            }

        });
        backToSignUp.setOnAction(event -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/com/creativehazio/view/sign_up.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){}
        });
        connectWithDatabaseID.setOnAction(event -> {
            udb = new UserDatabase();
            databaseDB = new DatabaseDB();
            String loginUsername = "";
            String loginPassword = "";

            String databaseid = databaseID.getText();
            int dbID = Integer.parseInt(databaseid);
            DB db = databaseDB.getByID(dbID);

            if (db != null){
                databaseIDisSuccess.setText("Connected");

                try {
                    Connection conn = DatabaseConnector.getConn();
                    PreparedStatement pst = conn.prepareStatement("SELECT * FROM users");
                    ResultSet rs = pst.executeQuery();

                    while (rs.next()){
                        loginUsername = rs.getString("username");
                        loginPassword = rs.getString("password");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

                User previousUser = udb.getUser(loginUsername,loginPassword);

                if (previousUser!=null){
                    try {
                        loginController = LoginController.getInstance();
                        loginController.setCurrentUsername(loginUsername);
                        loginController.setCurrentUserPassword(loginPassword);
                        root = FXMLLoader.load(getClass().getResource("/com/creativehazio/view/add_slices.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }catch (IOException e){}
                }
            }else
                databaseIDisSuccess.setText("Failed");
        });

    }
}
