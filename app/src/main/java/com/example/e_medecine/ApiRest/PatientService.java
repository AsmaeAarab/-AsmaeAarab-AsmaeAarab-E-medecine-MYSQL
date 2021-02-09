package com.example.e_medecine.ApiRest;

import com.example.e_medecine.model.Patient;
import com.example.e_medecine.model.User;
import com.example.e_medecine.model.Users;
import com.example.e_medecine.model.Ville;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PatientService {

    @GET("listerPatient")
    Call<List<Users>> getPatients();
    //@Headers("Content-Type: application/json")
    // GetRequest.SetContentEncoding("json")

    @Headers({"Accept: application/json"})
    @POST("ajouter")
    Call<Users>addUser(@Body Users users);

    @Headers({"Accept: application/json"})
    @POST("ajouterPatient")
    Call<Patient>ajoutPatient(@Body Patient patient);

    @GET("getIdVille/{labelVille}")
    int getIdVille(@Path("labelVille")String labelVille);

}
