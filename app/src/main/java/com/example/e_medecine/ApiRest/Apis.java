package com.example.e_medecine.ApiRest;

public class Apis {
    public static final String URL_002="http://192.168.1.9:8080/patients/";
    public static final String URL_001="http://192.168.1.5:8080/user/";
    public static PatientService getPatientsService(){
        return  Cliente.getCliente(URL_002).create(PatientService.class);
    }
    public static MedecinService getMedecinService(){
        return Medecin.getCliente(URL_001).create(MedecinService.class);
    }
}




