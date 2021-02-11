package com.example.e_medecine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specialite {
    @SerializedName("idSpecialite")
    @Expose
    private Integer idSpecialite;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("imageSpecialite")
    @Expose
    private String imageSpecialite;

    public Specialite() {

    }
    public Specialite( String label, String imageSpecialite) {
        this.label = label;
        this.imageSpecialite = imageSpecialite;
    }
    public Specialite(Integer idSpecialite, String label, String imageSpecialite) {
        this.idSpecialite = idSpecialite;
        this.label = label;
        this.imageSpecialite = imageSpecialite;
    }

    public Integer getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(Integer idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImageSpecialite() {
        return imageSpecialite;
    }

    public void setImageSpecialite(String imageSpecialite) {
        this.imageSpecialite = imageSpecialite;
    }

}