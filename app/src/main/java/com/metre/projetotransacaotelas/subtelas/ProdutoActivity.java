package com.metre.projetotransacaotelas.subtelas;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.metre.OUtil;
import com.metre.SessaoAplicacao;
import com.metre.model.Pedido;
import com.metre.model.PedidoItem;
import com.metre.model.Produto;
import com.metre.model.Usuario;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.fragment.PedidoFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

public class ProdutoActivity extends AppCompatActivity {
    private TextView lblProduto,lblSubtotal;
    private EditText txtQnt;
    private FloatingActionButton btnMenos;
    private FloatingActionButton btnMais;
    private ImageView imagemProduto;

    private Produto produto;
    private PedidoItem pedidoItem;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtQnt = findViewById(R.id.txtQnt);
        btnMenos = findViewById(R.id.btnMenos);
        btnMais = findViewById(R.id.btnMais);
        imagemProduto = findViewById(R.id.imagemProduto);
        lblProduto = findViewById(R.id.lblProduto);
        lblSubtotal = findViewById(R.id.lblSubtotal);


        createListeners();
        carregarDadosProduto();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == (android.R.id.home)){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }






    private void createListeners() {
        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtrairQnt();
            }
        });
        btnMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarQnt();
            }
        });
    }

    public void carregarDadosProduto(){
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("produto")){
            produto = (Produto) bundle.getSerializable("produto");
            pedidoItem = new PedidoItem(produto,BigDecimal.ONE,new Usuario());
        }else{
            produto = null;
        }
        Picasso.get().load(produto.getFoto())
                .memoryPolicy(MemoryPolicy.NO_STORE)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.ic_cancel_black_24dp)
                .resize(250,300)
                .into(imagemProduto);
        imagemProduto.setMaxHeight(250);
        imagemProduto.setMaxWidth(250);

        lblProduto.setText(produto.getDescricao());
        lblSubtotal.setText("Subtotal "+OUtil.formatarMoeda2(produto.getPreco()));
    }




    public void adicionarQnt(){
        pedidoItem.adicionarQuantidade(BigDecimal.ONE);
        txtQnt.setText(pedidoItem.getQuantidade().toString());
        lblSubtotal.setText("Subtotal "+OUtil.formatarMoeda2(pedidoItem.getTotal()));
    }

    public void subtrairQnt(){
        Integer qntAtual = Integer.parseInt(txtQnt.getText().toString());
        if(qntAtual > 1) {
           pedidoItem.diminuirQuantidade(BigDecimal.ONE);
           txtQnt.setText(pedidoItem.getQuantidade().toString());
           lblSubtotal.setText("Subtotal "+OUtil.formatarMoeda2(pedidoItem.getTotal()));
        }
    }

    public void excluirPedido(View view){
        finish();
    }


    public void LancarEContinuar(View view){
        SessaoAplicacao.addItem(pedidoItem);
        Toast.makeText(this, "Item adicionado!", Toast.LENGTH_LONG).show();
        Intent returnIntent = new Intent();
        setResult(100,returnIntent);
        finish();

    }
    public void LancarEFinalizar(View view){
        Intent returnIntent = new Intent();
        setResult(101,returnIntent);
        finish();
    }

    public void withItems(View view) {
        final String[] items = {"Apple", "Banana", "Orange", "Grapes"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecione a forma!")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), items[which] + " is clicked", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("CANCEL", null);
        builder.setNeutralButton("NEUTRAL", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setBackgroundColor(Color.BLACK);
        button.setPadding(0, 0, 20, 0);
        button.setTextColor(Color.WHITE);
    }
}
