package com.example.e_medecine.model;

public class Rendezvous {
    private int id;
    private int idp;
    private int idm;
    private byte[] Image;
    private String Nom;
    private String Prenom;
    private String titreRdv;
    private String date;

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