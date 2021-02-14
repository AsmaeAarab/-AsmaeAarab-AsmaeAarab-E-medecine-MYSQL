package com.example.e_medecine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Patient implements Serializable {

    @SerializedName("idPatient")
    @Expose
    private int idPatient;
    @SerializedName("idUser")
    @Expose
    private Users idUser;
    @SerializedName("agePatient")
    @Expose
    private String agePatient;
    @SerializedName("adresse")
    @Expose
    private String adresse;
    @SerializedName("cnssPatient")
    @Expose
    private String cnssPatient;

    public Patient()
    {

    }

    public Patient(int idPatient) {
        this.idPatient = idPatient;
    }

    public Patient(int idPatient, Users idUser, String agePatient, String adresse, String cnssPatient) {
        super();
        this.idPatient = idPatient;
        this.idUser = idUser;
        this.agePatient = agePatient;
        this.adresse = adresse;
        this.cnssPatient = cnssPatient;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    public String getAgePatient() {
        return agePatient;
    }

    public void setAgePatient(String agePatient) {
        this.agePatient = agePatient;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCnssPatient() {
        return cnssPatient;
    }

    public void setCnssPatient(String cnssPatient) {
        this.cnssPatient = cnssPatient;
    }


}
