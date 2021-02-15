package com.example.e_medecine.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medecin {

    @Expose
    @SerializedName("prenom_user")
    private String prenom_user;
    @Expose
    @SerializedName("nom_user")
    private String nom_user;
    @Expose
    @SerializedName("experience")
    private int experience;
    @Expose
    @SerializedName("frais")
    private int frais;
    @Expose
    @SerializedName("telephone_user")
    private String telephone_user;
    @Expose
    @SerializedName("image_user")
    private String image_user;
    @Expose
    @SerializedName("localisation_medecin")
    private String localisation_medecin;
    @Expose
    @SerializedName("label")
    private String label;
    @Expose
    @SerializedName("id_medecin")
    private int id_medecin;

    public Medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
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

    public String getTelephone_user() {
        return telephone_user;
    }

    public void setTelephone_user(String telephone_user) {
        this.telephone_user = telephone_user;
    }

    public String getImage_user() {
        return image_user;
    }

    public void setImage_user(String image_user) {
        this.image_user = image_user;
    }

    public String getLocalisation_medecin() {
        return localisation_medecin;
    }

    public void setLocalisation_medecin(String localisation_medecin) {
        this.localisation_medecin = localisation_medecin;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }
}