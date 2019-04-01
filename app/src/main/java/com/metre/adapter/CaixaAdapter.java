package com.metre.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metre.OUtil;
import com.metre.SessaoAplicacao;
import com.metre.model.CaixaItem;
import com.metre.model.PedidoItem;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.subtelas.RecebimentoActivity;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CaixaAdapter extends RecyclerView.Adapter<ViewHolderCaixa>{
    int selected_position = 0;
    private List<CaixaItem> itens;

    public CaixaAdapter(List<CaixaItem> itens) {
        this.itens = itens;
    }

    @NonNull
    @Override
    public ViewHolderCaixa onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowcaixa,viewGroup,false);
        return new ViewHolderCaixa(view,itens,viewGroup.getContext());
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolderCaixa view, int i) {
        if(itens != null && itens.size() > 0) {
            CaixaItem p = itens.get(i);
                view.txtMovimento.setText(p.getHistorico());
                view.txtForma.setText(p.getFormaQuitacao());
                view.txtValor.setText(OUtil.formatarMoeda2(p.getValor()));
        }
    }

    @Override
    public int getItemCount() {
        if(itens != null) {
            return itens.size();
        }else{
            System.out.println("ITEM COUNT 0");
            return 0;
        }
    }


}




