package com.metre.projetotransacaotelas.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.metre.SessaoAplicacao;
import com.metre.config.RetrofitConfig;
import com.metre.model.Grupo;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.subtelas.AbrirCaixa;
import com.metre.projetotransacaotelas.subtelas.GrupoActivity;
import com.metre.projetotransacaotelas.subtelas.RecebimentoActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoFragment extends Fragment {
    private LinearLayout conteudo;
    private FloatingActionButton btnReceber;
    LinearLayout.LayoutParams paramButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

    public List<Grupo> grupos = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pedido, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        conteudo = getView().findViewById(R.id.conteudo);
        btnReceber = getView().findViewById(R.id.btnReceber);

        btnReceber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SessaoAplicacao.caixa == null){
                    Toast.makeText(getView().getContext(),"Faça a abertura do caixa primeiro!",Toast.LENGTH_LONG);
                    Intent returnIntent = new Intent(getContext(),AbrirCaixa.class);
                    startActivityForResult(returnIntent,109);
                    return;
                }

                if(SessaoAplicacao.itensPedido != null && SessaoAplicacao.itensPedido.size() > 0){
                    Intent returnIntent = new Intent(getContext(),RecebimentoActivity.class);
                    startActivity(returnIntent);
                }else{
                    Toast.makeText(getView().getContext(),"Não possui nenhum item adicionado!",Toast.LENGTH_SHORT);
                }
            }
        });
        carregarGrupos(view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("DATA CODE: "+resultCode);
        System.out.println("onActivityResult");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        System.out.println("onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }



    public void montarGrid(View view, List<Grupo> grupos){

        for(Grupo g : grupos){
            Button b = new Button(view.getContext());
            b.setText(g.getDescricao());
            String foto = "c"+(g.getImagem());
            try {
                Drawable image = getResources().getDrawable(getResources().getIdentifier(foto, "drawable", "com.metre.projetotransacaotelas"));
                int h = image.getIntrinsicHeight();
                int w = image.getIntrinsicWidth();
                image.setBounds( 0, 0, w, h );
                b.setCompoundDrawables(image,null,null,null);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it = new Intent(getContext(), GrupoActivity.class);
                        it.putExtra("grupo",g);
                        ((AppCompatActivity) getContext()).startActivityForResult(it, 0);
                    }
                });
            }catch (Exception e){
                System.out.println("Erro ao exception: "+e.getMessage());
            }

            conteudo.addView(b,paramButton);
        }
    }


    public void carregarGrupos(View view){
        grupos = new ArrayList<>();
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(view.getContext());
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Aguarde....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setIndeterminate(true);
        progressDoalog.setTitle("Carregando");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();
        Call<List<Grupo>> call = new RetrofitConfig().getGrupoService().listarGrupos();
        call.enqueue(new Callback<List<Grupo>>() {



            @Override
            public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {
                grupos = response.body();
                if(grupos == null){
                     Toast.makeText(getContext(), "Nenhum grupo foi carregado!", Toast.LENGTH_SHORT).show();
                     progressDoalog.dismiss();
                    return;
                }
                montarGrid(view,grupos);
                progressDoalog.dismiss();
            }
            @Override
            public void onFailure(Call<List<Grupo>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getContext(), "Erro ao buscar os grupos no serviço!", Toast.LENGTH_SHORT).show();
                Log.e("GrupoService", "Erro ao buscar o grupo: " + t.getMessage());
            }
        });
    }


}
