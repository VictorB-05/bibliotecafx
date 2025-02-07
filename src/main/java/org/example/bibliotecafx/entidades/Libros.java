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

    public Libros(String titulo, String isbn, String editorial, int anyo) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.editorial = editorial;
        this.anyo = anyo;
    }

    public Libros(String titulo, String isbn, String editorial, int anyo, Autores autores) {
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

    public String gettitulo() {
        return titulo;
    }

    public void settitulo(String titulo) {
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
}
