package com.example.fernando.proyectofaguilar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fernando.proyectofaguilar.Utilidades.Utilidades;

public class ConsultarUsuarioActivity extends AppCompatActivity {
    EditText txtDocumentoId, txtCuenta, txtNombre, txtTelefono;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);

        txtDocumentoId = (EditText) findViewById(R.id.txtDocumentoId);
        txtCuenta = (EditText) findViewById(R.id.txtCuentaUsuario);
        txtNombre = (EditText) findViewById(R.id.txtNombreUsuario);
        txtTelefono = (EditText) findViewById(R.id.txtTelefonoUsuario);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBuscar:
                consultarUsuario();
                //consultarSql();
                break;
            case R.id.btnActualizar:
                actualizarUsuario();
                break;
            case R.id.btnEliminar:
                eliminarUsuario();
                break;
        }
    }

    private void consultarUsuario() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametrosConsulta = {txtDocumentoId.getText().toString()};
        String[] campos = {Utilidades.CAMPO_CUENTA, Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_ID + "=?", parametrosConsulta, null, null, null);
            cursor.moveToFirst();
            txtCuenta.setText(cursor.getString(0));
            txtNombre.setText(cursor.getString(1));
            txtTelefono.setText(cursor.getString(2));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_LONG).show();
            limpiarControles();
        }
    }

    private void eliminarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtDocumentoId.getText().toString()};

        db.delete(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "La información del usuario se eliminó correctamente", Toast.LENGTH_LONG).show();
        txtDocumentoId.setText("");
        db.close();

        limpiarControles();
    }

    private void actualizarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtDocumentoId.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_CUENTA, txtCuenta.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, txtNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, txtTelefono.getText().toString());

        db.update(Utilidades.TABLA_USUARIO, values, Utilidades.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "La información del usuario se actualizó correctamente", Toast.LENGTH_LONG).show();
        db.close();
    }

    private void limpiarControles() {
        txtCuenta.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
    }
}
