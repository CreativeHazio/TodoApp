package com.creativehazio.controller;

import com.creativehazio.database.DatabaseConnector;
import com.creativehazio.user.UserDAOInterface;
import com.creativehazio.user.UserDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AllSliceController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    private UserDAOInterface udb;
    private LoginController loginController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backToAddSlices;

    @FXML
    private TextArea allSlicesTextArea = new TextArea();


    @FXML
    void initialize() {
        udb = new UserDatabase();
        loginController = LoginController.getInstance();

        String all = "";

        try {
            Connection conn = DatabaseConnector.getConn();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM "+
                    udb.getUser(loginController.getCurrentUsername(),loginController.getCurrentUserPassword()).getUserName()
                    +"SLICES");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                Date dateCreated = rs.getDate("dateCreated");
                Time timeCreated = rs.getTime("timeCreated");
                String body = rs.getString("body");

                all += "* Title: "+title.toUpperCase()+"\n"+ "Contents: "+body +"\n" + dateCreated +"\t" +timeCreated+"\n\n";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        allSlicesTextArea.setText(all);

        backToAddSlices.setOnAction(event -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/com/creativehazio/view/add_slices.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
