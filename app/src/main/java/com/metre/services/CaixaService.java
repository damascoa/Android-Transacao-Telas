package com.metre.services;

import com.metre.model.Caixa;
import com.metre.model.CaixaItem;
import com.metre.model.Grupo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CaixaService {


    @POST("caixa/abrir")
    Call<Caixa> abrirCaixa();



    @GET("caixa/{idCaixa}")
    Call<Caixa> buscarCaixa(@Path("idCaixa")Integer idCaixa);

    @GET("caixa/listar/{idCaixa}")
    Call<List<CaixaItem>> buscarMovimento(@Path("idCaixa")Integer idCaixa);
}
