package com.example.fernando.proyectofaguilar.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fernando.proyectofaguilar.R;
import com.example.fernando.proyectofaguilar.entidades.Usuario;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/11/2017.
 */

public class ListaPersonasAdapter extends RecyclerView.Adapter<ListaPersonasAdapter.PersonasViewHolder> {
    ArrayList<Usuario> listaUsuario;

    public ListaPersonasAdapter(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_usuarios, null, false);
        return new PersonasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.txtDocumentoID.setText(listaUsuario.get(position).getId().toString());
        holder.txtCuentaUsuario.setText(listaUsuario.get(position).getCuenta().toString());
        holder.txtNombreUsuario.setText(listaUsuario.get(position).getNombre());
        holder.txtTelefonoUsuario.setText(listaUsuario.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class PersonasViewHolder extends RecyclerView.ViewHolder {
        TextView txtDocumentoID, txtCuentaUsuario, txtNombreUsuario, txtTelefonoUsuario;

        public PersonasViewHolder(View itemView) {
            super(itemView);

            txtDocumentoID = (TextView) itemView.findViewById(R.id.textDocumento);
            txtCuentaUsuario = (TextView) itemView.findViewById(R.id.textCuenta);
            txtNombreUsuario = (TextView) itemView.findViewById(R.id.textNombre);
            txtTelefonoUsuario = (TextView) itemView.findViewById(R.id.textTelefono);
        }
    }
}
