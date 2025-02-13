package org.example.bibliotecafx.interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bibliotecafx.DAO.IAutores;
import org.example.bibliotecafx.DAO.IAutoresImpl;
import org.example.bibliotecafx.DAO.ILibros;
import org.example.bibliotecafx.DAO.ILibrosImpl;
import org.example.bibliotecafx.entidades.Autores;
import org.example.bibliotecafx.entidades.Libros;

import java.io.IOException;
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
    }

    @FXML
    protected void deleteLibros(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/libros/LibrosEliminar.fxml");
    }

    @FXML
    protected void addLibrosBBDD(ActionEvent actionEvent) {
        ILibros iLibros = new ILibrosImpl();
        IAutores iAutores = new IAutoresImpl();
        int anyoInt = Integer.parseInt(anyo.getText().trim());
        String autorAux = autor.getText().trim();
        if(autorAux.isEmpty()){
            Libros libro = new Libros(null,titulo.getText(),isbn.getText(),editorial.getText(),anyoInt);
            iLibros.addLibro(libro);
        }else {
            int autorId = Integer.parseInt(autorAux);
            Autores autor = iAutores.buscarAutor(autorId);
            if(autor!=null){
                System.out.println(autor);
                Libros libro = new Libros(null,titulo.getText(),isbn.getText(),editorial.getText(),anyoInt,autor);
                iLibros.addLibro(libro);
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Autor no encontrado");
                alert.setContentText("El ID del autor ingresado no existe en la base de datos.");
                alert.showAndWait();
            }
        }
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
        List<Libros> libros;
        if(tituloR.isSelected()) {
            System.out.println(id.getText());
            libros = iLibros.buscarLibrosTitulo(id.getText());
        }else if(isbnR.isSelected()){
            libros = iLibros.buscarLibrosISBN(id.getText().trim());
        }else if(autorR.isSelected()){
            IAutores iAutores = new IAutoresImpl();
            String autorAux = id.getText().trim();
            if(!autorAux.isEmpty()){
                try {
                    int autorId = Integer.parseInt(autorAux);
                    Autores autor = iAutores.buscarAutor(autorId);
                    if(autor!=null) {
                        libros = iLibros.buscarLibrosAutor(autor);
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Autor no encontrado");
                        alert.setContentText("El ID del autor ingresado no existe en la base de datos.");
                        alert.showAndWait();
                        return;
                    }
                }catch (NumberFormatException ex){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("ID");
                    alert.setContentText("El ID tiene que ser un numero");
                    alert.showAndWait();
                    return;
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ID");
                alert.setContentText("El ID no puede estar vacio");
                alert.showAndWait();
                return;
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Opción");
            alert.setContentText("Elge una opción");
            alert.showAndWait();
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
        IAutores iAutores = new IAutoresImpl();
        int anyo = Integer.parseInt(this.anyo.getText());
        int id = Integer.parseInt(this.id.getText());
        String autorAux = autor.getText().trim();
        if(autorAux.isEmpty()){
            libro  = new Libros(id,titulo.getText(),isbn.getText(),editorial.getText(),anyo);
            ILibros librosDDBB = new ILibrosImpl();
            librosDDBB.modificarLibros(libro);
        }else{
            int autorId = Integer.parseInt(autorAux);
            Autores autor = iAutores.buscarAutor(autorId);
            if(autor!=null){
                libro = new Libros(id,titulo.getText(),isbn.getText(),editorial.getText(),anyo,autor);
                ILibros librosDDBB = new ILibrosImpl();
                librosDDBB.modificarLibros(libro);
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Autor no encontrado");
                alert.setContentText("El ID del autor ingresado no existe en la base de datos.");
                alert.showAndWait();
            }
        }
    }

    public void listarLibros() throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/libros/LibrosListar.fxml");

    }

    public void listarLibrosBBDD(){
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
        List<Libros> libros = iLibros.buscarLibrosPrestamo(true);

        tableView.getItems().addAll(libros);
    }
}
