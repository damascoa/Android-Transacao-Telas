package com.metre.config;

import com.metre.SessaoAplicacao;
import com.metre.services.CaixaService;
import com.metre.services.CartaoService;
import com.metre.services.FormasService;
import com.metre.services.GrupoService;
import com.metre.services.ProdutoService;
import com.metre.services.UsuarioService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final String URL =
            "http://"+
            SessaoAplicacao.preferencias.getString("ip","192.168.1.199")+":"+
            SessaoAplicacao.preferencias.getString("porta","8080")+"/"+
            SessaoAplicacao.preferencias.getString("contexto","app");

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit =  new Retrofit.Builder().baseUrl(URL+"/services/ticket/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();;
    }

    public GrupoService getGrupoService(){
        System.out.println("URL: "+this.retrofit.baseUrl().toString());
        GrupoService gs = this.retrofit.create(GrupoService.class);
        return gs;
    }

    public ProdutoService getProdutoService(){
        ProdutoService ps = this.retrofit.create(ProdutoService.class);
        return ps;
    }

    public UsuarioService getUsuarioService(){
        UsuarioService ps = this.retrofit.create(UsuarioService.class);
        return ps;
    }

    public CaixaService getCaixaService(){
        CaixaService cs = this.retrofit.create(CaixaService.class);
        return cs;
    }

    public FormasService getFormaService(){
        FormasService cs = this.retrofit.create(FormasService.class);
        return cs;
    }
    public CartaoService getCartaoService(){
        CartaoService cs = this.retrofit.create(CartaoService.class);
        return cs;
    }
}
