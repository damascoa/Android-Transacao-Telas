package com.metre.projetotransacaotelas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.metre.SessaoAplicacao;
import com.metre.helper.CircleTransform;
import com.metre.projetotransacaotelas.R;

import com.metre.projetotransacaotelas.fragment.CaixaFragment;
import com.metre.projetotransacaotelas.fragment.ConfiguracaoFragment;
import com.metre.projetotransacaotelas.fragment.HomeFragment;
import com.metre.projetotransacaotelas.fragment.ImpressorasFragment;
import com.metre.projetotransacaotelas.fragment.PedidoFragment;
import com.metre.projetotransacaotelas.subtelas.LoginActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class TemplateActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView imgUsuario;
    private TextView txtNomeUsuario;
    private TextView txtPerfil;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(toolbar == null){
            System.out.println("toolbar É NULO");
        }




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       View headerLayout = navigationView.getHeaderView(0);



        txtNomeUsuario = headerLayout.findViewById(R.id.txtNomeUsuario);
        imgUsuario = headerLayout.findViewById(R.id.imgUsuario);
        txtPerfil = headerLayout.findViewById(R.id.txtPerfil);


        if(txtNomeUsuario == null){
            System.out.println("txtNomeUsuario É NULO");
        }


        SessaoAplicacao.preferencias = getApplicationContext().getSharedPreferences("Configuracao", 0);
        if( SessaoAplicacao.preferencias .getString("ip", null) == null){
            System.out.println("IP ESTA NULO");
            navegarPorID(R.id.nav_config);
            return;
        }
        if(SessaoAplicacao.usuario == null){
            Intent it = new Intent(this,LoginActivity.class);
            startActivity(it);
            return;
        }else{


            if(SessaoAplicacao.usuario.getNome() != null){
                System.out.println("USUARIO LOG: "+SessaoAplicacao.usuario.toString());
                txtNomeUsuario.setText(SessaoAplicacao.usuario.getNome());
            }else{
                System.out.println("USUARIO LOG 2: "+SessaoAplicacao.usuario.toString());
                txtNomeUsuario.setText(SessaoAplicacao.usuario.getUsuario());
            }
            Picasso.get()
                    .load(SessaoAplicacao.usuario.getFoto())
                    .transform(new CircleTransform())
                    .memoryPolicy(MemoryPolicy.NO_STORE)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.ic_cancel_black_24dp)
                    .into(imgUsuario);
            imgUsuario.setMaxHeight(24);
            imgUsuario.setMaxWidth(24);

        }



        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

    }


    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.template, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        } else if (id == R.id.nav_pedido) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PedidoFragment()).commit();
        } else if (id == R.id.nav_caixa) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CaixaFragment()).commit();
        } else if (id == R.id.nav_impressoras) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ImpressorasFragment()).commit();
        } else if (id == R.id.nav_config) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ConfiguracaoFragment()).commit();
        } else if (id == R.id.nav_sair) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void navegarPorID(int id){
        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        } else if (id == R.id.nav_pedido) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PedidoFragment()).commit();
        } else if (id == R.id.nav_caixa) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CaixaFragment()).commit();
        } else if (id == R.id.nav_impressoras) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ImpressorasFragment()).commit();
        } else if (id == R.id.nav_config) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ConfiguracaoFragment()).commit();
        } else if (id == R.id.nav_sair) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


}
