package com.metre.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metre.OUtil;
import com.metre.SessaoAplicacao;
import com.metre.model.PedidoItem;
import com.metre.model.Produto;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.subtelas.RecebimentoActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PedidoItemAdapter extends RecyclerView.Adapter<ViewHolderPedidoItem>{
    int selected_position = 0;
    private List<PedidoItem> itens;
    private RecebimentoActivity ra;

    public PedidoItemAdapter(List<PedidoItem> itens, RecebimentoActivity ra) {
        this.itens = itens; this.ra = ra;
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
                view.txtNome.setText(p.getProduto().getDescricao());
                view.txtQntPreco.setText(p.getQuantidade()+" x "+OUtil.formatarMoeda2(p.getPreco()));
                view.txtTotal.setText(OUtil.formatarMoeda2(p.getTotal()));
            if(p.getProduto().getFoto()!= null && !p.getProduto().getFoto().isEmpty()){
                Picasso.get().load(p.getProduto().getFoto()).memoryPolicy(MemoryPolicy.NO_STORE)
                        .placeholder(R.drawable.placeholder).error(R.drawable.ic_cancel_black_24dp).into(view.imgProduto);
            }else{
                view.imgProduto.setImageResource(R.drawable.placeholder);
            }
                view.imgProduto.setMaxHeight(64);
                view.imgProduto.setMaxWidth(64);


            view.btnExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SessaoAplicacao.removerItem(p);
                    ra.carregarLista();

                }
            });
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




