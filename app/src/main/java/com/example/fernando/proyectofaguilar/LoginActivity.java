package com.example.fernando.proyectofaguilar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fernando.proyectofaguilar.Utilidades.Utilidades;

public class LoginActivity extends AppCompatActivity {

    public final static String CUENTA_VALIDO = "faguilar";
    public final static String CONTRASEÑA_VALIDO = "5974610";

    private Context context;
    private LinearLayout lyLogin;
    private EditText txtCuentaUsuario;
    private EditText txtClaveUsuario;
    private Button btnIngresar;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Bienvenido");

        context = this;
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);

        lyLogin = (LinearLayout) findViewById(R.id.lyLogin);
        txtCuentaUsuario = (EditText) findViewById(R.id.txtCuentaUsuario);
        txtClaveUsuario = (EditText) findViewById(R.id.txtClaveUsuario);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vpsCuentaUsuario = txtCuentaUsuario.getText().toString();
                String vpsClaveUsuario = txtClaveUsuario.getText().toString();

                if(TextUtils.isEmpty(vpsCuentaUsuario)){
                    txtCuentaUsuario.setError("La cuenta de usuario es requerida");
                    txtCuentaUsuario.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(vpsClaveUsuario)){
                    txtClaveUsuario.setError("La clave de usuario es requerida");
                    txtClaveUsuario.requestFocus();
                    return;
                }

//                if (vpsCuentaUsuario.equals(CUENTA_VALIDO) && vpsClaveUsuario.equals(CONTRASEÑA_VALIDO)) {
//                    //Toast.makeText(getApplicationContext(), getString(R.string.login_ok, vpsCuentaUsuario), Toast.LENGTH_SHORT).show();
//
//                    //Intent menuIntent = new Intent(context, MenuActivity.class);
//                    //menuIntent.putExtra("CuentaUsuario", txtCuentaUsuario.getText().toString());
//                    //startActivity(menuIntent);
//
//                    Intent principalIntent = new Intent(context, PrincipalActivity.class);
//                    principalIntent.putExtra("CuentaUsuario", txtCuentaUsuario.getText().toString());
//                    startActivity(principalIntent);
//
//                } else {
//                    Toast.makeText(getApplicationContext(), getString(R.string.login_error), Toast.LENGTH_SHORT).show();
//                    txtCuentaUsuario.requestFocus();
//                }

                SQLiteDatabase db = conn.getReadableDatabase();
                //String[] parametrosConsulta = {vpsClaveUsuario};
                String[] parametrosConsulta = {vpsCuentaUsuario, vpsClaveUsuario};
                String[] campos = {Utilidades.CAMPO_CUENTA, Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};

                try {
                    //Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_ID + "=?", parametrosConsulta, null, null, null);
                    Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_CUENTA + "=? AND " + Utilidades.CAMPO_ID + "=?", parametrosConsulta, null, null, null);
                    cursor.moveToFirst();

                    Intent principalIntent = new Intent(context, PrincipalActivity.class);
                    principalIntent.putExtra("CuentaUsuario", cursor.getString(0));
                    startActivity(principalIntent);

                    cursor.close();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                    //spLimpiarControles();
                    txtCuentaUsuario.requestFocus();
                }
            }
        });
    }

    private void spLimpiarControles(){
        txtCuentaUsuario.setText("");
        txtClaveUsuario.setText("");
    }

}
