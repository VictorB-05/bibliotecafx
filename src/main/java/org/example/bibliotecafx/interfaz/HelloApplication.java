package org.example.bibliotecafx.interfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/bibliotecafx/interfaz/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        scene.getStylesheets().add(getClass().getResource("/org/example/bibliotecafx/interfaz/styles.css").toExternalForm());
        stage.setTitle("Biblioteca!");
        stage.setScene(scene);
        stage.show();
    }

}