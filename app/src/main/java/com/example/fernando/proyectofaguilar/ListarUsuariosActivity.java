package com.example.fernando.proyectofaguilar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fernando.proyectofaguilar.Utilidades.Utilidades;
import com.example.fernando.proyectofaguilar.adaptadores.ListaPersonasAdapter;
import com.example.fernando.proyectofaguilar.entidades.Usuario;

import java.util.ArrayList;

public class ListarUsuariosActivity extends AppCompatActivity {

    ArrayList<Usuario> listaUsuario;
    RecyclerView recyclerViewUsuarios;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);
        listaUsuario = new ArrayList<>();

        recyclerViewUsuarios = (RecyclerView) findViewById(R.id.recyclerPersonas);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaPersonasAdapter adapter = new ListaPersonasAdapter(listaUsuario);
        recyclerViewUsuarios.setAdapter(adapter);
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario = null;
        // listaUsuarios=new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO, null);

        while (cursor.moveToNext()) {
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setCuenta(cursor.getString(1));
            usuario.setNombre(cursor.getString(2));
            usuario.setTelefono(cursor.getString(3));

            listaUsuario.add(usuario);
        }
    }

//    private void llenarListaUsuarios() {
//        listaUsuario.add(new Usuario(1, "Cristian", "548526"));
//        listaUsuario.add(new Usuario(1, "Cristian", "548526"));
//        listaUsuario.add(new Usuario(1, "Cristian", "548526"));
//        listaUsuario.add(new Usuario(1, "Cristian", "548526"));
//        listaUsuario.add(new Usuario(1, "Cristian", "548526"));
//        listaUsuario.add(new Usuario(1, "Cristian", "548526"));
//        listaUsuario.add(new Usuario(1, "Cristian", "548526"));
//    }
}
