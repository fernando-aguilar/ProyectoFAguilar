package com.example.fernando.proyectofaguilar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        setTitle("Opciones de Usuario");
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
    }

    public void onClick(View view){
        Intent usuarioIntent = null;
        switch (view.getId()){
            case R.id.btnRegistrarUsuario:
                usuarioIntent = new Intent(UsuarioActivity.this, RegistrarUsuarioActivity.class);
                break;

            case R.id.btnConsultarUsuario:
                usuarioIntent = new Intent(UsuarioActivity.this, ConsultarUsuarioActivity.class);
                break;
        }

        if (usuarioIntent != null){
            startActivity(usuarioIntent);
        }
    }
}
