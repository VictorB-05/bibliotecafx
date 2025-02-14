package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entidades.Libros;
import org.example.bibliotecafx.entidades.Prestamos;
import org.example.bibliotecafx.entidades.Socios;

import java.util.List;

public interface IPrestamos {
    void registrarPrestamos(Prestamos prestamo);
    List<Prestamos> historialPrestamo(Socios socio);
    void libroDevuelto(Libros libro);
    boolean libroDisponible (Libros libro);
}
