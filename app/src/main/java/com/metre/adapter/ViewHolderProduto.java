package com.metre.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.metre.projetotransacaotelas.R;

public class ViewHolderProduto extends RecyclerView.ViewHolder{
    public final View view;
    public final TextView txtNome;
    public final TextView txtPreco;
    public final ImageView imgProduto;

    public ViewHolderProduto(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        txtNome     = itemView.findViewById(R.id.txtNome);
        txtPreco =    itemView.findViewById(R.id.txtPreco);
        imgProduto =  itemView.findViewById(R.id.imgProduto);
    }




}