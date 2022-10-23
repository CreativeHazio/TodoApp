package com.creativehazio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/creativehazio/view/sign_up.fxml"));
        primaryStage.setTitle("ORANGE");
        primaryStage.setY(50);
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
