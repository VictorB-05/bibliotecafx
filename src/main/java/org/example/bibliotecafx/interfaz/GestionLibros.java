package org.example.bibliotecafx.interfaz;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GestionLibros {
    @FXML
    private AnchorPane scene2;

    @FXML
    protected void switchBotton() throws IOException {
        new SeceneSwitch(scene2,"/org/example/bibliotecafx/interfaz/hello-view.fxml");
    }
}
