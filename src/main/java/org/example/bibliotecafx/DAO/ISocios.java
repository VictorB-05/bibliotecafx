package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entidades.Socios;

import java.util.List;

public interface ISocios {
    void addSocios(Socios socio);
    void modificarSocios(Socios socio);
    Socios buscarSocio(int id);
    boolean deleteSocio(int id);
    List<Socios> buscarNombreSocio(String nombre);
    List<Socios> buscarTelefonoSocio(String telefono);
    List<Socios> listarSocio();
}
