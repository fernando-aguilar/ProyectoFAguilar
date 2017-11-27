package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fernando.proyectofaguilar.ConexionSQLiteHelper;
import com.example.fernando.proyectofaguilar.ConsultarUsuarioActivity;
import com.example.fernando.proyectofaguilar.ListarUsuariosActivity;
import com.example.fernando.proyectofaguilar.R;
import com.example.fernando.proyectofaguilar.RegistrarUsuarioActivity;
import com.example.fernando.proyectofaguilar.UsuarioActivity;

public class UsuarioFragment extends Fragment {
    private Context context;
    private Button btnRegistrarUsuario;
    private Button btnConsultarUsuario;
    private Button btnListarUsuarios;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "bd_usuarios", null, 1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usuario, container, false);

        btnRegistrarUsuario = (Button) view.findViewById(R.id.btnRegistrarUsuario);
        btnConsultarUsuario = (Button) view.findViewById(R.id.btnConsultarUsuario);
        btnListarUsuarios = (Button) view.findViewById(R.id.btnListarUsuarios);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegistrarUsuarioActivity.class);
                startActivity(intent);
            }
        });

        btnConsultarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConsultarUsuarioActivity.class);
                startActivity(intent);
            }
        });

        btnListarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListarUsuariosActivity.class);
                startActivity(intent);
            }
        });
    }

//    public void onClick(View view){
//        Intent usuarioIntent = null;
//        switch (view.getId()){
//            case R.id.btnRegistrarUsuario:
//                usuarioIntent = new Intent(context, RegistrarUsuarioActivity.class);
//                break;
//
//            case R.id.btnConsultarUsuario:
//                usuarioIntent = new Intent(context, ConsultarUsuarioActivity.class);
//                break;
//
//            case R.id.btnListarUsuarios:
//                usuarioIntent = new Intent(context, ListarUsuariosActivity.class);
//                break;
//        }
//
//        if (usuarioIntent != null){
//            startActivity(usuarioIntent);
//        }
//    }
}
