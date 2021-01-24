package com.example.e_medecine.model;

public class Medecin {

    private int idMedecin;
    private String nom;
    private String prenom;
    private String specialite;
    private String location;
    private String tele;
    private int experience;
    private int frais;
    private int imageMedecin;

    public Medecin() {
    }

    public Medecin(int idMedecin, String nom, String prenom, String specialite, String location, String tele, int experience, int frais, int imageMedecin) {
        this.idMedecin = idMedecin;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.location = location;
        this.tele = tele;
        this.experience = experience;
        this.frais = frais;
        this.imageMedecin = imageMedecin;
    }

    public int getImageMedecin() {
        return imageMedecin;
    }

    public void setImageMedecin(int imageMedecin) {
        this.imageMedecin = imageMedecin;
    }

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
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
}
