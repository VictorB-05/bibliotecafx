package org.example.bibliotecafx.interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloController {
    @FXML
    private AnchorPane scene1;

    @FXML
    public void onGestionLibrosClick(ActionEvent actionEvent) throws IOException{
        new SeceneSwitch(scene1,"/org/example/bibliotecafx/interfaz/GestionLibros.fxml");
    }

    @FXML
    public void onGestionAutoresClick(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(scene1,"/org/example/bibliotecafx/interfaz/GestionAutores.fxml");
    }

    @FXML
    public void onGestionClientesClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onGestionPrestamosClick(ActionEvent actionEvent) {
    }
}