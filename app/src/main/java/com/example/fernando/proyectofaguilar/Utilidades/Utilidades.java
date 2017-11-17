package com.example.fernando.proyectofaguilar.Utilidades;

/**
 * Created by Fernando on 16/11/2017.
 */

public class Utilidades {

    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_CUENTA = "cuentausuario";
    public static final String CAMPO_NOMBRE = "nombreusuario";
    public static final String CAMPO_TELEFONO = "telefono";

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " +  TABLA_USUARIO + " (" + CAMPO_ID   + " INTEGER, " + CAMPO_CUENTA + " TEXT, " + CAMPO_NOMBRE + " TEXT, " + CAMPO_TELEFONO + " TEXT)";
}
