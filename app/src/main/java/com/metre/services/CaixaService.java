package com.metre.services;

import com.metre.model.AbrirCaixa;
import com.metre.model.Caixa;
import com.metre.model.CaixaItem;
import com.metre.model.FechamentoCaixa;
import com.metre.model.Grupo;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CaixaService {


    @POST("caixa/abrir")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<com.metre.model.AbrirCaixa> abrirCaixa(@Body com.metre.model.AbrirCaixa abertura);



    @GET("caixa/{idCaixa}")
    Call<Caixa> buscarCaixa(@Path("idCaixa")Integer idCaixa);

    @GET("caixa/listar/{idCaixa}")
    Call<List<CaixaItem>> buscarMovimento(@Path("idCaixa")Integer idCaixa);

    @GET("caixa/saldo/{idCaixa}")
    Call<String> saldo(@Path("idCaixa")Integer idCaixa);


    @GET("caixa/fechar/{idCaixa}")
    Call<FechamentoCaixa> fecharCaixa(@Path("idCaixa")Integer idCaixa);


}
