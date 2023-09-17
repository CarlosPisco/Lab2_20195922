package com.example.lab2.services;

import com.example.lab2.dto.Perfil;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfileService {

    //Separar la URL base del URL path
    @GET("/api/")
    Call<Perfil> getPerfil();


}
