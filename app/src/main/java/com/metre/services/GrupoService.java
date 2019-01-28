package com.metre.services;

import com.metre.model.Grupo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GrupoService {
    @GET("grupos")
    Call<List<Grupo>> listarGrupos();
}
