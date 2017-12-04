package com.example.fernando.proyectofaguilar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fernando.proyectofaguilar.entidades.Cliente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegistrarClienteActivity extends AppCompatActivity {
    DatabaseReference databaseCliente;

    EditText txtNombreCliente, txtCorreoCliente, txtFechaNacimiento;
    Spinner ddlSexo;
    Button btnRegistrarCliente;

    private static final String LOG = RegistrarClienteActivity.class.getSimpleName();
    private Context context;
    private Toolbar toolbar;

    static final int DATE_DIALOG_ID = 0;
    private int año;
    private int mes;
    private int dia;

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
        txtFechaNacimiento = (EditText) findViewById(R.id.txtFechaNacimiento);
        ddlSexo = (Spinner) findViewById(R.id.ddlSexo);
        btnRegistrarCliente = (Button) findViewById(R.id.btnRegistrarCliente);

        btnRegistrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarCliente();
            }
        });

//        txtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar c = Calendar.getInstance();
//                año = c.get(Calendar.YEAR);
//                mes = c.get(Calendar.MONTH);
//                dia = c.get(Calendar.DAY_OF_MONTH);
//
//                showDialog(DATE_DIALOG_ID);
//            }
//        });
    }

//    private DatePickerDialog.OnDateSetListener FechaListener =
//            new DatePickerDialog.OnDateSetListener()
//            {
//                public void onDateSet(DatePicker view, int yearOf, int monthOfYear, int dayOfMonth)
//                {
//                    Toast.makeText(RegistrarClienteActivity.this, "Escogiste: " + dayOfMonth + "-" + monthOfYear + "-" + yearOf, Toast.LENGTH_SHORT).show();
//                }
//            };

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
