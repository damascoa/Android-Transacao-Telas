package com.metre.projetotransacaotelas.subtelas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.metre.projetotransacaotelas.R;

public class AbrirCaixa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrir_caixa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Abrir Caixa");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == (android.R.id.home)){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
