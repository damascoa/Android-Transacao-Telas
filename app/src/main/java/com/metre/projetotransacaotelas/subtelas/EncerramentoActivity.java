package com.metre.projetotransacaotelas.subtelas;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.metre.OUtil;
import com.metre.SessaoAplicacao;
import com.metre.config.RetrofitConfig;
import com.metre.model.Administradora;
import com.metre.model.Bandeira;
import com.metre.model.FormaQuitacao;
import com.metre.model.Pedido;
import com.metre.projetotransacaotelas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EncerramentoActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    private Pedido pedido;
    private Spinner spinner2;
    private TextView lblValorPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encerramento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(spinner2 == null) {
            spinner2 = findViewById(R.id.spinner2);
            spinner2.setOnItemSelectedListener(this);
            System.out.println(" NULO");
        }else{
            System.out.println("SPINNER NAO É NULO");
        }
        carregarFormasQuitacao(this);
        carregarAdministradoras();

        lblValorPedido = findViewById(R.id.lblValorPedido);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("pedido")){
            pedido = (Pedido) bundle.getSerializable("pedido");
            lblValorPedido.setText(OUtil.formatarMoeda2(pedido.getTotal()));
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        FormaQuitacao item = (FormaQuitacao) parent.getItemAtPosition(position);

        if(item.getForma().equals("CC")){
            abrirDialogoCartao(view.getContext());
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    LinearLayout.LayoutParams paramButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    public void abrirDialogoCartao(Context c){
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View promptView = layoutInflater.inflate(R.layout.dialogcartao, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);
        alertDialogBuilder.setView(promptView);

        final Button btnCartaoCredito = promptView.findViewById(R.id.btnCartaoCredito);
        final Button btnCartaoDebito = promptView.findViewById(R.id.btnCartaoDebito);
        LinearLayout conteudoAdministradora = promptView.findViewById(R.id.conteudoAdministradora);
        LinearLayout conteudoBandeiras = promptView.findViewById(R.id.conteudoBandeiras);
        btnCartaoCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conteudoAdministradora.removeAllViews();
                for(Administradora a : SessaoAplicacao.administradorasCartao){
                    Button b = new Button(v.getContext());
                    b.setText(a.getNome());

                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            conteudoBandeiras.removeAllViews();
                            for(Bandeira band : a.getBandeiras()){
                                Button ba = new Button(v.getContext());
                                ba.setText(band.getNome());
                                conteudoAdministradora.addView(ba,paramButton);
                            }
                        }
                    });
                    conteudoAdministradora.addView(b,paramButton);
                }
            }
        });
        btnCartaoDebito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("Receber");
                    }
                })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == (android.R.id.home)){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void carregarFormasQuitacao(Context c){
        if(SessaoAplicacao.formasQuitacao == null){
            Call<List<FormaQuitacao>> callForma = new RetrofitConfig().getFormaService().listarFormas();
            callForma.enqueue(new Callback<List<FormaQuitacao>>() {
                @Override
                public void onResponse(Call<List<FormaQuitacao>> call, Response<List<FormaQuitacao>> response) {
                    if(response.body() != null){
                        SessaoAplicacao.formasQuitacao = response.body();
                        ArrayAdapter<FormaQuitacao> dataAdapter = new ArrayAdapter<FormaQuitacao>(c, android.R.layout.simple_spinner_item, SessaoAplicacao.formasQuitacao);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(dataAdapter);
                    }
                }
                @Override
                public void onFailure(Call<List<FormaQuitacao>> call, Throwable t) {
                    Toast.makeText(EncerramentoActivity.this, "Erro ao buscar as formas de quitação! "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            ArrayAdapter<FormaQuitacao> dataAdapter = new ArrayAdapter<FormaQuitacao>(c, android.R.layout.simple_spinner_item, SessaoAplicacao.formasQuitacao);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(dataAdapter);
        }
    }

    private void carregarAdministradoras(){
        if(SessaoAplicacao.administradorasCartao == null){
            Call<List<Administradora>> callForma = new RetrofitConfig().getCartaoService().listarAdministradora();
            callForma.enqueue(new Callback<List<Administradora>>() {
                @Override
                public void onResponse(Call<List<Administradora>> call, Response<List<Administradora>> response) {
                    if(response.body() != null){
                        SessaoAplicacao.administradorasCartao = response.body();
                    }
                }
                @Override
                public void onFailure(Call<List<Administradora>> call, Throwable t) {
                    Toast.makeText(EncerramentoActivity.this, "Erro ao buscar as administradoras! "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
