package com.creativehazio.controller;

import com.creativehazio.todo.Slices;
import com.creativehazio.todo.SlicesDAOInterface;
import com.creativehazio.todo.SlicesDatabase;
import com.creativehazio.user.UserDAOInterface;
import com.creativehazio.user.UserDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddSlicesController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    private SlicesDAOInterface sdb;
    private UserDAOInterface udb;
    LoginController loginController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Button addSlice;

    @FXML
    private Button allSlices;

    @FXML
    private Label isSaved;

    @FXML
    private TextArea slices;

    @FXML
    void initialize() {
        addSlice.setOnAction(event -> {
            udb = new UserDatabase();
            sdb = new SlicesDatabase();
            loginController = LoginController.getInstance();

            Slices slice = new Slices("Todo", Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()),slices.getText());
            boolean saveSlice = sdb.save(slice,udb.getUser(loginController.getCurrentUsername(),loginController.getCurrentUserPassword()));
            long id = 0;
            while (!saveSlice) {
                saveSlice = sdb.save(slice,udb.getUser(loginController.getCurrentUsername(),loginController.getCurrentUserPassword()));
                slice.setId(id++);

                if (saveSlice){
                    slices.setText("");
                    isSaved.setText("Saved");
                }else
                    isSaved.setText("Failed");
            }
//            TODO: Set allSlices TextArea with slices and Clear slices TextArea after successful save..... slices.setText("");
        });
        allSlices.setOnAction(event -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/com/creativehazio/view/allSlice.fxml"));
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
