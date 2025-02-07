package org.example.bibliotecafx.interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.bibliotecafx.DAO.ILibros;
import org.example.bibliotecafx.DAO.ILibrosImpl;
import org.example.bibliotecafx.entidades.Libros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private TextField id;
    @FXML
    private RadioButton tituloR;
    @FXML
    private RadioButton autorR;
    @FXML
    private RadioButton isbnR;
    @FXML
    private TableView<Libros> tabla;
    @FXML
    private TableColumn<Libros,Integer> idTable;
    @FXML
    private TableColumn<Libros,String> tituloTable;
    @FXML
    private TableColumn<Libros,Integer> isbnTable;
    @FXML
    private TableColumn<Libros,String> editorialTable;
    @FXML
    private TableColumn<Libros,Integer> anyoTable;

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
        ILibros iLibros = new ILibrosImpl();
        int idInt = Integer.parseInt(id.getText().trim());
        iLibros.deleteLibros(idInt);
    }


    @FXML
    public void buscarLibros(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/libros/LibrosBuscar.fxml");
    }

    @FXML
    public void buscarLibrosBBDD(ActionEvent actionEvent) {
        ILibros iLibros = new ILibrosImpl();
        List<Libros> libros = new ArrayList<Libros>();
        if(tituloR.isFocused()) {
            libros = iLibros.buscarLibrosTitulo(id.getText());
        }else if(isbnR.isFocused()){
            int ISBN = Integer.parseInt( id.getText());
            libros = iLibros.buscarLibrosISBN(ISBN);
        }else if(autorR.isFocused()){
            libros = iLibros.buscarLibrosAutor(id.getText());
        }else{
            return;
        }
        for(Libros libro : libros){

        }
    }
}
