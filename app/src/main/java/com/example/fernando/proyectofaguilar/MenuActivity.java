package com.example.fernando.proyectofaguilar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    private Context context;
    private String CuentaUsuario;
    private TextView lblCuentaUsuario;

    private LinearLayout lyCuotasCobradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        context = this;
        Intent intent = getIntent();
        CuentaUsuario = intent.getStringExtra("CuentaUsuario");

        //Vicular variables con los controles
        lblCuentaUsuario = (TextView) findViewById(R.id.lblCuentaUsuario);
        lyCuotasCobradas = (LinearLayout) findViewById(R.id.lyCuotasCobradas);

        lblCuentaUsuario.setText("Cuenta usuario: " + CuentaUsuario);

        lyCuotasCobradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cuotasIntent = new Intent(context, CuotasCobradasActivity.class);
                //cuotasIntent.putExtra("CuentaUsuario", lblCuentaUsuario.getText().toString());
                startActivity(cuotasIntent);
            }
        });
    }
}
