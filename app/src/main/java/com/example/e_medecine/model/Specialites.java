package com.example.e_medecine.model;

import java.io.Serializable;

public  class Specialites implements Serializable {
    private int idSpecialite;
    private String label;
    private byte[] imageSpecialite;

    public Specialites() {
    }
    public Specialites(int idSpecialite, String label) {
        this.idSpecialite = idSpecialite;
        this.label = label;
    }
    public Specialites(int id_specialite, String label, byte[] imageSpecialite) {
        this.idSpecialite = id_specialite;
        this.label = label;
        this.imageSpecialite = imageSpecialite;
    }

    public byte[] getImageSpecialite() {
        return imageSpecialite;
    }

    public void setImageSpecialite(byte[] imageSpecialite) {
        this.imageSpecialite = imageSpecialite;
    }

    public int getId_specialite() {
        return idSpecialite;
    }

    public void setId_specialite(Integer id_specialite) {
        this.idSpecialite = id_specialite;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String labe) {
        this.label = label;
    }
}
