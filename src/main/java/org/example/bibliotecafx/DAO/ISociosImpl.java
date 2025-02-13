package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entidades.Socios;
import org.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ISociosImpl implements ISocios {
    @Override
    public void addSocios(Socios socio) {
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {
            //transaccion
            session.beginTransaction();

            session.persist(socio);

            session.getTransaction();
        }
    }

    @Override
    public void modificarSocios(Socios socio) {
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.update(socio);

            session.getTransaction().commit();
        }
    }

    @Override
    public Socios buscarSocio(int id) {
        Socios socio = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            //transaccion
            session.beginTransaction();

            socio = session.get(Socios.class,id);

            session.getTransaction();
        }
        return socio;
    }

    @Override
    public boolean deleteSocio(int id) {
        boolean res = true;
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {
            //transaccion
            session.beginTransaction();

            Socios socio = session.get(Socios.class,id);

            if(socio!= null){
                session.createQuery("delete from Socios where id = :id",Socios.class)
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
    public List<Socios> buscarNombreSocio(String nombre) {
        List<Socios> socios;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            socios = session.createQuery("from Socios where nombre = :Nombre",Socios.class)
                    .setParameter("Nombre", nombre)
                    .list();
        }

        return socios;
    }

    @Override
    public List<Socios> buscarTelefonoSocio(String telefono) {
        List<Socios> socios;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            socios = session.createQuery("from Socios where telefono = :Telefono",Socios.class)
                    .setParameter("Telefono", telefono)
                    .list();
        }

        return socios;
    }

    @Override
    public List<Socios> listarSocio() {
        List<Socios> socios;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            socios = session.createQuery("from Socios",Socios.class)
                    .list();
        }

        return socios;
    }
}
