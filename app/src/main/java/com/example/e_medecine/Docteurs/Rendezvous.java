package com.example.e_medecine.Docteurs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Rendezvous implements Serializable {
    @SerializedName("idRDV")
    @Expose
    private int id;
    @SerializedName("idPatient")
    @Expose
    private int idp;
    @SerializedName("idMedecin")
    @Expose
    private int idm;
    @SerializedName("imageUser")
    @Expose
    private byte[] Image;
    @SerializedName("image")
    @Expose
    private String Imagenew;
    @SerializedName("nomUser")
    @Expose
    private String Nom;
    @SerializedName("prenomUser")
    @Expose
    private String Prenom;
    @SerializedName("titreRDV")
    @Expose
    private String titreRdv;
    @SerializedName("dateRDV")
    @Expose
    private String date;

    public Rendezvous(int idp, int idm, String imagenew, String nom, String prenom, String titreRdv, String date) {
        this.idp = idp;
        this.idm = idm;
        Imagenew = imagenew;
        Nom = nom;
        Prenom = prenom;
        this.titreRdv = titreRdv;
        this.date = date;
    }

    public Rendezvous(int id, int idp, int idm, byte[] image, String nom, String prenom, String titreRdv, String date) {
        this.id = id;
        this.idp = idp;
        this.idm = idm;
        Image = image;
        Nom = nom;
        Prenom = prenom;
        this.titreRdv = titreRdv;
        this.date = date;
    }

    public Rendezvous(int idp, int idm, byte[] image, String nom, String prenom, String titreRdv, String date) {
        this.idp = idp;
        this.idm = idm;
        Image = image;
        Nom = nom;
        Prenom = prenom;
        this.titreRdv = titreRdv;
        this.date = date;
    }

    public String getImagenew() {
        return Imagenew;
    }

    public void setImagenew(String imagenew) {
        Imagenew = imagenew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getTitreRdv() {
        return titreRdv;
    }

    public void setTitreRdv(String titreRdv) {
        this.titreRdv = titreRdv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}