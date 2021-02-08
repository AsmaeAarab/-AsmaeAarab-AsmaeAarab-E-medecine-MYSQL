package com.example.e_medecine.ApiRest;




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

    @Headers({"Accept: application/json"})
    @POST("find/insert/user")
    Call<Users>addUserM(@Body Users user);

    @GET("/find/user/Phone/login/{Docteur}/{Password}/{Phone}")
    User FinduserbyPhone(@Path("Password") String Pass, @Path("Phone") String Phone, @Path("Docteur") String Docteur);

    @GET("find/user/{Phone}")
    int getIdUser(@Path("Phone") String Phone);
}
