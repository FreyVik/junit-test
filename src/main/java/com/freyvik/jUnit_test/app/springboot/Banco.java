package com.freyvik.jUnit_test.app.springboot;

public class Banco {
    private Long id;
    private String nombre;
    private Integer totalTransfers;

    public Banco() {
    }

    public Banco(Long id, String nombre, Integer totalTransfers) {
        this.id = id;
        this.nombre = nombre;
        this.totalTransfers = totalTransfers;
    }

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

    public Integer getTotalTransfers() {
        return totalTransfers;
    }
    public void setTotalTransfers(Integer totalTransfers) {
        this.totalTransfers = totalTransfers;
    }
}
