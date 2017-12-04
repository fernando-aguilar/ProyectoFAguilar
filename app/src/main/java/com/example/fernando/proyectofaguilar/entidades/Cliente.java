package com.example.fernando.proyectofaguilar.entidades;

/**
 * Created by Fernando on 03/12/2017.
 */

public class Cliente {
    String idCliente;
    String nombreCliente;
    String emailCliente;

    public Cliente(){

    }

    //Alt + Insert
    public Cliente(String idCliente, String nombreCliente, String emailCliente) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.emailCliente = emailCliente;
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
}


