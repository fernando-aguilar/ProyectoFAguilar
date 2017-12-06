package com.example.fernando.proyectofaguilar.adaptadores;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fernando.proyectofaguilar.R;
import com.example.fernando.proyectofaguilar.entidades.Cliente;

import java.util.List;

/**
 * Created by Fernando on 05/12/2017.
 */

public class ListaClientesAdapter extends ArrayAdapter<Cliente> {
    private Activity contex;
    private List<Cliente> listaClientes;

    public ListaClientesAdapter(Activity context, List<Cliente> listaClientes){
        super(context, R.layout.list_item_clientes, listaClientes);

        this.contex = context;
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = contex.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_item_clientes, null, true);

        TextView textViewNombre = (TextView) listViewItem.findViewById(R.id.textViewNombre);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);

        Cliente cliente = listaClientes.get(position);
        textViewNombre.setText(cliente.getNombreCliente());
        textViewEmail.setText(cliente.getEmailCliente());

        return  listViewItem;
    }
}
