package com.example.codecademy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        stage.setTitle("Matthijs van Gastel-2186230, Quinn Verschoor-2168424, Mohamed Haddouch-2177710, Joshua Angela De La Cruz-2202612");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    } 
}