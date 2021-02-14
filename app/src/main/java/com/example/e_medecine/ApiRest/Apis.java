package com.example.e_medecine.ApiRest;


public class Apis {
    //public static final String URL_002="http://192.168.56.1:8080/patients/";
   // public static final String URL_001="http://192.168.56.1:8080/user/";
    public static final String URL_002="http://192.168.1.108:8080/patients/";
    public static final String URL_001="http://192.168.1.108:8080/user/";
    public static final String URL_003="http://192.168.1.108:8080/specialites/";
    public static final String URL_004="http://192.168.1.108:8080/rdv/";
    public static PatientService getPatientsService(){
        return  Cliente.getCliente(URL_002).create(PatientService.class);
    }
    public static MedecinService getMedecinService(){
        return Cliente.getCliente(URL_001).create(MedecinService.class);
    }
    public static SpecialiteService getSpecialiteService(){
        return Cliente.getCliente(URL_003).create(SpecialiteService.class);
    }
    public static RendezVousService getRDVService(){
        return Cliente.getCliente(URL_004).create(RendezVousService.class);
    }
}




