package com.example.fernando.proyectofaguilar.entidades;

/**
 * Created by Fernando on 03/12/2017.
 */

public class Cliente {
    String idCliente;
    String nombreCliente;
    String emailCliente;

    String fechaNacimiento;
    String estadoCivil;

    public Cliente(){

    }

    //Alt + Insert
    public Cliente(String idCliente, String nombreCliente, String emailCliente, String fechaNacimiento, String estadoCivil) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.emailCliente = emailCliente;

        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
}


