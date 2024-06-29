package com.eguino.app.streaming.models;

import com.eguino.app.streaming.models.enums.Dispositivo;

import java.time.LocalDate;

public class Historial {
    private Long id;
    private Long usuarioID;
    private Long mediaID;
    private LocalDate fecha;
    private Integer progreso;
    private Dispositivo dispositivo;
    private String ubicacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioID;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioID = usuarioID;
    }

    public Long getMediaID() {
        return mediaID;
    }

    public void setMediaID(Long mediaID) {
        this.mediaID = mediaID;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getProgreso() {
        return progreso;
    }

    public void setProgreso(Integer progreso) {
        this.progreso = progreso;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
