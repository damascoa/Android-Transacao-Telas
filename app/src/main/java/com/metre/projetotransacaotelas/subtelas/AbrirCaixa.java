package com.metre.projetotransacaotelas.subtelas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.metre.SessaoAplicacao;
import com.metre.config.RetrofitConfig;
import com.metre.model.Caixa;
import com.metre.model.enums.Periodo;
import com.metre.projetotransacaotelas.R;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbrirCaixa extends AppCompatActivity {
    AppCompatEditText iptValor;
    Spinner periodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrir_caixa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Abrir Caixa");
        periodo = findViewById(R.id.spinner);
        iptValor = findViewById(R.id.iptValor);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Periodo.valores());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodo.setAdapter(dataAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == (android.R.id.home)){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void abrirCaixa(View view){
        if(iptValor.getText().toString().isEmpty()){
            Toast.makeText(this, "Informe o valor", Toast.LENGTH_SHORT).show();
            return;
        }
        com.metre.model.AbrirCaixa abertura = new com.metre.model.AbrirCaixa();
        abertura.setPeriodo(periodo.getSelectedItem().toString());
        abertura.setValor(new BigDecimal(iptValor.getText().toString()));
        abertura.setIdUsuario(SessaoAplicacao.usuario.getIdUsuario());

        Call<com.metre.model.AbrirCaixa> call = new RetrofitConfig().getCaixaService().abrirCaixa(abertura);
        call.enqueue(new Callback<com.metre.model.AbrirCaixa>() {
            @Override
            public void onResponse(Call<com.metre.model.AbrirCaixa> call, Response<com.metre.model.AbrirCaixa> response) {
                if(response != null && response.body() != null){
                    com.metre.model.AbrirCaixa aber = response.body();
                    if(aber.getIdCaixa() != null){
                        buscarCaixa(aber.getIdCaixa());
                        Toast.makeText(AbrirCaixa.this, "Caixa aberto com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(AbrirCaixa.this, "Seu caixa não foi aberto!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<com.metre.model.AbrirCaixa> call, Throwable t) {
                t.printStackTrace();
                if(t instanceof UnrecognizedPropertyException){
                    Toast.makeText(AbrirCaixa.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(AbrirCaixa.this, "Não estamos conseguindo conectar com o servidor!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void buscarCaixa(Integer id){
        Call<Caixa> call = new RetrofitConfig().getCaixaService().buscarCaixa(id);
        call.enqueue(new Callback<Caixa>() {
            @Override
            public void onResponse(Call<Caixa> call, Response<Caixa> response) {
                if(response.body() != null){
                    SessaoAplicacao.caixa = response.body();
                }
            }

            @Override
            public void onFailure(Call<Caixa> call, Throwable t) {
                Toast.makeText(AbrirCaixa.this, "Não estamos encontrando o caixa "+id+" no servidor!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}


