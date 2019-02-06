package com.metre.projetotransacaotelas.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.metre.SessaoAplicacao;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.subtelas.LoginActivity;

public class ConfiguracaoFragment extends Fragment {

    private AppCompatEditText iptIP;
    private AppCompatEditText iptPorta;
    private AppCompatEditText iptContexto;
    private Button btnConfirmar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnConfirmar = view.findViewById(R.id.btnConfirmar);
        iptIP = view.findViewById(R.id.iptIP);
        iptPorta = view.findViewById(R.id.iptPorta);
        iptContexto = view.findViewById(R.id.iptContexto);
        super.onViewCreated(view, savedInstanceState);
        btnConfirmar = view.findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarConfiguracao(v);
            }
        });

        if(SessaoAplicacao.preferencias.getString("ip","192.168.1.199") != null){
            iptIP.setText(SessaoAplicacao.preferencias.getString("ip","192.168.1.199"));
            iptPorta.setText(SessaoAplicacao.preferencias.getString("porta","8080"));
            iptContexto.setText(SessaoAplicacao.preferencias.getString("contexto","app"));
        }
    }

    public void salvarConfiguracao(View view){
        if(iptIP.getText() == null){
            Toast.makeText(view.getContext(), "Informe o IP!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(iptPorta.getText() == null){
            Toast.makeText(view.getContext(), "Informe a porta!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(iptContexto.getText() == null){
            Toast.makeText(view.getContext(), "Informe o contexto!", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences.Editor editor =  SessaoAplicacao.preferencias.edit();
        editor.putString("ip",iptIP.getText().toString());
        editor.putString("porta",iptPorta.getText().toString());
        editor.putString("context",iptContexto.getText().toString());
        editor.apply();
        Toast.makeText(view.getContext(), "Configuração efetuada com sucesso!", Toast.LENGTH_SHORT).show();
        Intent it =new Intent(getContext(),LoginActivity.class);
        startActivity(it);
    }
}
