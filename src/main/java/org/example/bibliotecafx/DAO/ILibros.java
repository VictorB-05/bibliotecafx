package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entidades.Libros;

import java.util.List;

public interface ILibros {
    boolean buscarLibro(int id);
    void addLibro(Libros libros);
    void modificarLibros(Libros libro);
    boolean deleteLibros(int id);
    List<Libros> buscarLibrosTitulo(String titulo);
    List<Libros> buscarLibrosAutor(int id);
    List<Libros> buscarLibrosISBN(String isbn);
    List<Libros> buscarLibrosDisponibles();

}
