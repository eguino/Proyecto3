package com.eguino.app.streaming.repositories;

import com.eguino.app.streaming.models.Comentario;
import com.eguino.app.streaming.models.Suscripcion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuscripcionRepository implements IRepository<Suscripcion> {
    private Connection conn;

    public SuscripcionRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Suscripcion> listar() throws SQLException {
        List<Suscripcion> suscripciones = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM SUSCRIPCION")) {
            while (rs.next()){
                Suscripcion a = this.getSuscripcion(rs);
                suscripciones.add(a);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return suscripciones;
    }

    @Override
    public Suscripcion getById(Long id) throws SQLException {
        Suscripcion suscripcion = null;
        try (PreparedStatement stmt =
                     conn.prepareStatement("SELECT * FROM suscripcion WHERE ID_SUSCRIPCION= ?")) {
            stmt.setLong(1,id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()){
                    suscripcion = this.getSuscripcion(rs);
                }
            }
        }
        return suscripcion;
    }

    @Override
    public void guardar(Suscripcion suscripcion) throws SQLException {
        String sql = "";
        if (suscripcion.getId() != null && suscripcion.getId() > 0) {
            sql = "update suscripcion set nombre=?, cant_dispositivos=?, " +
                    "costo=?, calidad_max=?" +
                    "where id_suscripcion=?";
        } else {
            sql = "insert into suscripcion(id_suscripcion, nombre, " +
                    "cant_dispositivos, costo, calidad_max) " +
                    "values (SEQUENCE1.NEXTVAL,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            if(suscripcion.getId() != null && suscripcion.getId() > 0){
                stmt.setString(1,suscripcion.getNombre());
                stmt.setInt(2,suscripcion.getCantDispositivos());
                stmt.setDouble(3,suscripcion.getCosto());
                stmt.setString(4,suscripcion.getCalidadMax());
                stmt.setLong(5, suscripcion.getId());
            } else {
                stmt.setString(1,suscripcion.getNombre());
                stmt.setInt(2,suscripcion.getCantDispositivos());
                stmt.setDouble(3,suscripcion.getCosto());
                stmt.setString(4,suscripcion.getCalidadMax());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from suscripcion where id_suscripcion =?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }

    private Suscripcion getSuscripcion(ResultSet rs) throws SQLException {
        Suscripcion a = new Suscripcion();
        a.setId(rs.getLong("ID_SUSCRIPCION"));
        a.setNombre(rs.getString("NOMBRE"));
        a.setCantDispositivos(rs.getInt("CANT_DISPOSITIVOS"));
        a.setCosto(rs.getDouble("COSTO"));
        a.setCalidadMax(rs.getString("CALIDAD_MAX"));
        return a;
    }
}
