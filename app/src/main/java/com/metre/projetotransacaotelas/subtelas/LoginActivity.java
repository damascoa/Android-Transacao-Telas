package com.metre.projetotransacaotelas.subtelas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.metre.SessaoAplicacao;
import com.metre.config.RetrofitConfig;
import com.metre.model.Usuario;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.TemplateActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private AppCompatEditText iptUsuario;
    private AppCompatEditText iptSenha;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Metre Ticket");

        iptUsuario = findViewById(R.id.iptUsuario);
        iptSenha = findViewById(R.id.iptSenha);
        btnLogin = findViewById(R.id.btnLogin);

    }

    public void login(View view){
        if(iptUsuario.getText().toString().isEmpty()){
            Toast.makeText(this, "Informe o usuário!", Toast.LENGTH_SHORT).show();
            iptUsuario.requestFocus();
            return;
        }
        if(iptSenha.getText().toString().isEmpty()){
            Toast.makeText(this, "Informe o senha!", Toast.LENGTH_SHORT).show();
            iptSenha.requestFocus();
            return;
        }

        Call<Usuario> call = new RetrofitConfig().getUsuarioService().login(iptUsuario.getText().toString(),iptSenha.getText().toString());
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.body() != null){
                    SessaoAplicacao.usuario = response.body();
                    Intent it = new Intent(LoginActivity.this,TemplateActivity.class);
                    startActivity(it);
                }else{
                    Toast.makeText(LoginActivity.this, "Usuario ou senha inválidos!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                Log.e("LoginActivity", "Erro ao tentar logar: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Erro ao tentar logar: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
