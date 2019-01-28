package com.metre.projetotransacaotelas.subtelas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.metre.SessaoAplicacao;
import com.metre.adapter.PedidoItemAdapter;
import com.metre.adapter.ProdutoAdapter;
import com.metre.projetotransacaotelas.R;

public class RecebimentoActivity extends AppCompatActivity {
    private RecyclerView lstPedidos;
    private PedidoItemAdapter pedidoItemAdapter;

    private TextView txtSubtotal;
    private Button btnContinuar;
    private Button btnReceber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recebimento);
        setTitle("Recebimento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtSubtotal = findViewById(R.id.txtSubtotal);
        btnContinuar = findViewById(R.id.btnContinuar);
        btnReceber = findViewById(R.id.btnReceber);

        lstPedidos =  findViewById(R.id.lstPedidos);

        lstPedidos.setLayoutManager(new LinearLayoutManager(this));
        pedidoItemAdapter = new PedidoItemAdapter(SessaoAplicacao.itensPedido);
        lstPedidos.setAdapter(pedidoItemAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == (android.R.id.home)){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
