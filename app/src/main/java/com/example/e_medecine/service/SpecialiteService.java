package com.example.e_medecine.service;

import com.example.e_medecine.model.Specialite;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SpecialiteService {
    @GET("all")
    Call<List<Specialite>> getspecialite();

    @GET("image/")
    Call<ResponseBody> retrieveImageData();
}
