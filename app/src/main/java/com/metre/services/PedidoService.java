package com.metre.services;

import com.metre.model.AbrirCaixa;
import com.metre.model.Caixa;
import com.metre.model.CaixaItem;
import com.metre.model.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PedidoService {


    @POST("pedido/registrar")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<Pedido> registrarPedido(@Body Pedido pedido);




}
