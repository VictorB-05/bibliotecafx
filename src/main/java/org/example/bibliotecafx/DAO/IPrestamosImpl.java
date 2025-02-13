package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entidades.Prestamos;
import org.example.bibliotecafx.entidades.Socios;
import org.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class IPrestamosImpl implements IPrestamos {
    @Override
    public void registrarPrestamos(Prestamos prestamo) {
        try(SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession()) {
            //transaccion
            session.beginTransaction();

            session.persist(prestamo);

            session.getTransaction();
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
    public void libroDevuelto(int id) {

    }
}
