package com.metre.services;

import com.metre.model.Administradora;
import com.metre.model.FormaQuitacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FormasService {
    @GET("formas")
    Call<List<FormaQuitacao>> listarFormas();
}
