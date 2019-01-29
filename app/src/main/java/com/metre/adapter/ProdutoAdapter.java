package com.metre.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.metre.OUtil;
import com.metre.model.Produto;
import com.metre.projetotransacaotelas.R;
import com.metre.task.DownloadImageTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;

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
        return new ViewHolderProduto(view,produtos,viewGroup.getContext());
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduto view, int i) {
        if(produtos != null && produtos.size() > 0) {
            Produto p = produtos.get(i);
                view.txtNome.setText(p.getDescricao());
                view.txtPreco.setText(OUtil.formatarMoeda2(p.getPreco()));
              // new DownloadImageTask(view.imgProduto).execute("https://cdn.shoplightspeed.com/shops/604817/files/882430/coca-cola-usa-coke-24-12oz-case.jpg");
        if(p.getFoto() != null && !p.getFoto().isEmpty()){
            Picasso.get()
                    .load(p.getFoto())
                    .memoryPolicy(MemoryPolicy.NO_STORE)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.ic_cancel_black_24dp)

                    .into(view.imgProduto);
        }else{
            view.imgProduto.setImageResource(R.drawable.placeholder);
        }

                view.imgProduto.setMaxHeight(64);
                view.imgProduto.setMaxWidth(64);

        }else{
            System.out.println("============================================PRODUTOS CHEGOU NULO");
        }
    }

    @Override
    public int getItemCount() {
        if(produtos != null) {
            return produtos.size();
        }else{
            return 0;
        }
    }


}




