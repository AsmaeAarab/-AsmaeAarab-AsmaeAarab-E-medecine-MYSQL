package com.example.e_medecine.model;

import java.io.Serializable;

public  class Specialite implements Serializable {
    private int id_specialite;
    private String labe;
    private byte[] imageSpecialite;

    public Specialite() {
    }
    public Specialite(int id_specialite, String labe, byte[] imageSpecialite) {
        this.id_specialite = id_specialite;
        this.labe = labe;
        this.imageSpecialite = imageSpecialite;
    }

    public byte[] getImageSpecialite() {
        return imageSpecialite;
    }

    public void setImageSpecialite(byte[] imageSpecialite) {
        this.imageSpecialite = imageSpecialite;
    }

    public int getId_specialite() {
        return id_specialite;
    }

    public void setId_specialite(int id_specialite) {
        this.id_specialite = id_specialite;
    }

    public String getLabe() {
        return labe;
    }

    public void setLabe(String labe) {
        this.labe = labe;
    }

}
