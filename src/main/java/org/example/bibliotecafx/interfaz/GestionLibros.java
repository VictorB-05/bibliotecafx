package org.example.bibliotecafx.interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bibliotecafx.DAO.ILibros;
import org.example.bibliotecafx.DAO.ILibrosImpl;
import org.example.bibliotecafx.entidades.Libros;

import java.io.IOException;

public class GestionLibros {
    @FXML
    private AnchorPane scene2;
    @FXML
    private AnchorPane ventana;
    @FXML
    private TextField titulo;
    @FXML
    private TextField isbn;
    @FXML
    private TextField autor;
    @FXML
    private TextField editorial;
    @FXML
    private TextField anyo;

    @FXML
    protected void switchBotton() throws IOException {
        new SeceneSwitch(scene2,"/org/example/bibliotecafx/interfaz/hello-view.fxml");
    }

    @FXML
    protected void addLibros(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/libros/LibrosAdd.fxml");
    }

    @FXML
    protected void deleteLibros(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/libros/LibrosEliminar.fxml");
    }

    @FXML
    protected void addLibrosBBDD(ActionEvent actionEvent) {
        ILibros iLibros = new ILibrosImpl();
        int anyoInt = Integer.parseInt(anyo.getText().trim());
        Libros libro = new Libros(titulo.getText(),isbn.getText(),editorial.getText(),anyoInt);
        iLibros.addLibro(libro);
    }

    @FXML
    protected void deleteLibrosBBDD(ActionEvent actionEvent) {
        //Hibernate
        System.out.println("paco");
    }


}
