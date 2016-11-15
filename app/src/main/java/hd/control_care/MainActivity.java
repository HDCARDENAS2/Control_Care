package hd.control_care;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mdrawer;
    private ActionBarDrawerToggle mtoogle;
    private android.app.FragmentManager fragmentManager ;
    private LinearLayout ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BDUsuario BD = new BDUsuario(this);
        ArrayList<Usuario> list_usuario = BD.f_get_usuario();

        if(list_usuario.size() == 0){
            Intent i = new Intent(this,GestorRegistro.class);
            startActivity(i);
            finish();
        }else{

            NavigationView nview = (NavigationView) findViewById(R.id.navegacion);
            nview.setNavigationItemSelectedListener(this);

            mdrawer = (DrawerLayout)findViewById(R.id.drawerLayout);
            mtoogle = new ActionBarDrawerToggle(this,mdrawer,R.string.abrir,R.string.cerrar);
            mdrawer.addDrawerListener(mtoogle);
            mtoogle.syncState();
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

            fragmentManager = getFragmentManager();
            ly = (LinearLayout) findViewById(R.id.content_layout);
            crear_fragment(null,new GestorSemana());

            DatosTemporales.setFecha(list_usuario.get(0).getFecha_ultima_regla());

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mtoogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        fragmentManager = getFragmentManager();

        switch (item.getItemId()){
            case R.id.inicio:
                return crear_fragment(item,new GestorSemana());
            case R.id.control_peso:
                return crear_fragment(item,new Gestorpeso());
            case R.id.citas:
                return crear_fragment(item,new GestorCita());
        }
        return false;
    }

    private boolean crear_fragment(MenuItem item, Fragment fragment){
        try {

            if(item != null){
                item.setChecked(true);
            }

            ly.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.content_layout,fragment).commit();
            mdrawer.closeDrawer(GravityCompat.START);
            return true;

        }catch (Exception e) {
            Toast toast = Toast.makeText(this, "Error" + e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
    }



}
