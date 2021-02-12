package com.example.e_medecine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Medecin implements Serializable {
    @SerializedName("idMedecin")
    @Expose
    private Integer  idMedecin;
    @SerializedName("idUser")
    @Expose
    private Integer idUser;
    @SerializedName("idSpecialite")
    @Expose
    private Integer  idSpecialite;
    @SerializedName("typeMedecin")
    @Expose
    private String typeMedecin;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("localisationMedecin")
    @Expose
    private String localisationMedecin;
    @SerializedName("experience")
    @Expose
    private int experience;
    @SerializedName("frais")
    @Expose
    private int frais;
    @SerializedName("nomUser")
    @Expose
    private String nomUser;
    @SerializedName("prenomUser")
    @Expose
    private String prenomUser;
    @SerializedName("imageUser")
    @Expose
    private byte[] imageUser;
//to do get data from medecin user and specialitie separately
    /*
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

    public Medecin( int idUserMedecin, int idSpecialiteMedecin, String typeMedecin, String specialite, String location, int experience, int frais, String nomMedecin, String prenomMedecin, byte[] imageMedecin) {
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

     */

    public Medecin(Integer idMedecin, Integer idUser, Integer idSpecialite, String typeMedecin, String label, String localisationMedecin, int experience, int frais, String nomUser, String prenomUser,byte[] imageUser) {
        this.idMedecin = idMedecin;
        this.idUser = idUser;
        this.idSpecialite = idSpecialite;
        this.typeMedecin = typeMedecin;
        this.label = label;
        this.localisationMedecin = localisationMedecin;
        this.experience = experience;
        this.frais = frais;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.imageUser = imageUser;
    }

    public Medecin() {
    }

    public Integer getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Integer idMedecin) {
        this.idMedecin = idMedecin;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(Integer idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    public String getTypeMedecin() {
        return typeMedecin;
    }

    public void setTypeMedecin(String typeMedecin) {
        this.typeMedecin = typeMedecin;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLocalisationMedecin() {
        return localisationMedecin;
    }

    public void setLocalisationMedecin(String localisationMedecin) {
        this.localisationMedecin = localisationMedecin;
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

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public byte[]  getImageUser() {
        return imageUser;
    }

    public void setImageUser(byte[]  imageUser) {
        this.imageUser = imageUser;
    }
}
