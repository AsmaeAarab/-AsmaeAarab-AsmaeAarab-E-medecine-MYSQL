package com.example.e_medecine.service;

import com.example.e_medecine.model.Specialite;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpecialiteService {
    @GET("liste/")
    Call<List<Specialite>> getspecialite();
}
