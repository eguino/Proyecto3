package com.eguino.app.streaming.services;

import com.eguino.app.streaming.models.Suscripcion;
import com.eguino.app.streaming.repositories.IRepository;
import com.eguino.app.streaming.repositories.SuscripcionRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SuscripcionService implements IService<Suscripcion> {
    private IRepository<Suscripcion> suscripcionRepo;

    public SuscripcionService(Connection conn) {
        suscripcionRepo = new SuscripcionRepository(conn);
    }

    @Override
    public List<Suscripcion> listar() {
        try{
            return suscripcionRepo.listar();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Suscripcion> getById(Long id) {
        try{
            return Optional.ofNullable(suscripcionRepo.getById(id));
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Suscripcion suscripcion) {
        try{
            suscripcionRepo.guardar(suscripcion);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try{
            suscripcionRepo.eliminar(id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }
}
