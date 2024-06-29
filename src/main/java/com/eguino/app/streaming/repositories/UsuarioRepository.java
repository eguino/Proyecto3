package com.eguino.app.streaming.repositories;

import com.eguino.app.streaming.models.Usuario;
import com.eguino.app.streaming.models.enums.Paises;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements IRepository<Usuario> {
    private Connection conn;

    public UsuarioRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO")) {
            while (rs.next()){
                Usuario a = this.getUsuario(rs);
                usuarios.add(a);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    @Override
    public Usuario getById(Long id) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt =
                     conn.prepareStatement("SELECT * FROM usuario WHERE ID_USUARIO= ?")) {
            stmt.setLong(1,id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()){
                    usuario = this.getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql = "";
        if (usuario.getId() != null && usuario.getId() > 0) {
            sql = "update usuario set nombre=?, correo=?, clave=?, " +
                    "fecha_registro=?, suscripcion_id=?, " +
                    "num_perfiles=?, pais=? " +
                    "where id_usuario=?";
        } else {
            sql = "insert into usuario(id_usuario, nombre, correo, " +
                    "clave, fecha_registro, suscripcion_id, " +
                    "num_perfiles, pais) " +
                    "values (SEQUENCE2.NEXTVAL,?,?,?,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            if(usuario.getId() != null && usuario.getId() > 0){
                stmt.setString(1,usuario.getNombre());
                stmt.setString(2,usuario.getCorreo());
                stmt.setString(3,usuario.getClave());
                stmt.setDate(4,Date.valueOf(usuario.getFechaRegistro()));
                stmt.setLong(5, usuario.getSuscripcionId());
                stmt.setInt(6, usuario.getNumPerfiles());
                stmt.setString(7, usuario.getPais().toString());
                stmt.setLong(8, usuario.getId());
            } else {
                stmt.setString(1,usuario.getNombre());
                stmt.setString(2,usuario.getCorreo());
                stmt.setString(3,usuario.getClave());
                stmt.setDate(4,Date.valueOf(usuario.getFechaRegistro()));
                stmt.setLong(5, usuario.getSuscripcionId());
                stmt.setInt(6, usuario.getNumPerfiles());
                stmt.setString(7, usuario.getPais().toString());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from usuario where id_usuario =?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }

    private Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario a = new Usuario();
        SuscripcionRepository suscripcionRepository = new SuscripcionRepository(conn);
        a.setId(rs.getLong("ID_USUARIO"));
        a.setNombre(rs.getString("NOMBRE"));
        a.setCorreo(rs.getString("CORREO"));
        a.setClave(rs.getString("CLAVE"));
        a.setFechaRegistro(rs.getDate("FECHA_REGISTRO").toLocalDate());
        a.setSuscripcionId(rs.getLong("SUSCRIPCION_ID"));
        a.setNumPerfiles(rs.getInt("NUM_PERFILES"));

        String paisString = rs.getString("PAIS");
        Paises pais = Paises.valueOf(paisString.toUpperCase());
        a.setPais(pais);

        return a;
    }
}
