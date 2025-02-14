package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entidades.Autores;
import org.example.bibliotecafx.entidades.Libros;

import java.util.List;

public interface ILibros {
    Libros buscarLibro(int id);
    void addLibro(Libros libros);
    void modificarLibros(Libros libro);
    boolean deleteLibros(int id);
    List<Libros> buscarLibrosTitulo(String titulo);
    List<Libros> buscarLibrosAutor(Autores autor);
    List<Libros> buscarLibrosISBN(String isbn);
    List<Libros> buscarLibrosLibres();
    List<Libros> buscarLibrosPrestados();
}
