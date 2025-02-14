package org.example.bibliotecafx.DAO;

import jakarta.persistence.NoResultException;
import org.example.bibliotecafx.entidades.Libros;
import org.example.bibliotecafx.entidades.Prestamos;
import org.example.bibliotecafx.entidades.Socios;
import org.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class IPrestamosImpl implements IPrestamos {
    @Override
    public void registrarPrestamos(Prestamos prestamo) {
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {
            //transaccion
            session.beginTransaction();

            session.persist(prestamo);

            session.getTransaction().commit();
        }
    }

    @Override
    public List<Prestamos> historialPrestamo(Socios socio) {
        List<Prestamos> prestamos;
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()){
            prestamos = session.createQuery("from Prestamos where socios = :socio", Prestamos.class)
                    .setParameter("socio",socio)
                    .list();
        }
        return prestamos;
    }

    @Override
    public void libroDevuelto(Libros libro) {
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()){

            Prestamos prestamo = session.createQuery("from Prestamos where libro = :libros AND devuelto = :de", Prestamos.class)
                    .setParameter("libros",libro)
                    .setParameter("de",false)
                    .getSingleResult();

            session.beginTransaction();

            prestamo.setDevuelto(true);
             session.merge(prestamo);

            session.getTransaction().commit();
        }
    }

    @Override
    public boolean libroDisponible(Libros libro) {
        boolean disponible;
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()){

            Prestamos prestamo = session.createQuery("from Prestamos where libro = :libros AND devuelto = :de", Prestamos.class)
                    .setParameter("libros",libro)
                    .setParameter("de",false)
                    .getSingleResult();

            disponible = prestamo.isDevuelto();
        }catch (NoResultException ex){
            disponible = true;
        }
        return disponible;
    }
}
