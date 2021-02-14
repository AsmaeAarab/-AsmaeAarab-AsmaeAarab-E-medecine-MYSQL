package com.example.e_medecine.ApiRest;

import com.example.e_medecine.model.RDV;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface RendezVousService {

    @GET("allRDV/{id_patient}")
    Call<List<RDV>> findAllRDV(@Path("id_patient")int idPatient);

    @Headers({"Accept: application/json"})
    @POST("addRDV")
    Call<RDV> addRDV(@Body RDV rdv);
}
