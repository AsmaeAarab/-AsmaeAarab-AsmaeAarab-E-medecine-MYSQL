package com.example.e_medecine.model;

public class Medecin {
    private int idMedecin;
    private int idUserMedecin;
    private int idSpecialiteMedecin;
    private String typeMedecin;
    private String specialite;
    private String location;
    private int experience;
    private int frais;
    private String nomMedecin;
    private String prenomMedecin;
    private byte[] imageMedecin;


    public Medecin() {
    }

    public Medecin(int idMedecin, int idUserMedecin, int idSpecialiteMedecin, String typeMedecin, String specialite, String location, int experience, int frais, String nomMedecin, String prenomMedecin, byte[] imageMedecin) {
        this.idMedecin = idMedecin;
        this.idUserMedecin = idUserMedecin;
        this.idSpecialiteMedecin = idSpecialiteMedecin;
        this.typeMedecin = typeMedecin;
        this.specialite = specialite;
        this.location = location;
        this.experience = experience;
        this.frais = frais;
        this.nomMedecin = nomMedecin;
        this.prenomMedecin = prenomMedecin;
        this.imageMedecin = imageMedecin;
    }

    public String getNomMedecin() {
        return nomMedecin;
    }

    public void setNomMedecin(String nomMedecin) {
        this.nomMedecin = nomMedecin;
    }

    public String getPrenomMedecin() {
        return prenomMedecin;
    }

    public void setPrenomMedecin(String prenomMedecin) {
        this.prenomMedecin = prenomMedecin;
    }

    public byte[] getImageMedecin() {
        return imageMedecin;
    }

    public void setImageMedecin(byte[] imageMedecin) {
        this.imageMedecin = imageMedecin;
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

    public int getIdSpecialiteMedecin() {
        return idSpecialiteMedecin;
    }

    public void setIdSpecialiteMedecin(int idSpecialiteMedecin) {
        this.idSpecialiteMedecin = idSpecialiteMedecin;
    }

    public String getTypeMedecin() {
        return typeMedecin;
    }

    public void setTypeMedecin(String typeMedecin) {
        this.typeMedecin = typeMedecin;
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
