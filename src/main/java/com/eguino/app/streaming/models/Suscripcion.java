package com.eguino.app.streaming.models;

public class Suscripcion {
    private Long id;
    private String nombre;
    private Integer cantDispositivos;
    private Double costo;
    private String calidadMax;

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

    public Integer getCantDispositivos() {
        return cantDispositivos;
    }

    public void setCantDispositivos(Integer cantDispositivos) {
        this.cantDispositivos = cantDispositivos;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getCalidadMax() {
        return calidadMax;
    }

    public void setCalidadMax(String calidadMax) {
        this.calidadMax = calidadMax;
    }
}
