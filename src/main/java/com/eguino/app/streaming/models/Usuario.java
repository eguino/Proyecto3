package com.eguino.app.streaming.models;

import com.eguino.app.streaming.models.enums.Paises;

import java.time.LocalDate;

public class Usuario {
    private Long id;
    private String nombre;
    private String correo;
    private String clave;
    private LocalDate fechaRegistro;
    private Long suscripcionId;
    private Integer numPerfiles;
    private Paises pais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getSuscripcionId() {
        return suscripcionId;
    }

    public void setSuscripcionId(long suscripcionId) {
        this.suscripcionId = suscripcionId;
    }

    public Integer getNumPerfiles() {
        return numPerfiles;
    }

    public void setNumPerfiles(Integer numPerfiles) {
        this.numPerfiles = numPerfiles;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }
}
