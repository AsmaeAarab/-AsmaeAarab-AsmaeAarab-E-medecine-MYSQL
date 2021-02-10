package com.example.e_medecine.ApiRest;




import com.example.e_medecine.Docteurs.Docteur;
import com.example.e_medecine.model.User;
import com.example.e_medecine.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MedecinService {
    /*@GET("listerPatient")
    Call<List<Users>> getPatients();*/
    //@Headers("Content-Type: application/json")
    // GetRequest.SetContentEncoding("json")


    @GET("listerPatient")
    Call<List<Users>> getUsers();
    @Headers({"Accept: application/json"})
    @POST("find/insert/u/user")
    Call<Users>addUserM(@Body Users user);
    @Headers({"Accept: application/json"})
    @POST("find/insert/m/medecin")
    Call<Docteur>addMedecin(@Body Docteur docteur);

    @GET("find/user/Phone/login/{Docteur}/{Password}/{Phone}")
    Call<Users>FinduserbyPhone(@Path("Password") String Pass, @Path("Phone") String Phone, @Path("Docteur") String Docteur);

    @GET("find/user/{Phone}")
    Call<List<Users>>getIdUser(@Path("Phone") String Phone);
}
