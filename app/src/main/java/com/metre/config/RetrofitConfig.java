package com.metre.config;

import com.metre.services.GrupoService;
import com.metre.services.ProdutoService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit =  new Retrofit.Builder().baseUrl("http://192.168.1.199:8080/app/services/ticket/")
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
}
