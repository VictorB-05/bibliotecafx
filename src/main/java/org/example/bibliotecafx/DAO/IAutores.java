package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entidades.Autores;

import java.util.List;

public interface IAutores {
    boolean buscarAutor(int id);
    void addAutores(Autores autor);
    void modificarAutor(Autores autor);
    boolean deleteAutor(int id);
    List<Autores> buscarAutores(String nombre);
    List<Autores> listarAutores();
}
