package com.eguino.app.streaming.repositories;

import com.eguino.app.streaming.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository implements IRepository<Categoria> {
    private Connection conn;

    public CategoriaRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM CATEGORIA")) {
            while (rs.next()){
                Categoria a = this.getCategoria(rs);
                categorias.add(a);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return categorias;
    }

    @Override
    public Categoria getById(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement stmt =
                     conn.prepareStatement("SELECT * FROM CATEGORIA WHERE ID_CATEGORIA= ?")) {
            stmt.setLong(1,id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()){
                    categoria = this.getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
        String sql = "";
        if (categoria.getId() != null && categoria.getId() > 0) {
            sql = "update categoria set nombre=?, descripcion=?, " +
                    "fecha_creacion=?, activo=? " +
                    "where id_categoria=?";
        } else {
            sql = "insert into categoria(id_categoria, nombre, descripcion, " +
                    "fecha_creacion, activo) " +
                    "values (SEQUENCE3.NEXTVAL,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            if(categoria.getId() != null && categoria.getId() > 0){
                stmt.setString(1,categoria.getNombre());
                stmt.setString(2,categoria.getDescripcion());
                stmt.setDate(3,Date.valueOf(categoria.getFechaCreacion()));
                stmt.setString(4, categoria.getDescripcion());
                stmt.setLong(5, categoria.getId());
            } else {
                stmt.setString(1,categoria.getNombre());
                stmt.setString(2,categoria.getDescripcion());
                stmt.setDate(3,Date.valueOf(categoria.getFechaCreacion()));
                stmt.setString(4, categoria.getDescripcion());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from categoria where id_categoria =?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }

    private Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria a = new Categoria();
        SuscripcionRepository suscripcionRepository = new SuscripcionRepository(conn);
        a.setId(rs.getLong("ID_CATEGORIA"));
        a.setNombre(rs.getString("NOMBRE"));
        a.setDescripcion(rs.getString("DESCRIPCION"));
        a.setFechaCreacion(rs.getDate("FECHA_CREACION").toLocalDate());
        a.setActivo(rs.getBoolean("ACTIVO"));

        return a;
    }
}
