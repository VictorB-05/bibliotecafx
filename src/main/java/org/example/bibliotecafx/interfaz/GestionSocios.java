package org.example.bibliotecafx.interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bibliotecafx.DAO.*;
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
    public RadioButton nombreR;
    @FXML
    public RadioButton telefonoR;
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

    public void addSocios(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/socios/SociosAdd.fxml");
    }


    public void modificarSocios(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana, "/org/example/bibliotecafx/socios/SociosModifcar.fxml");
    }

    public void deleteSocios(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/socios/SociosEliminar.fxml");

    }

    public void buscarSocios(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/socios/SociosBuscar.fxml");
    }

    public void listarSocios(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/socios/SociosListar.fxml");
    }
    public void addSociosBBDD(ActionEvent actionEvent) {
        ISocios iSocios = new ISociosImpl();
        Socios socio = new Socios(null,nombre.getText(),telefono.getText(),direccion.getText());
        iSocios.addSocios(socio);
    }

    public void deleteSocioBBDD(ActionEvent actionEvent) {
        ISocios iSocios = new ISociosImpl();
        int id = Integer.parseInt(this.id.getText());
        iSocios.deleteSocio(id);
    }

    public void modificarSociosBBDD(ActionEvent actionEvent) {
        ISocios iSocios = new ISociosImpl();
        int id = Integer.parseInt(this.id.getText());
        Socios socio = new Socios(id,nombre.getText(),telefono.getText(), direccion.getText());
        iSocios.modificarSocios(socio);
    }

    public void buscarSociosBBDD(ActionEvent actionEvent) {
        if(idTable.getCellValueFactory() == null){
            idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
            nombreTable.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            telefonoTable.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            direccionTable.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        }else{
            tableView.getItems().clear();
        }

        ISocios iSocios = new ISociosImpl();
        List<Socios> socios;
        if(nombreR.isSelected()){
            socios = iSocios.buscarNombreSocio(id.getText());

        }else if(telefonoR.isSelected()){
            socios = iSocios.buscarTelefonoSocio(id.getText());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Opción");
            alert.setContentText("Elge una opción");
            alert.showAndWait();
            return;
        }

        tableView.getItems().addAll(socios);
    }

    public void listarSociosBBDD(ActionEvent actionEvent) {

        if(idTable.getCellValueFactory() == null){
            idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
            nombreTable.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            telefonoTable.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            direccionTable.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        }else{
            tableView.getItems().clear();
        }

        ISocios iSocios = new ISociosImpl();
        List<Socios> socios = iSocios.listarSocio();
        tableView.getItems().addAll(socios);
    }
}
