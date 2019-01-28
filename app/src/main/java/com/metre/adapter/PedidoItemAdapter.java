package com.metre.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metre.OUtil;
import com.metre.model.PedidoItem;
import com.metre.model.Produto;
import com.metre.projetotransacaotelas.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PedidoItemAdapter extends RecyclerView.Adapter<ViewHolderPedidoItem>{
    int selected_position = 0;
    private List<PedidoItem> itens;

    public PedidoItemAdapter(List<PedidoItem> itens) {
        this.itens = itens;
    }

    @NonNull
    @Override
    public ViewHolderPedidoItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowpedido,viewGroup,false);
        return new ViewHolderPedidoItem(view,itens,viewGroup.getContext());
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolderPedidoItem view, int i) {
        if(itens != null && itens.size() > 0) {
            PedidoItem p = itens.get(i);
                view.txtNome.setText(p.getProduto());
                view.txtQntPreco.setText(p.getQuantidade()+" x "+OUtil.formatarMoeda2(p.getPreco()));
              // new DownloadImageTask(view.imgProduto).execute("https://cdn.shoplightspeed.com/shops/604817/files/882430/coca-cola-usa-coke-24-12oz-case.jpg");

                Picasso.get()
                        .load(p.getIdProduto().getFoto())
                        .memoryPolicy(MemoryPolicy.NO_STORE)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.ic_cancel_black_24dp)

                 .into(view.imgProduto, new Callback() {
                     @Override
                     public void onSuccess() {
                         System.out.println("::::::::::::::::::::::::::::::::::::SUCESSO");
                     }

                     @Override
                     public void onError(Exception e) {
                         System.out.println(":::::::::::::::::::::::::::::::::::::::ERRO");
                         e.printStackTrace();
                     }
                 });
                view.imgProduto.setMaxHeight(64);
                view.imgProduto.setMaxWidth(64);

        }else{
            System.out.println("============================================PRODUTOS CHEGOU NULO");
        }
    }

    @Override
    public int getItemCount() {
        if(itens != null) {
            return itens.size();
        }else{
            return 0;
        }
    }


}




