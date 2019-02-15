package com.metre.projetotransacaotelas.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.metre.SessaoAplicacao;
import com.metre.adapter.CaixaAdapter;
import com.metre.config.RetrofitConfig;
import com.metre.model.Caixa;
import com.metre.model.CaixaItem;
import com.metre.projetotransacaotelas.TemplateActivity;
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
        System.out.println("onViewCreated");
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

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecharCaixa(v);
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        System.out.println("onCreateContextMenu");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void carregarMovimentoCaixa(){
        movimento = new ArrayList<>();
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        try {
            System.out.println("ID DO CAIXA: "+SessaoAplicacao.usuario.getCaixaCorrente());
            if(SessaoAplicacao.caixa == null && SessaoAplicacao.usuario.getCaixaCorrente() != null ){
                buscarCaixa(SessaoAplicacao.usuario.getCaixaCorrente());
                btnAbrir.setVisibility(View.GONE);
                btnFechar.setVisibility(View.VISIBLE);
            }else{
                btnAbrir.setVisibility(View.VISIBLE);
                btnFechar.setVisibility(View.GONE);

            }

            Call<List<CaixaItem>> call = new RetrofitConfig().getCaixaService().buscarMovimento(SessaoAplicacao.usuario.getCaixaCorrente());
            call.enqueue(new Callback<List<CaixaItem>>() {
                @Override
                public void onResponse(Call<List<CaixaItem>> call, Response<List<CaixaItem>> response) {
                    if(response.body() != null){
                        movimento.addAll(response.body());
                        for(CaixaItem ci : movimento){
                            System.out.println("CI: "+ci.getHistorico());
                        }
                        lstMovimento.setAdapter(new CaixaAdapter(movimento));
                    }else{
                        System.out.println("NAO VEIO NADA ");
                    }
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
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
        System.out.println("onCreateView");
        return inflater.inflate(R.layout.fragment_caixa, container, false);

    }



    public void buscarCaixa(Integer id){
        Call<Caixa> call = new RetrofitConfig().getCaixaService().buscarCaixa(id);
        call.enqueue(new Callback<Caixa>() {
            @Override
            public void onResponse(Call<Caixa> call, Response<Caixa> response) {
                if(response.body() != null){
                    SessaoAplicacao.caixa = response.body();
                }
            }

            @Override
            public void onFailure(Call<Caixa> call, Throwable t) {
                Toast.makeText(getContext(), "Não estamos encontrando o caixa "+id+" no servidor!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fecharCaixa(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Fechamento de Caixa");
        builder.setMessage("Confirma o fechamento!");

        String positiveText = "Sim";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                    }
                });

        String negativeText = "Não";
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }
}
