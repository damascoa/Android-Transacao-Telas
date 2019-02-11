package com.metre.projetotransacaotelas.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.metre.SessaoAplicacao;
import com.metre.adapter.CaixaAdapter;
import com.metre.config.RetrofitConfig;
import com.metre.model.CaixaItem;
import com.metre.projetotransacaotelas.subtelas.AbrirCaixa;
import com.metre.projetotransacaotelas.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaixaFragment extends Fragment {
    private TextView lblSaldo;
    private Button btnAbrir;
    private Button btnFechar;
    private RecyclerView lstMovimento;



    private ProgressDialog mProgressDialog;
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

        if(SessaoAplicacao.caixa == null){
            btnFechar.setEnabled(false);
            btnAbrir.setEnabled(true);
        }else{
            btnFechar.setEnabled(true);
            btnAbrir.setEnabled(false);
        }
        btnAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),AbrirCaixa.class));
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void carregarMovimentoCaixa(){
        movimento = new ArrayList<>();
        mProgressDialog = new ProgressDialog(getContext());
         getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        try {
            System.out.println("ID DO CAIXA: "+SessaoAplicacao.usuario.getCaixaCorrente());
            Call<List<CaixaItem>> call = new RetrofitConfig().getCaixaService().buscarMovimento(SessaoAplicacao.usuario.getCaixaCorrente());
            call.enqueue(new Callback<List<CaixaItem>>() {
                @Override
                public void onResponse(Call<List<CaixaItem>> call, Response<List<CaixaItem>> response) {
                    if(response.body() != null){
                        movimento.addAll(response.body());
                        lstMovimento.setAdapter(new CaixaAdapter(movimento));
                    }
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                }

                @Override
                public void onFailure(Call<List<CaixaItem>> call, Throwable t) {
                    Log.e("Erro ao buscar o movimento",t.getMessage());
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                }
            });

        }catch (Exception e){
            System.err.println("Erro ao buscar o movimento do caixa!"+e.getMessage());
        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_caixa, container, false);

    }
}
