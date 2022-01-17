package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/main_layout.fxml"));
        primaryStage.getIcons().add(new Image("sample/view/logo.png"));
        primaryStage.setTitle("Book Loan Management (Alpha Build 1.2.7)");
        primaryStage.setScene(new Scene(root, 854, 480));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
