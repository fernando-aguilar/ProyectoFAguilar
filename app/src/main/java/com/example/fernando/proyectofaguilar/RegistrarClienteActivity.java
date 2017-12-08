package com.example.fernando.proyectofaguilar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fernando.proyectofaguilar.adaptadores.ListaClientesAdapter;
import com.example.fernando.proyectofaguilar.entidades.Cliente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegistrarClienteActivity extends AppCompatActivity {
    DatabaseReference databaseCliente;

    ListView listViewClientes;
    List<Cliente> clienteList;

    EditText txtNombreCliente, txtCorreoCliente, txtFechaNacimiento;
    Spinner ddlEstadoCivil;

    @Override
    public View findViewById(@IdRes int id) {
        return super.findViewById(id);
    }

    Button btnRegistrarCliente;

    private static final String LOG = RegistrarClienteActivity.class.getSimpleName();
    private Context context;
    private Toolbar toolbar;

    static final int DATE_DIALOG_ID = 0;
    private int año;
    private int mes;
    private int dia;

    protected android.app.Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, FechaListener, año, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener FechaListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int yearOf, int monthOfYear, int dayOfMonth) {
                    //Toast.makeText(RegistrarClienteActivity.this, "Escogiste: " + dayOfMonth + "-" + monthOfYear + "-" + yearOf, Toast.LENGTH_SHORT).show();
                    txtFechaNacimiento.setText(dayOfMonth + "/" + monthOfYear + "/" + yearOf);
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        //FireBase
        databaseCliente = FirebaseDatabase.getInstance().getReference("clientes");

        context = this;
        setTitle("Administrar Cliente (Firebase)");

        //Codigo para el boton de retroceso
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNombreCliente = (EditText) findViewById(R.id.txtNombreCliente);
        txtCorreoCliente = (EditText) findViewById(R.id.txtCorreoCliente);
        txtFechaNacimiento = (EditText) findViewById(R.id.txtFechaNacimiento);
        ddlEstadoCivil = (Spinner) findViewById(R.id.ddlEstadoCivil);

        btnRegistrarCliente = (Button) findViewById(R.id.btnRegistrarCliente);

        listViewClientes = (ListView) findViewById(R.id.listViewClientes);
        clienteList = new ArrayList<>();

        txtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                año = c.get(Calendar.YEAR);
                mes = c.get(Calendar.MONTH);
                dia = c.get(Calendar.DAY_OF_MONTH);

                showDialog(DATE_DIALOG_ID);
            }
        });

        btnRegistrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spAdicionarCliente();
            }
        });

        listViewClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Cliente cliente = clienteList.get(position);
                showUpdateDialog(cliente.getIdCliente(), cliente.getNombreCliente(), cliente.getEmailCliente(), cliente.getFechaNacimiento(), cliente.getEstadoCivil());

                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseCliente.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clienteList.clear();

                for (DataSnapshot clienteSnapshot : dataSnapshot.getChildren()) {
                    Cliente cliente = clienteSnapshot.getValue(Cliente.class);

                    clienteList.add(cliente);
                }

                ListaClientesAdapter adapter = new ListaClientesAdapter(RegistrarClienteActivity.this, clienteList);
                listViewClientes.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void spAdicionarCliente() {
        String nombrecliente = txtNombreCliente.getText().toString().trim();
        String emailcliente = txtCorreoCliente.getText().toString().trim();
        String fechaNacimiento = txtFechaNacimiento.getText().toString().trim();
        String estadoCivil = ddlEstadoCivil.getSelectedItem().toString();

        if (TextUtils.isEmpty(nombrecliente)) {
            txtNombreCliente.setError("El nombre del cliente es requerido");
            txtNombreCliente.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(emailcliente)) {
            txtCorreoCliente.setError("El email del cliente es requerido");
            txtCorreoCliente.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(emailcliente)) {
            txtFechaNacimiento.setError("La fecha de nacimiento es requerida");
            txtFechaNacimiento.requestFocus();
            return;
        }

        String id = databaseCliente.push().getKey(); //id que genera firebase
        Cliente cliente = new Cliente(id, nombrecliente, emailcliente, fechaNacimiento, estadoCivil);
        databaseCliente.child(id).setValue(cliente);

        //Toast.makeText(context, "Registro de cliente exitoso !!", Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarClienteActivity.this);
        builder.setIcon(R.drawable.ok3).
                setTitle("Mensaje").
                setMessage("La información del cliente se registro exitosamente");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        spLimpiarControles();
    }

    private boolean spModificarCliente(String id, String nombreCliente, String emailCliente, String fechaNacimento, String estadoCivil) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("clientes").child(id);

        Cliente cliente = new Cliente(id, nombreCliente, emailCliente, fechaNacimento, estadoCivil);
        databaseReference.setValue(cliente);

        //Toast.makeText(context, "La información del cliente se actualizó exitosamente !!", Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarClienteActivity.this);
        builder.setIcon(R.drawable.ok3).
                setTitle("Mensaje").
                setMessage("La información del cliente se actualizó exitosamente");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        return true;
    }

    private void spEliminarCliente(String id) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("clientes").child(id);
        databaseReference.removeValue();

        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarClienteActivity.this);
        builder.setIcon(R.drawable.ok3).
                setTitle("Mensaje").
                setMessage("El cliente se eliminó exitosamente");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Despliega una ventena para modificar o eliminar clientes
    private void showUpdateDialog(final String idCliente, String nombreCliente, String emailCliente, String fechaNacimiento, String estadoCivil) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText txtNombre = (EditText) dialogView.findViewById(R.id.edtNombre);
        final EditText txtEmail = (EditText) dialogView.findViewById(R.id.edtEmail);
        final EditText txtFechaNacimiento = (EditText) dialogView.findViewById(R.id.edtFechaNacimiento);
        final Spinner ddlEstadoCivil = (Spinner) dialogView.findViewById(R.id.spiEstadoCivil);

        final Button btnUpdate = (Button) dialogView.findViewById(R.id.btnUpdate);
        final Button btnDelete = (Button) dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle("Cliente: " + nombreCliente);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String fechanacimiento = txtFechaNacimiento.getText().toString().trim();
                String estadocivil = ddlEstadoCivil.getSelectedItem().toString();

                if (TextUtils.isEmpty(nombre)) {
                    txtNombre.setError("nombre requerido");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    txtEmail.setError("email requerido");
                    return;
                }

                if (TextUtils.isEmpty(fechanacimiento)) {
                    txtFechaNacimiento.setError("fecha nacimiento requerido");
                    return;
                }

                spModificarCliente(idCliente, nombre, email, fechanacimiento, estadocivil);
                alertDialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spEliminarCliente(idCliente);
                alertDialog.dismiss();
            }
        });
    }

    private void spLimpiarControles() {
        txtNombreCliente.setText("");
        txtCorreoCliente.setText("");
        txtFechaNacimiento.setText("");
    }
}
