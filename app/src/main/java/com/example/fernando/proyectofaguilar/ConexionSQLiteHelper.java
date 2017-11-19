package com.example.fernando.proyectofaguilar;

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
    }

    @Override
    //refresca los scripts
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST usuario");
        onCreate(db);
    }
}
