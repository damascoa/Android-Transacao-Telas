package com.metre.services;

import com.metre.model.Grupo;
import com.metre.model.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProdutoService {
    @GET("produtos/{idgrupo}")
    Call<List<Produto>> listarProdutos(@Path("idgrupo") Integer id);
}
