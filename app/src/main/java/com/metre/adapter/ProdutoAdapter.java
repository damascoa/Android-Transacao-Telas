package com.metre.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.metre.model.Produto;
import com.metre.projetotransacaotelas.R;
import com.metre.task.DownloadImageTask;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ViewHolderProduto>{
    int selected_position = 0;
    private List<Produto> produtos;

    public ProdutoAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public ViewHolderProduto onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowproduto,viewGroup,false);
        return new ViewHolderProduto(view);
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduto view, int i) {
        if(produtos != null && produtos.size() > 0) {
            Produto p = produtos.get(i);
               view.itemView.setBackgroundColor(selected_position == i ? Color.GREEN : Color.TRANSPARENT);
                view.txtNome.setText(p.getDescricao().toString());
                view.txtPreco.setText(p.getPreco().toString());
              // new DownloadImageTask(view.imgProduto).execute("https://cdn.shoplightspeed.com/shops/604817/files/882430/coca-cola-usa-coke-24-12oz-case.jpg");
                Picasso.get().load("https://cdn.shoplightspeed.com/shops/604817/files/882430/coca-cola-usa-coke-24-12oz-case.jpg").into(view.imgProduto);
                view.imgProduto.setMaxHeight(96);
                view.imgProduto.setMaxWidth(96);
        }else{
            System.out.println("============================================PRODUTOS CHEGOU NULO");
        }
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }


}




