package com.metre.adapter;

import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.metre.OUtil;
import com.metre.model.PedidoRecebimento;
import com.metre.projetotransacaotelas.R;


import java.util.List;

public class RecebimentoAdapter extends RecyclerView.Adapter<ViewHolderRecebimento>{
    int selected_position = 0;
    private List<PedidoRecebimento> itens;

    public RecebimentoAdapter(List<PedidoRecebimento> itens) {
        this.itens = itens;
    }

    @NonNull
    @Override
    public ViewHolderRecebimento onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowrecebimento,viewGroup,false);
        return new ViewHolderRecebimento(view,itens,viewGroup.getContext());
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecebimento view, int i) {
        if(itens != null && itens.size() > 0) {
            PedidoRecebimento p = itens.get(i);
            view.txtFormaRecebimento.setText(p.getForma());
            view.txtValorRecebimento.setText(OUtil.formatarMoeda2(p.getValor()));
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




