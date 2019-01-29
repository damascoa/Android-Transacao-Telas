package com.metre.projetotransacaotelas.subtelas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.metre.adapter.ProdutoAdapter;
import com.metre.config.RetrofitConfig;
import com.metre.model.Grupo;
import com.metre.model.Produto;
import com.metre.projetotransacaotelas.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GrupoActivity extends AppCompatActivity {
    private TextView txtGrupo;
    private ConstraintLayout layoutContextMain;
    private RecyclerView lstProdutos;

    List<Produto> produtos = new ArrayList<>();

    private ProdutoAdapter produtoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtGrupo = findViewById(R.id.txtGrupo);



        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("grupo")){
           Grupo grupo = (Grupo) bundle.getSerializable("grupo");
           carregarProdutos(grupo.getIdGrupo());
           txtGrupo.setText(grupo.getDescricao());
        }else{
            txtGrupo.setText("Grupo não carregado");
        }
        layoutContextMain = findViewById(R.id.layoutContextMain);

        lstProdutos = findViewById(R.id.lstProdutos);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        lstProdutos.setLayoutManager(manager);
    }


    public void carregarProdutos(Integer idgrupo){
         produtos = new ArrayList<>();

        Call<List<Produto>> call = new RetrofitConfig().getProdutoService().listarProdutos(idgrupo);
        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                if(response.body() != null){
                    produtos = response.body();
                    System.out.println("PRODUTOS SIZE: "+response.body().size());
                    produtoAdapter = new ProdutoAdapter(produtos);
                    lstProdutos.setAdapter(produtoAdapter);
                }else{
                    Toast.makeText(GrupoActivity.this, "Nenhum produto para este grupo!", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                Log.e("ProdutoService", "Erro ao buscar os produtos: " + t.getMessage());
                Toast.makeText(GrupoActivity.this, "Erro ao buscar os produtos: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == (android.R.id.home)){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //100 QUER DIZER FOI VAI CONTINUAR PEDIDO
        // QUALQUER OUTRO NUMERO QUER DIZER QUE JA VAI RECEBER OS PEDIDOS
        System.out.println("CODE: " +resultCode);
        if(resultCode == 100 || resultCode == 0){
            finish();
        }else{
            Intent it = new Intent(this,RecebimentoActivity.class);
            this.startActivityForResult(it, 1000);
        }

    }
}
