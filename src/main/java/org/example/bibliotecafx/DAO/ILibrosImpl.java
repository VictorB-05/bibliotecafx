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
        boolean res = true;
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {
            //transaccion
            session.beginTransaction();

            Libros libro = session.get(Libros.class,id);

            if(libro!= null){
                session.delete(libro);
            }else{
                res = false;
            }

            // guardar en mi bbdd
            session.getTransaction();
        }
        return res;
    }

    @Override
    public List<Libros> buscarLibrosTitulo(String titulo) {
        List<Libros> libro;
        // usamos try with para usar el auto-close de session
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            //HQL (SQL de hibernate) es caseSensitive (la mayusculas si cuentan)
            libro = session.createQuery("from Libros where titulo = :titulo", Libros.class)
                    .setParameter("titulo", titulo)
                    .list();
        }
        return libro;
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
