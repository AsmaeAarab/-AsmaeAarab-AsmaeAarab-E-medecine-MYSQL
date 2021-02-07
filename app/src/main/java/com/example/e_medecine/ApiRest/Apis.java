package com.example.e_medecine.ApiRest;

public class Apis {
    public static final String URL_002="http://192.168.1.9:8080/patients/";

    public static PatientService getPatientsService(){
        return  Cliente.getCliente(URL_002).create(PatientService.class);
    }
}




