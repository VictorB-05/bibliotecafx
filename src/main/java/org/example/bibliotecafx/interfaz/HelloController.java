package org.example.bibliotecafx.interfaz;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloController {
    @FXML
    private AnchorPane scene1;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        new SeceneSwitch(scene1,"/org/example/bibliotecafx/interfaz/GestionLibros.fxml");
    }
}