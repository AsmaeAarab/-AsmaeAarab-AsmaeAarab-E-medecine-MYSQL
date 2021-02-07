package com.example.e_medecine.utilities;

import com.example.e_medecine.service.SpecialiteService;

public class APIs {

    public static final String URL_001="http://192.168.0.106:8080/";

    public static SpecialiteService getSpecialiteService(){
        return  Client.getClient(URL_001).create(SpecialiteService.class);
    }
}
