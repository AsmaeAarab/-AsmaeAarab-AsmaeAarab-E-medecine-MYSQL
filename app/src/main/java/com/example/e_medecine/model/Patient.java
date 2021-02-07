package com.example.e_medecine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Patient {
    @SerializedName("age_patient")
    @Expose
    private String age;
    @SerializedName("adresse")
    @Expose
    private String adresse;
    @SerializedName("id_user")
    @Expose
    private int id_user;
    @SerializedName("cnss_patient")
    @Expose
    private String cnss;

    public Patient() {
    }

    public Patient(String age, String adresse, int id_user, String cnss) {
        this.age = age;
        this.adresse = adresse;
        this.id_user = id_user;
        this.cnss = cnss;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getCnss() {
        return cnss;
    }

    public void setCnss(String cnss) {
        this.cnss = cnss;
    }
}
