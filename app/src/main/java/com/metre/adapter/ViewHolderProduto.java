package com.metre.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.metre.model.Grupo;
import com.metre.model.Produto;
import com.metre.projetotransacaotelas.subtelas.ProdutoActivity;
import com.metre.projetotransacaotelas.R;

import java.util.List;

public class ViewHolderProduto extends RecyclerView.ViewHolder{
    public List<Produto> produtos;
    public final View view;
    public final TextView txtNome;
    public final TextView txtPreco;
    public final ImageView imgProduto;

    public ViewHolderProduto(@NonNull View itemView,List<Produto> produtos,final Context context) {
        super(itemView);
        this.view = itemView;
        txtNome     = itemView.findViewById(R.id.txtNome);
        txtPreco =    itemView.findViewById(R.id.txtPreco);
        imgProduto =  itemView.findViewById(R.id.imgProduto);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(produtos.size() > 0) {
                    Produto produtoSelecionado = produtos.get(getLayoutPosition());
                    Intent it = new Intent(context, ProdutoActivity.class);
                    it.putExtra("produto",produtoSelecionado);
                    Bundle bundle = it.getExtras();
                    ((AppCompatActivity) context).startActivityForResult(it, 0,bundle);




                }
            }
        });


    }




}