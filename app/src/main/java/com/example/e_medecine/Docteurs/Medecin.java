package com.example.e_medecine.Docteurs;

import com.example.e_medecine.model.Specialites;
import com.example.e_medecine.model.Users;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Medecin implements Serializable {
    @SerializedName("idMedecin")
    @Expose
    private int idMedecin;
    @SerializedName("idUser")
    @Expose
    private Users idUserMedecin;
    @SerializedName("idSpecialite")
    @Expose
    private Specialites idSpecialiteMedecin;
    @SerializedName("typeMedecin")
    @Expose
    private String typeMedecin;
    @SerializedName("localisationMedecin")
    @Expose
    private String location;
    @SerializedName("experience")
    @Expose
    private int experience;
    @SerializedName("frais")
    @Expose
    private int frais;
    @SerializedName("termecondition")
    @Expose
    private String termeCondition;
    public Medecin(){

    }

    public Medecin(Users idUserMedecin, Specialites idSpecialiteMedecin, String typeMedecin, String location, int experience, int frais, String termeCondition) {
        this.idUserMedecin = idUserMedecin;
        this.idSpecialiteMedecin = idSpecialiteMedecin;
        this.typeMedecin = typeMedecin;
        this.location = location;
        this.experience = experience;
        this.frais = frais;
        this.termeCondition = termeCondition;
    }

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public Users getIdUserMedecin() {
        return idUserMedecin;
    }

    public void setIdUserMedecin(Users idUserMedecin) {
        this.idUserMedecin = idUserMedecin;
    }

    public Specialites getIdSpecialiteMedecin() {
        return idSpecialiteMedecin;
    }

    public void setIdSpecialiteMedecin(Specialites idSpecialiteMedecin) {
        this.idSpecialiteMedecin = idSpecialiteMedecin;
    }

    public String getTypeMedecin() {
        return typeMedecin;
    }

    public void setTypeMedecin(String typeMedecin) {
        this.typeMedecin = typeMedecin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getFrais() {
        return frais;
    }

    public void setFrais(int frais) {
        this.frais = frais;
    }

    public String getTermeCondition() {
        return termeCondition;
    }

    public void setTermeCondition(String termeCondition) {
        this.termeCondition = termeCondition;
    }
}
