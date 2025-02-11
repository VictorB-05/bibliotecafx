package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entidades.Autores;
import org.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class IAutoresImpl implements IAutores {

    @Override
    public boolean buscarAutor(int id) {
        boolean retru = false;
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {
            //transaccion
            session.beginTransaction();

            Autores autor = session.get(Autores.class,id);

            retru = autor!=null;

            session.getTransaction();
        }
        return retru;
    }

    @Override
    public void addAutores(Autores autor) {
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {
            //transaccion
            session.beginTransaction();

            session.persist(autor);

            session.getTransaction();
        }
    }

    @Override
    public void modificarAutor(Autores autor) {
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.merge(autor);

            session.getTransaction();
        }
    }

    @Override
    public boolean deleteAutor(int id) {
        boolean res = true;
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {
            //transaccion
            session.beginTransaction();

            Autores autor = session.get(Autores.class,id);

            if(autor != null){
                session.createNativeQuery("delete from Autores where id = :id",Autores.class)
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
    public List<Autores> buscarAutores(String nombre) {
        List<Autores> autores = new ArrayList<>();;
        // usamos try with para usar el auto-close de session
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            autores = session.createQuery("from Autores where nombre = :nombre", Autores.class)
                    .setParameter("nombre", nombre)
                    .list();
        }
        return autores;
    }

    @Override
    public List<Autores> listarAutores() {
        List<Autores> autores = new ArrayList<>();;
        // usamos try with para usar el auto-close de session
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            autores = session.createQuery("from Autores", Autores.class)
                    .list();
        }
        return autores;
    }
}
