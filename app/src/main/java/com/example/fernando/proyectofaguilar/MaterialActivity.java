package com.example.fernando.proyectofaguilar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MaterialActivity extends AppCompatActivity {
    //Ids de los items de navegacion
    private static final int DRAWER_ITEM_UNO = 1;
    private static final int DRAWER_ITEM_DOS = 2;
    private static final int DRAWER_ITEM_TRES = 3;
    private static final int DRAWER_ITEM_CUATRO = 4;
    private static final int DRAWER_ITEM_CINCO = 5;

    //Definimos el entorno
    private Drawer drawer;
    private Context context;
    private FrameLayout contenedor;
    Toolbar toolbar;

    private String CuentaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        context = this;
        Intent intent = getIntent();
        CuentaUsuario = intent.getStringExtra("CuentaUsuario");

        //Antes de comenzar
        //Adicionar el tema  android:theme="@style/MaterialDrawerTheme.Light.DarkToolbar al manifest

        // Handle Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Contenedor es el espacio central
        contenedor = (FrameLayout) findViewById(R.id.contenedor);

        //Definimos el header
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().
                                withName(CuentaUsuario).
                                withEmail("info@devfest.xyz")
                )
                .build();

        //Definimos el Navigacion Drawer
        drawer = new DrawerBuilder(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new DividerDrawerItem(),
                        //Deinimos los items de navegacion
                        new PrimaryDrawerItem().
                                withIdentifier(DRAWER_ITEM_UNO).
                                withName(R.string.item_uno).
                                withTextColor(getResources().getColor(R.color.primary)).
                                withIconColor(getResources().getColor(R.color.primary)).
                                withSelectedTextColor(getResources().getColor(R.color.colorAccent)).
                                withSelectedIconColor(getResources().getColor(R.color.colorAccent)).
                                //withIcon(FontAwesome.Icon.faw_user),
                                        withIcon(R.drawable.clientes),
                        new PrimaryDrawerItem().
                                withIdentifier(DRAWER_ITEM_DOS).
                                withName(R.string.item_dos).
                                withTextColor(getResources().getColor(R.color.primary)).
                                withIconColor(getResources().getColor(R.color.primary)).
                                withSelectedTextColor(getResources().getColor(R.color.colorAccent)).
                                withSelectedIconColor(getResources().getColor(R.color.colorAccent)).
                                //withIcon(FontAwesome.Icon.faw_newspaper_o),
                                        withIcon(R.drawable.vencimientos),
                        new PrimaryDrawerItem().
                                withIdentifier(DRAWER_ITEM_TRES).
                                withName(R.string.item_tres).
                                withTextColor(getResources().getColor(R.color.primary)).
                                withIconColor(getResources().getColor(R.color.primary)).
                                withSelectedTextColor(getResources().getColor(R.color.colorAccent)).
                                withSelectedIconColor(getResources().getColor(R.color.colorAccent)).
                                //withIcon(FontAwesome.Icon.faw_database)
                                        withIcon(R.drawable.cuotas_cobradas_3)
                ).addStickyDrawerItems(
                        //Este item se encuentra en la parte inferior
                        new SecondaryDrawerItem()
                                .withName(R.string.item_cuatro)
                                .withIdentifier(DRAWER_ITEM_CUATRO)
                                //.withIcon(FontAwesome.Icon.faw_arrow_left)
                                .withIcon(R.drawable.users)
                                .withTextColor(getResources().getColor(R.color.primary))
                                .withIconColor(getResources().getColor(R.color.primary))
                                .withSelectedTextColor(getResources().getColor(R.color.colorAccent))
                                .withSelectedIconColor(getResources().getColor(R.color.colorAccent))
                                .withCheckable(false),
                        new SecondaryDrawerItem()
                                .withName(R.string.item_cinco)
                                .withIdentifier(DRAWER_ITEM_CINCO)
                                //.withIcon(FontAwesome.Icon.faw_arrow_left)
                                .withIcon(R.drawable.salir)
                                .withTextColor(getResources().getColor(R.color.primary))
                                .withIconColor(getResources().getColor(R.color.primary))
                                .withSelectedTextColor(getResources().getColor(R.color.colorAccent))
                                .withSelectedIconColor(getResources().getColor(R.color.colorAccent))
                                .withCheckable(false)
                )
                //Accion Click sobre los items de menu
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem drawerItem) {
                        seleccionartItem(drawerItem.getIdentifier());
                        return false;
                    }
                })
                .withSelectedItem(0)
                .withSavedInstance(savedInstanceState)
                .build();

        //Al inicializar se selecciona el primer item
        seleccionartItem(DRAWER_ITEM_UNO);
    }

    //Funcion de seleccion de item
    private void seleccionartItem(int i) {
        //Toast.makeText(context,"Selecciono el item N "+i,Toast.LENGTH_LONG).show();
        switch (i) {
            case 1:

                break;
            case 2:

                break;
            case 3:
                Intent cuotasIntent = new Intent(context, CuotasCobradasActivity.class);
                //cuotasIntent.putExtra("CuentaUsuario", lblCuentaUsuario.getText().toString());
                startActivity(cuotasIntent);
                break;
            case 4:
                Intent usersIntent = new Intent(context, UsuarioActivity.class);
                //cuotasIntent.putExtra("CuentaUsuario", lblCuentaUsuario.getText().toString());
                startActivity(usersIntent);
                break;
            default:
                AlertDialog.Builder Dialogo = new AlertDialog.Builder(MaterialActivity.this);
                Dialogo.setTitle("Salir de la Aplicación");
                Dialogo.setMessage("¿Está seguro de salir de la aplicación? ");
                Dialogo.setIcon(R.drawable.info);

                Dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();

                        finish();
                        System.exit(0);
                    }
                });
                Dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                Dialogo.show();
                //Toast.makeText(context, "Selecciono el item N " + i, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Guardamos la info del drawer
        if (drawer != null) {
            outState = drawer.saveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Boton hamburguesa en el ActionBar para Abrir y Cerrar el Navigation Drawer
        if (item.getItemId() == android.R.id.home) {
            if (drawer.isDrawerOpen())
                drawer.closeDrawer();
            else
                drawer.openDrawer();
        }
        return super.onOptionsItemSelected(item);
    }

    //Este porcion de codigo me sirve para preguntar si deseo regresar al activity login y asi cerrar mi session
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            //super.finish();
            AlertDialog.Builder Dialogo = new AlertDialog.Builder(MaterialActivity.this);
            Dialogo.setTitle("Salir de la Aplicación");
            Dialogo.setMessage("¿Está seguro de salir de la aplicación? ");
            Dialogo.setIcon(R.drawable.info);

            Dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //finish();

                    finish();
                    System.exit(0);

                    /*Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);*/
                }
            });
            Dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            Dialogo.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
