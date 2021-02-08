package com.example.e_medecine.utilities;

import com.example.e_medecine.service.SpecialiteService;

public class APIs {

    public static final String URL_001="http://localhost:8080/specialites/";

    public static SpecialiteService getSpecialiteService(){
        return  Client.getClient(URL_001).create(SpecialiteService.class);
    }
}
