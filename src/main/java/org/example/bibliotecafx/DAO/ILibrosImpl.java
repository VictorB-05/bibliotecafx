package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entidades.Libros;
import org.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ILibrosImpl implements ILibros {
    @Override
    public void addLibro(Libros libros) {
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {
            //transaccion
            session.beginTransaction();

            // guardar en mi bbdd
            session.persist(libros);
            session.getTransaction();
        }
    }

    @Override
    public void modificarLibros(Libros libro) {

    }

    @Override
    public boolean deleteLibros(int id) {
        return false;
    }

    @Override
    public List<Libros> buscarLibrosTitulo(String titulo) {
        return null;
    }

    @Override
    public List<Libros> buscarLibrosAutor(String Autor) {
        return null;
    }

    @Override
    public List<Libros> buscarLibrosISBN(int isbn) {
        return null;
    }

    @Override
    public List<Libros> buscarLibrosDisponibles() {
        return null;
    }
}
