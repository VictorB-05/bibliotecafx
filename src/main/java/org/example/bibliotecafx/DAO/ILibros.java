package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entidades.Libros;

import java.util.List;

public interface ILibros {
    void addLibro(Libros libros);
    void modificarLibros(Libros libro);
    boolean deleteLibros(int id);
    List<Libros> buscarLibrosTitulo(String titulo);
    List<Libros> buscarLibrosAutor(String Autor);
    List<Libros> buscarLibrosISBN(int isbn);
    List<Libros> buscarLibrosDisponibles();

}
