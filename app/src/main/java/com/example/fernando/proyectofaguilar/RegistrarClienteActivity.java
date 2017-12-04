package com.example.fernando.proyectofaguilar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fernando.proyectofaguilar.entidades.Cliente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarClienteActivity extends AppCompatActivity {
    DatabaseReference databaseCliente;

    EditText txtNombreCliente, txtCorreoCliente;
    Button btnRegistrarCliente;

    private static final String LOG = RegistrarClienteActivity.class.getSimpleName();
    private Context context;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        //FireBase
        databaseCliente = FirebaseDatabase.getInstance().getReference("clientes");


        context = this;
        setTitle("Registrar Cliente");

        //Codigo para el boton de retroceso
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNombreCliente = (EditText) findViewById(R.id.txtNombreCliente);
        txtCorreoCliente = (EditText) findViewById(R.id.txtCorreoCliente);
        btnRegistrarCliente = (Button) findViewById(R.id.btnRegistrarCliente);

        btnRegistrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarCliente();
            }
        });
    }

    private void adicionarCliente() {
        String nombrecliente = txtNombreCliente.getText().toString().trim();
        String emailcliente = txtCorreoCliente.getText().toString().trim();

        if (!TextUtils.isEmpty(nombrecliente)) {
            String id = databaseCliente.push().getKey(); //id que genera firebase
            Cliente cliente = new Cliente(id, nombrecliente, emailcliente);
            databaseCliente.child(id).setValue(cliente);

            Toast.makeText(context, "Registro de cliente exitoso !!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "El nombre del cliente es requerido", Toast.LENGTH_LONG).show();
        }
    }

}
