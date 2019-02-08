package com.metre.adapter;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metre.OUtil;
import com.metre.helper.Dipositivo;
import com.metre.projetotransacaotelas.R;

import java.util.Arrays;
import java.util.List;

public class ImpressoraAdapter extends RecyclerView.Adapter<ViewHolderImpressora>{
    int selected_position = 0;
    private List<Dipositivo> itens;
    public ImpressoraAdapter(List<Dipositivo> itens) {
        this.itens = itens;
    }

    @NonNull
    @Override
    public ViewHolderImpressora onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowcaixa,viewGroup,false);
        return new ViewHolderImpressora(view,itens,viewGroup.getContext());
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolderImpressora view, int i) {
        if(itens != null && itens.size() > 0) {
            System.out.println("ARRAY::::::: "+Arrays.toString(itens.toArray()));
            Dipositivo p = itens.get(i);
            view.txtNome.setText(p.getNome());
            view.txtMac.setText(p.getMac());

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




