package com.eguino.app.streaming.models;

import java.time.LocalDate;

public class Comentario {
    private Long id;
    private Long usuarioID;
    private Long mediaID;
    private String comentario;
    private Integer calificacion;
    private LocalDate fecha;
    private Integer meGusta;
    private Long respuestaID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Long getMediaID() {
        return mediaID;
    }

    public void setMediaID(Long mediaID) {
        this.mediaID = mediaID;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getMeGusta() {
        return meGusta;
    }

    public void setMeGusta(Integer meGusta) {
        this.meGusta = meGusta;
    }

    public Long getRespuestaID() {
        return respuestaID;
    }

    public void setRespuestaID(Long respuestaID) {
        this.respuestaID = respuestaID;
    }
}
