package com.example.fernando.proyectofaguilar.entidades;

/**
 * Created by Fernando on 16/11/2017.
 */

public class Usuario {
    private Integer id;
    private String cuentausuario;
    private String nombreusuario;
    private String telefono;

    public Usuario(Integer id, String cuentausuario, String nombreusuario, String telefono) {
        this.id = id;
        this.cuentausuario = cuentausuario;
        this.nombreusuario = nombreusuario;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCuentausuario() {
        return cuentausuario;
    }

    public void setCuentausuario(String cuentausuario) {
        this.cuentausuario = cuentausuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
