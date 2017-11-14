package com.example.fernando.proyectofaguilar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public final static String CUENTA_VALIDO = "faguilar";
    public final static String CONTRASEÑA_VALIDO = "5974610";

    private Context context;
    private LinearLayout lyLogin;
    private EditText txtCuentaUsuario;
    private EditText txtClaveUsuario;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        lyLogin = (LinearLayout) findViewById(R.id.lyLogin);
        txtCuentaUsuario = (EditText) findViewById(R.id.txtCuentaUsuario);
        txtClaveUsuario = (EditText) findViewById(R.id.txtClaveUsuario);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vpsCuentaUsuario = txtCuentaUsuario.getText().toString();
                String vpsClaveUsuario = txtClaveUsuario.getText().toString();

                if (vpsCuentaUsuario.equals(CUENTA_VALIDO) && vpsClaveUsuario.equals(CONTRASEÑA_VALIDO)) {
                    //Toast.makeText(getApplicationContext(), getString(R.string.login_ok, vpsCuentaUsuario), Toast.LENGTH_SHORT).show();

                    //Intent menuIntent = new Intent(context, MenuActivity.class);
                    //menuIntent.putExtra("CuentaUsuario", txtCuentaUsuario.getText().toString());
                    //startActivity(menuIntent);

                    Intent materialIntent = new Intent(context, MaterialActivity.class);
                    materialIntent.putExtra("CuentaUsuario", txtCuentaUsuario.getText().toString());
                    startActivity(materialIntent);

                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                    txtCuentaUsuario.requestFocus();
                }
            }
        });
    }
}
