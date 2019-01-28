package com.metre.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.metre.model.PedidoItem;
import com.metre.model.Produto;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.subtelas.ProdutoActivity;

import java.util.List;

public class ViewHolderPedidoItem extends RecyclerView.ViewHolder{
    public List<PedidoItem> itens;
    public final View view;
    public final TextView txtNome;
    public final TextView txtTotal;
    public final ImageView imgProduto;
    public final TextView txtQntPreco;
    public final Button btnExcluir;

    public ViewHolderPedidoItem(@NonNull View itemView, List<PedidoItem> itens, final Context context) {
        super(itemView);
        this.view = itemView;
        txtNome     = itemView.findViewById(R.id.txtNome);
        txtTotal =    itemView.findViewById(R.id.txtTotal);
        imgProduto =  itemView.findViewById(R.id.imgProduto);
        btnExcluir =  itemView.findViewById(R.id.btnExcluir);
        txtQntPreco = itemView.findViewById(R.id.txtQntPreco);


    }




}