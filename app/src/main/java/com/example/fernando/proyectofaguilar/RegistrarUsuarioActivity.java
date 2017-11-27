package com.example.fernando.proyectofaguilar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fernando.proyectofaguilar.Utilidades.Utilidades;

import javax.security.auth.callback.Callback;

public class RegistrarUsuarioActivity extends AppCompatActivity {
    EditText txtDocumentoId, txtCuenta, txtNombre, txtTelefono;

    private static final String LOG = RegistrarUsuarioActivity.class.getSimpleName();
    private Context context;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        context = this;
        setTitle("Registrar Usuario");

        txtDocumentoId = (EditText) findViewById(R.id.txtDocumentoId);
        txtCuenta = (EditText) findViewById(R.id.txtCuentaUsuario);
        txtNombre = (EditText) findViewById(R.id.txtNombreUsuario);
        txtTelefono = (EditText) findViewById(R.id.txtTelefonoUsuario);

        //Codigo para el boton de retroceso
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClick(View view){
        registrarUsuarios();
        //registrarUsuariosSQL();
    }

    private void registrarUsuariosSQL() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        //insert into usuario (id, cuenta, nombre, telefono) values (123, 'faguilar', 'fernando aguilar', '78912112')
        String insert = "INSERT INTO " + Utilidades.TABLA_USUARIO + " ("
                                       + Utilidades.CAMPO_ID + ", " + Utilidades.CAMPO_CUENTA + ", " + Utilidades.CAMPO_NOMBRE + ", " + Utilidades.CAMPO_TELEFONO + ") "
                                       + "VALUES ("
                                       + txtDocumentoId.getText().toString() + ", '" + txtCuenta.getText().toString() + "', '" + txtNombre.getText().toString() + "', '" + txtTelefono.getText().toString() + "')";

        db.execSQL(insert);
        Toast.makeText(getApplicationContext(), "Registro exitoso !!", Toast.LENGTH_SHORT).show();

        db.close();
    }

    private void registrarUsuarios() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, txtDocumentoId.getText().toString());
        values.put(Utilidades.CAMPO_CUENTA, txtCuenta.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, txtNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, txtTelefono.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);
        //Toast.makeText(getApplicationContext(), "ID Registro: " + idResultante, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Registro exitoso !!", Toast.LENGTH_SHORT).show();

        db.close();
    }
}
