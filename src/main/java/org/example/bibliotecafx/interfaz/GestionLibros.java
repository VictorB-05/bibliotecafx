package org.example.bibliotecafx.interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bibliotecafx.DAO.ILibros;
import org.example.bibliotecafx.DAO.ILibrosImpl;
import org.example.bibliotecafx.entidades.Autores;
import org.example.bibliotecafx.entidades.Libros;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

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
    private TableView<Libros> tableView;
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
    private TableColumn<Libros, Autores> autorTable;

    @FXML
    protected void switchBotton() throws IOException {
        new SeceneSwitch(scene2,"/org/example/bibliotecafx/interfaz/hello-view.fxml");
    }

    @FXML
    protected void addLibros(ActionEvent actionEvent) throws IOException {


        new SeceneSwitch(ventana,"/org/example/bibliotecafx/libros/LibrosAdd.fxml");

//        ILibrosImpl librosDao = new ILibrosImpl();
//        List<Libros> librosAnaya = Arrays.asList(
//                new Libros("Java desde Cero", "9788499647083", "Anaya", 2022),
//                new Libros("Java desde Cero", "9788499647083", "Anaya", 2022),
//                new Libros("Java desde Cero", "9788499647083", "Anaya", 2022),
//                new Libros("Java desde Cero", "9788499647083", "Anaya", 2022),
//                new Libros("Java desde Cero", "9788499647083", "Anaya", 2022),
//                new Libros("Java desde Cero", "9788499647083", "Anaya", 2022)
//        );
//
//        for (Libros libro : librosAnaya) {
//            librosDao.addLibro(libro);
//        }
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
        if(idTable.getCellValueFactory() == null){
            idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
            tituloTable.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            isbnTable.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            editorialTable.setCellValueFactory(new PropertyValueFactory<>("editorial"));
            anyoTable.setCellValueFactory(new PropertyValueFactory<>("anyo"));
            autorTable.setCellValueFactory(new PropertyValueFactory<>("autores"));
        }else{
            tableView.getItems().clear();
        }

        ILibros iLibros = new ILibrosImpl();
        List<Libros> libros = new ArrayList<Libros>();
        if(tituloR.isSelected()) {
            System.out.println(id.getText());
            libros = iLibros.buscarLibrosTitulo(id.getText());
        }else if(isbnR.isSelected()){
            libros = iLibros.buscarLibrosISBN(id.getText().trim());
        }else if(autorR.isSelected()){
            libros = iLibros.buscarLibrosAutor(id.getText());
        }else{
            return;
        }
        tableView.getItems().addAll(libros);
    }
    @FXML
    public void modificarLibros(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/libros/LibrosModiifcar.fxml");
    }

    @FXML
    public void modificarLibrosBBDD(ActionEvent actionEvent) {
        Libros libro;
        int anyo = Integer.parseInt(this.anyo.getText());
        int id = Integer.parseInt(this.id.getText());
        if(autor.getText().trim()==""){
            libro  = new Libros(titulo.getText(),isbn.getText(),editorial.getText(),anyo);

        }else{
            libro  = new Libros(titulo.getText(),isbn.getText(),editorial.getText(),anyo);
        }
        ILibros librosDDBB = new ILibrosImpl();
        librosDDBB.modificarLibros(libro);
        libro.setId(id);
    }
}
