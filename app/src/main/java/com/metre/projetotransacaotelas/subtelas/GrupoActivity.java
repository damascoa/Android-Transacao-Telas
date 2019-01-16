package com.metre.projetotransacaotelas.subtelas;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.metre.adapter.ProdutoAdapter;
import com.metre.projetotransacaotelas.R;
import com.metre.services.ProdutoService;

public class GrupoActivity extends AppCompatActivity {
    private TextView txtGrupo;
    private ConstraintLayout layoutContextMain;
    private RecyclerView lstProdutos;


    private ProdutoAdapter produtoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtGrupo = findViewById(R.id.txtGrupo);



        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("grupo")){
            txtGrupo.setText(bundle.getSerializable("grupo").toString());
        }else{
            txtGrupo.setText("Grupo não carregado");
        }
        layoutContextMain = findViewById(R.id.layoutContextMain);



        lstProdutos = findViewById(R.id.lstProdutos);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        lstProdutos.setLayoutManager(manager);

        produtoAdapter = new ProdutoAdapter(new ProdutoService().getProdutos());
        lstProdutos.setAdapter(produtoAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == (android.R.id.home)){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
