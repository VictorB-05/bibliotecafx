package org.example.bibliotecafx.interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bibliotecafx.DAO.IAutores;
import org.example.bibliotecafx.DAO.IAutoresImpl;
import org.example.bibliotecafx.entidades.Autores;

import java.io.IOException;

public class GestionAutores {
    @FXML
    private AnchorPane principal;
    @FXML
    private AnchorPane ventana;
    @FXML
    private TextField nombre;
    @FXML
    private TextField pais;
    @FXML
    private TextField id;

    @FXML
    public void switchBotton(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(principal,"/org/example/bibliotecafx/interfaz/hello-view.fxml");
    }

    @FXML
    public void addAutores(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/autores/AutoresAdd.fxml");
    }
    @FXML
    public void addAutorBBDD(ActionEvent actionEvent) {
        IAutores iAutores = new IAutoresImpl();
        Autores autor = new Autores(null,nombre.getText(),pais.getText());
        iAutores.addAutores(autor);
    }

    public void modificarAutores(ActionEvent actionEvent) {
    }

    public void deleteAutores(ActionEvent actionEvent) {
    }

    public void buscarAutores(ActionEvent actionEvent) {
    }

    public void listarAutores(ActionEvent actionEvent) {
    }
}
