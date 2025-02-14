package org.example.bibliotecafx.interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bibliotecafx.DAO.*;
import org.example.bibliotecafx.entidades.Autores;
import org.example.bibliotecafx.entidades.Libros;
import org.example.bibliotecafx.entidades.Prestamos;
import org.example.bibliotecafx.entidades.Socios;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class GestionPrestamos {
    @FXML
    private AnchorPane scene2;
    @FXML
    private AnchorPane ventana;
    @FXML
    private TextField idLibro;
    @FXML
    private TextField idSocio;
    @FXML
    private DatePicker fechaInico;
    @FXML
    private DatePicker fechaFin;
    @FXML
    private TableView<Prestamos> tablePrestamos;
    @FXML
    private TableColumn<Prestamos,Integer> idTablePrestamos;
    @FXML
    private TableColumn<Prestamos,Libros> libroTable;
    @FXML
    private TableColumn<Prestamos, LocalDate> fechaInicioTable;
    @FXML
    private TableColumn<Prestamos,LocalDate> fechaFinTable;
    @FXML
    private TableColumn<Prestamos, Boolean> debueltoTable;
    @FXML
    private TableView<Libros> tableLibros;
    @FXML
    private TableColumn<Libros,Integer> idTableLibros;
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
    protected void addPrestamos(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/prestamos/PrestamosAdd.fxml");
    }

    @FXML
    protected void addPrestamosBBDD(ActionEvent actionEvent) {
        ILibros iLibros = new ILibrosImpl();
        ISocios iSocios = new ISociosImpl();
        IPrestamos iPrestamos = new IPrestamosImpl();
        Libros libro;
        Socios socios;
        String idLibrosAux = idLibro.getText().trim();
        String idSocioAux = idSocio.getText().trim();
        if(!idLibrosAux.isEmpty()){
            try{
                libro = iLibros.buscarLibro(Integer.parseInt(idLibrosAux));
                if (libro==null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Libro no encontrado");
                    alert.setContentText("El ID del libro ingresado no existe en la base de datos.");
                    alert.showAndWait();
                    return;
                }
            }catch (NumberFormatException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Formato no valido");
                alert.setContentText("El ID contien un carracter no valido del libro.");
                alert.showAndWait();
                return;
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Formato no valido");
            alert.setContentText("El ID no puede ser nulo del libro.");
            alert.showAndWait();
            return;
        }
        if(!iPrestamos.libroDisponible(libro)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Libro no disponible");
            alert.setContentText("El libro que se quiere prestar esta en prestamo ya en la base de datos");
            alert.showAndWait();
            return;
        }
        if(!idSocioAux.isEmpty()){
            try{
                socios = iSocios.buscarSocio(Integer.parseInt(idSocioAux));
                if (socios==null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Socios no encontrado");
                    alert.setContentText("El ID del socio ingresado no existe en la base de datos.");
                    alert.showAndWait();
                    return;
                }
            }catch (NumberFormatException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Formato no valido");
                alert.setContentText("El ID contien un carracter no valido del socio.");
                alert.showAndWait();
                return;
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Formato no valido");
            alert.setContentText("El ID no puedo ser nulo del socio.");
            alert.showAndWait();
            return;
        }
        Prestamos prestamo = new Prestamos(null,libro,socios,fechaInico.getValue(),fechaFin.getValue());
        iPrestamos.registrarPrestamos(prestamo);
    }

    @FXML
    public void buscarLibros(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana,"/org/example/bibliotecafx/prestamos/LibrosListarPrestados.fxml");
    }

    @FXML
    public void historialSocio(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana, "/org/example/bibliotecafx/prestamos/HistorialBuscar.fxml");
    }

    @FXML
    public void historialSocioBBDD(ActionEvent actionEvent) {
        if(idTablePrestamos.getCellValueFactory() == null){
            idTablePrestamos.setCellValueFactory(new PropertyValueFactory<>("id"));
            libroTable.setCellValueFactory(new PropertyValueFactory<>("libro"));
            fechaInicioTable.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
            fechaFinTable.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
            debueltoTable.setCellValueFactory(new PropertyValueFactory<>("devuelto"));
        }else{
            tablePrestamos.getItems().clear();
        }
        ISocios iSocios = new ISociosImpl();
        Socios socios;
        String idSocioAux = idSocio.getText().trim();
        System.out.println(idSocioAux);
        if(!idSocioAux.isEmpty()){
            try{
                socios = iSocios.buscarSocio(Integer.parseInt(idSocioAux));
                if (socios==null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Libro no encontrado");
                    alert.setContentText("El ID del socio ingresado no existe en la base de datos.");
                    alert.showAndWait();
                    return;
                }
            }catch (NumberFormatException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Formato no valido");
                alert.setContentText("El ID contien un carracter no valido.");
                alert.showAndWait();
                return;
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Formato no valido");
            alert.setContentText("El ID no puedo ser nulo.");
            alert.showAndWait();
            return;
        }
        IPrestamos iPrestamos = new IPrestamosImpl();
        List<Prestamos> prestamos = iPrestamos.historialPrestamo(socios);
        for(Prestamos prestamos1 : prestamos){
            System.out.println(prestamos1);
        }
        tablePrestamos.getItems().addAll(prestamos);
    }

    public void listarLibrosBBDD(ActionEvent actionEvent) {
        if(idTableLibros.getCellValueFactory() == null){
            idTableLibros.setCellValueFactory(new PropertyValueFactory<>("id"));
            tituloTable.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            isbnTable.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            editorialTable.setCellValueFactory(new PropertyValueFactory<>("editorial"));
            anyoTable.setCellValueFactory(new PropertyValueFactory<>("anyo"));
            autorTable.setCellValueFactory(new PropertyValueFactory<>("autores"));
        }else{
            tableLibros.getItems().clear();
        }
        ILibros iLibros = new ILibrosImpl();
        List<Libros> libros = iLibros.buscarLibrosPrestados();

        tableLibros.getItems().addAll(libros);
    }

    public void devolverLibros(ActionEvent actionEvent) throws IOException {
        new SeceneSwitch(ventana, "/org/example/bibliotecafx/prestamos/LibrosDevolver.fxml");
    }

    public void devolverLibrosBBDD(ActionEvent actionEvent) {
        ILibros iLibros = new ILibrosImpl();
        Libros libro;
        String idLibrosAux = idLibro.getText().trim();
        if(!idLibrosAux.isEmpty()){
            try{
                libro = iLibros.buscarLibro(Integer.parseInt(idLibrosAux));
                if (libro==null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Libro no encontrado");
                    alert.setContentText("El ID del libro ingresado no existe en la base de datos.");
                    alert.showAndWait();
                    return;
                }
            }catch (NumberFormatException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Formato no valido");
                alert.setContentText("El ID contien un carracter no valido del libro.");
                alert.showAndWait();
                return;
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Formato no valido");
            alert.setContentText("El ID no puede ser nulo del libro.");
            alert.showAndWait();
            return;
        }
        IPrestamos iPrestamos = new IPrestamosImpl();
        iPrestamos.libroDevuelto(libro);
    }
}
