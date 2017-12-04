package com.example.fernando.proyectofaguilar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Fernando on 03/12/2017.
 */

public class ClienteFragment extends Fragment{
    private Context context;
    private Button btnRegistrarCliente;
    private Button btnConsultarCliente;
    private Button btnListarCliente;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);

        btnRegistrarCliente = (Button) view.findViewById(R.id.btnRegistrarCliente);
        btnConsultarCliente = (Button) view.findViewById(R.id.btnConsultarCliente);
        btnListarCliente = (Button) view.findViewById(R.id.btnListarClientes);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnRegistrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegistrarClienteActivity.class);
                startActivity(intent);
            }
        });

        btnConsultarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConsultarClienteActivity.class);
                startActivity(intent);
            }
        });

        btnListarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListarClientesActivity.class);
                startActivity(intent);
            }
        });
    }
}
