package com.example.fernando.proyectofaguilar;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fernando.proyectofaguilar.Utilidades.Utilidades;

public class RegistrarUsuarioActivity extends AppCompatActivity {
    EditText campoID, campoCuenta, campoNombre, campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        setTitle("Registrar Usuario");

        campoID = (EditText) findViewById(R.id.txtID);
        campoCuenta = (EditText) findViewById(R.id.txtCuentaUsuario);
        campoNombre = (EditText) findViewById(R.id.txtNombreUsuario);
        campoTelefono = (EditText) findViewById(R.id.txtTelefonoUsuario);
    }

    public void onClick(View view){
        registrarUsuarios();
    }

    private void registrarUsuarios() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, campoID.getText().toString());
        values.put(Utilidades.CAMPO_CUENTA, campoCuenta.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, campoTelefono.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);
        Toast.makeText(getApplicationContext(), "ID Registro: " + idResultante, Toast.LENGTH_SHORT).show();
    }
}
