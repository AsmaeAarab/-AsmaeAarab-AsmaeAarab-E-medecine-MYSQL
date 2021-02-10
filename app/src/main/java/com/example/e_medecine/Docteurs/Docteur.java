package com.example.e_medecine.Docteurs;

import com.example.e_medecine.model.Specialite;
import com.example.e_medecine.model.Users;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Docteur implements Serializable {
    @SerializedName("idMedecin")
    @Expose
    private int idMedecin;
    @SerializedName("idUser")
    @Expose
    private int idUserMedecin;
    @SerializedName("idSpecialite")
    @Expose
    private Specialite idSpecialiteMedecin;
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
    @SerializedName("TermeCondition")
    @Expose
    private String TermeCondition;

    public Docteur()
    {

    }

    public Docteur(int idUserMedecin, Specialite idSpecialiteMedecin, String typeMedecin, String location, int experience, int frais, String termeCondition) {
        this.idUserMedecin = idUserMedecin;
        this.idSpecialiteMedecin = idSpecialiteMedecin;
        this.typeMedecin = typeMedecin;
        this.location = location;
        this.experience = experience;
        this.frais = frais;
        TermeCondition = termeCondition;
    }

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public int getIdUserMedecin() {
        return idUserMedecin;
    }

    public void setIdUserMedecin(int idUserMedecin) {
        this.idUserMedecin = idUserMedecin;
    }

    public Specialite getIdSpecialiteMedecin() {
        return idSpecialiteMedecin;
    }

    public void setIdSpecialiteMedecin(Specialite idSpecialiteMedecin) {
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
        return TermeCondition;
    }

    public void setTermeCondition(String termeCondition) {
        TermeCondition = termeCondition;
    }
}
