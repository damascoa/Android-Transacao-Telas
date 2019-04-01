package com.metre.projetotransacaotelas.subtelas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.metre.OUtil;
import com.metre.SessaoAplicacao;
import com.metre.adapter.PedidoItemAdapter;
import com.metre.adapter.ProdutoAdapter;
import com.metre.model.Pedido;
import com.metre.projetotransacaotelas.R;

import java.math.BigDecimal;

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
        System.out.println("ONCREATE");
        if(SessaoAplicacao.itensPedido == null || SessaoAplicacao.itensPedido.size() == 0){
            finish();
        }

        txtSubtotal = findViewById(R.id.txtSubtotal);
        btnContinuar = findViewById(R.id.btnContinuar);
        btnReceber = findViewById(R.id.btnReceber);

        lstPedidos =  findViewById(R.id.lstPedidos);

        carregarLista();

        txtSubtotal.setText(OUtil.formatarMoeda2(SessaoAplicacao.itensPedido.stream().map(m -> m.getTotal()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)));

    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        System.out.println("onCreateView");

        return super.onCreateView(name, context, attrs);
    }


    @Override
    protected void onRestart() {
        System.out.println("RESTART");
        if(SessaoAplicacao.itensPedido == null || SessaoAplicacao.itensPedido.size() == 0){
            finish();
        }
        super.onRestart();
    }



    public void carregarLista() {
        lstPedidos.setLayoutManager(new LinearLayoutManager(this));
        pedidoItemAdapter = new PedidoItemAdapter(SessaoAplicacao.itensPedido,this);
        lstPedidos.setAdapter(pedidoItemAdapter);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == (android.R.id.home)){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void continuarPedido(View view){
        finish();
    }

    public void receber(View view){
        Pedido pedido = new Pedido();
        pedido.setIdUsuario(SessaoAplicacao.usuario.getIdUsuario());
        pedido.setItens(SessaoAplicacao.itensPedido);
        pedido.setTotal(pedido.getItens().stream().map(m -> m.getTotal()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));

        Intent it = new Intent(view.getContext(), EncerramentoActivity.class);
        it.putExtra("pedido",pedido);
        view.getContext().startActivity(it);
    }





}
