package org.example.bibliotecafx.interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bibliotecafx.DAO.IAutores;
import org.example.bibliotecafx.DAO.IAutoresImpl;
import org.example.bibliotecafx.entidades.Autores;
import org.example.bibliotecafx.entidades.Socios;

import java.io.IOException;
import java.util.List;

public class GestionSocios {

    @FXML
    private AnchorPane principal;
    @FXML
    private AnchorPane ventana;
    @FXML
    private TextField nombre;
    @FXML
    private TextField telefono;
    @FXML
    private TextField direccion;
    @FXML
    private TextField id;
    @FXML
    private TableView<Socios> tableView;
    @FXML
    private TableColumn<Socios,Integer> idTable;
    @FXML
    private TableColumn<Socios,String> nombreTable;
    @FXML
    private TableColumn<Socios,String> telefonoTable;
    @FXML
    private TableColumn<Socios,String> direccionTable;


    public void switchBotton(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(principal,"/org/example/bibliotecafx/interfaz/hello-view.fxml");
    }

    public void addAutores(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/autores/AutoresAdd.fxml");
    }


    public void modificarAutores(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/autores/AutoresModificar.fxml");
    }

    public void deleteAutores(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/autores/AutoresEliminar.fxml");

    }

    public void buscarAutores(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/autores/AutoresBuscar.fxml");
    }

    public void listarAutores(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/autores/AutoresListar.fxml");
    }
    public void addAutorBBDD(ActionEvent actionEvent) {
        IAutores iAutores = new IAutoresImpl();
        Autores autor = new Autores(null,nombre.getText(), telefono.getText());
        iAutores.addAutores(autor);
    }

    public void deleteAutoresBBDD(ActionEvent actionEvent) {
        IAutores iAutores = new IAutoresImpl();
        int id = Integer.parseInt(this.id.getText());
        iAutores.deleteAutor(id);
    }

    public void modificarAutorBBDD(ActionEvent actionEvent) {
        IAutores iAutores = new IAutoresImpl();
        int id = Integer.parseInt(this.id.getText());
        Autores autor = new Autores(id,nombre.getText(), telefono.getText());
        iAutores.modificarAutor(autor);
    }

    public void buscarAutoresBBDD(ActionEvent actionEvent) {
//        if(idTable.getCellValueFactory() == null){
//            idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
//            nombreTable.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//            telefonoTable.setCellValueFactory(new PropertyValueFactory<>("pais"));
//        }else{
//            tableView.getItems().clear();
//        }
//
//        IAutores iAutores = new IAutoresImpl();
//        List<Autores> autores = iAutores.buscarAutores(nombre.getText());
//        tableView.getItems().addAll(autores);
    }

    public void listarAutoresBBDD(ActionEvent actionEvent) {
//        if(idTable.getCellValueFactory() == null){
//            idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
//            nombreTable.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//            telefonoTable.setCellValueFactory(new PropertyValueFactory<>("pais"));
//        }else{
//            tableView.getItems().clear();
//        }
//
//        IAutores iAutores = new IAutoresImpl();
//        List<Autores> autores = iAutores.listarAutores();
//        tableView.getItems().addAll(autores);
    }
}
