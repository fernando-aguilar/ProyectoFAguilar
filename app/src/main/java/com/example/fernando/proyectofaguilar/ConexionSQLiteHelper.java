package com.example.fernando.proyectofaguilar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fernando.proyectofaguilar.Utilidades.Utilidades;

/**
 * Created by Fernando on 16/11/2017.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    //constructor
    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    //generador de scripts
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        registrarUsuarios(db);
    }

    @Override
    //refresca los scripts
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST usuario");
        onCreate(db);
    }

    private void registrarUsuarios(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, 5974610);
        values.put(Utilidades.CAMPO_CUENTA, "faguilar");
        values.put(Utilidades.CAMPO_NOMBRE, "fernando aguilar");
        values.put(Utilidades.CAMPO_TELEFONO, "78912112");

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);
    }
}
