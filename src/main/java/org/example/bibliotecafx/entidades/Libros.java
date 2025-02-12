package org.example.bibliotecafx.entidades;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Libros implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String isbn;

    private String editorial;

    private int anyo;

    @ManyToOne
    @JoinColumn(name = "idAutor", nullable = true) // Foreign key
    private Autores autores;

    public Libros(Integer id,String titulo, String isbn, String editorial, int anyo) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.editorial = editorial;
        this.anyo = anyo;
        autores = new Autores();
        autores.setNombre("anonimo");
    }

    public Libros(Integer id, String titulo, String isbn, String editorial, int anyo, Autores autores) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.editorial = editorial;
        this.anyo = anyo;
        this.autores = autores;
    }

    public Libros() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public Autores getAutores() {
        return autores;
    }

    public void setAutores(Autores autores) {
        this.autores = autores;
    }
}
