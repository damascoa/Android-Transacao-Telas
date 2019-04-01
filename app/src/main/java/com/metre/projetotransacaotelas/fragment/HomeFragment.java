package com.metre.projetotransacaotelas.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.metre.SessaoAplicacao;
import com.metre.projetotransacaotelas.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
    private FragmentActivity myContext;


    private LinearLayout linearCaixa,linearPedido,linearImpressoras,linearMeusPedidos;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.linearCaixa = view.findViewById(R.id.linearCaixa);
        this.linearPedido = view.findViewById(R.id.linearPedido);
        this.linearImpressoras = view.findViewById(R.id.linearImpressoras);
        this.linearMeusPedidos = view.findViewById(R.id.linearMeusPedidos);


        super.onViewCreated(view, savedInstanceState);

        this.linearCaixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragManager = myContext.getSupportFragmentManager();
                fragManager.beginTransaction().replace(R.id.fragment_container,new CaixaFragment()).commit();
            }
        });

        this.linearPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragManager = myContext.getSupportFragmentManager();
                fragManager.beginTransaction().replace(R.id.fragment_container,new PedidoFragment()).commit();
            }
        });

        this.linearImpressoras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragManager = myContext.getSupportFragmentManager();
                fragManager.beginTransaction().replace(R.id.fragment_container,new ImpressorasFragment()).commit();
            }
        });

        this.linearMeusPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragManager = myContext.getSupportFragmentManager();
                fragManager.beginTransaction().replace(R.id.fragment_container,new CaixaFragment()).commit();
            }
        });
    }



}
