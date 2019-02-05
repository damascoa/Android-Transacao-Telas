package com.metre.services;

import com.metre.model.Produto;
import com.metre.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {
    @POST("login/{usuario}/{senha}")
    Call<Usuario> login(@Path("usuario") String usuario,@Path("senha") String senha);
}
