package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entidades.Autores;
import org.example.bibliotecafx.entidades.Libros;
import org.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ILibrosImpl implements ILibros {

    @Override
    public Libros buscarLibro(int id) {
        Libros libro = null;
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {
            //transaccion
            session.beginTransaction();

            libro = session.get(Libros.class,id);


            session.getTransaction();
        }
        return libro;
    }

    @Override
    public void addLibro(Libros libros) {
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {
            //transaccion
            session.beginTransaction();

            session.persist(libros);

            session.getTransaction();
        }
    }

    @Override
    public void modificarLibros(Libros libro) {
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.update(libro);

            session.getTransaction().commit();
        }
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
                session.createNativeQuery("delete from Libros where id = :id",Libros.class)
                        .setParameter("id", id)
                        .executeUpdate();
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
        List<Libros> libro = new ArrayList<>();;
        // usamos try with para usar el auto-close de session
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            libro = session.createQuery("from Libros where titulo = :titulo", Libros.class)
                    .setParameter("titulo", titulo)
                    .list();
        }
        autoresAninimos(libro);
        return libro;
    }

    @Override
    public List<Libros> buscarLibrosAutor(Autores autor) {
        List<Libros> libro;
        // usamos try with para usar el auto-close de session
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            libro = session.createQuery("from Libros where autores = :autor", Libros.class)
                    .setParameter("autor", autor)
                    .list();
        }
        return libro;
    }

    @Override
    public List<Libros> buscarLibrosISBN(String isbn) {
        List<Libros> libro;
        // usamos try with para usar el auto-close de session
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            libro = session.createQuery("from Libros where isbn = :ISBN", Libros.class)
                    .setParameter("ISBN", isbn)
                    .list();
        }
        autoresAninimos(libro);
        return libro;
    }

    @Override
    public List<Libros> buscarLibrosLibres() {
        List<Libros> libro;
        // usamos try with para usar el auto-close de session
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            libro = session.createQuery("select l from Libros l " +
                                    "left join Prestamos p on l.id = p.libro.id " +
                                    "where p.devuelto = :estado OR p.id IS NULL", Libros.class)
                    .setParameter("estado", true)
                    .list();
        }
        autoresAninimos(libro);
        return libro;
    }

    @Override
    public List<Libros> buscarLibrosPrestados() {
        List<Libros> libro;
        // usamos try with para usar el auto-close de session
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            libro = session.createQuery("select p.libro from Prestamos p where p.devuelto = :estado", Libros.class)
                    .setParameter("estado", false)
                    .list();
        }
        autoresAninimos(libro);
        return libro;
    }

    public void autoresAninimos(List<Libros> libros){
        for(Libros libro : libros){
            if (libro.getAutores()==null){
                Autores anonimo = new Autores();
                anonimo.setNombre("Anonimo");
                libro.setAutores(anonimo);
            }
        }
    }
}
