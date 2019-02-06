package com.metre.projetotransacaotelas.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.metre.adapter.CaixaAdapter;
import com.metre.model.CaixaItem;
import com.metre.model.Produto;
import com.metre.projetotransacaotelas.R;

import java.util.ArrayList;
import java.util.List;

public class CaixaFragment extends Fragment {
    private TextView lblSaldo;
    private Button btnAbrir;
    private Button btnFechar;
    private RecyclerView lstMovimento;




    List<CaixaItem> movimento = new ArrayList<>();
    private CaixaAdapter caixaAdapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        lblSaldo = view.findViewById(R.id.lblSaldo);
        btnAbrir = view.findViewById(R.id.btnAbrir);
        btnFechar = view.findViewById(R.id.btnFechar);
        lstMovimento = view.findViewById(R.id.lstMovimento);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        lstMovimento.setLayoutManager(manager);
        carregarMovimentoCaixa();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void carregarMovimentoCaixa(){
        movimento = new ArrayList<>();
        movimento.addAll(new CaixaItem().simulate());
        lstMovimento.setAdapter(new CaixaAdapter(movimento));
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_caixa, container, false);

    }
}
