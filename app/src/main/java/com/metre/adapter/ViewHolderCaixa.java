package com.metre.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.metre.model.CaixaItem;
import com.metre.model.Produto;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.subtelas.ProdutoActivity;

import java.util.List;

public class ViewHolderCaixa extends RecyclerView.ViewHolder  implements View.OnCreateContextMenuListener {
    public List<CaixaItem> movimentos;
    public final View view;
    public final TextView txtMovimento;
    public final TextView txtForma;
    public final TextView txtValor;



    public ViewHolderCaixa(@NonNull View itemView, List<CaixaItem> movimentos, final Context context) {
        super(itemView);
        this.view = itemView;
        txtMovimento     = itemView.findViewById(R.id.txtMovimento);
        txtForma =    itemView.findViewById(R.id.txtForma);
        txtValor =  itemView.findViewById(R.id.txtValor);

        itemView.setOnCreateContextMenuListener(this);



        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(movimentos.size() > 0) {
                /*    Produto produtoSelecionado = produtos.get(getLayoutPosition());
                    Intent it = new Intent(context, ProdutoActivity.class);
                    it.putExtra("produto",produtoSelecionado);
                    Bundle bundle = it.getExtras();
                    ((AppCompatActivity) context).startActivityForResult(it, 0,bundle);
                */
                }
            }
        });


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Ações");
        MenuItem callItem = menu.add(0, v.getId(), 0, "Cancelar");//groupId, itemId, order, title
        callItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                System.out.println("HELLLLLLLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                return false;
            }
        });
    }



}