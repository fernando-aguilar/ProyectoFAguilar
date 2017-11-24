package com.example.fernando.proyectofaguilar.entidades;

/**
 * Created by Fernando on 16/11/2017.
 */

public class Usuario {
    private Integer id;
    private String cuenta;
    private String nombre;
    private String telefono;

    public Usuario(Integer id, String cuenta, String nombre, String telefono) {
        this.id = id;
        this.cuenta = cuenta;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Usuario(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
