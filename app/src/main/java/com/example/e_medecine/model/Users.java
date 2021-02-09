package com.example.e_medecine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Users implements Serializable {

    @SerializedName("idUser")
    @Expose
    private int idUser;
    @SerializedName("nomUser")
    @Expose
    private String nomUser;
    @SerializedName("prenomUser")
    @Expose
    private String prenomUser;
    @SerializedName("genreUser")
    @Expose
    private String genreUser;
    @SerializedName("telephoneUser")
    @Expose
    private String telephoneUser;
    @SerializedName("imageUser")
    @Expose
    private byte[] imageUser;
    @SerializedName("idVille")
    @Expose
    private Ville idVille;
    @SerializedName("emailUser")
    @Expose
    private String emailUser;
    @SerializedName("passwordUser")
    @Expose
    private String passwordUser;
    @SerializedName("roleUser")
    @Expose
    private String roleUser;

    public Users() {
    }

    public Users(int idUser) {
        this.idUser = idUser;
    }

    public Users(int idUser, String nomUser, String prenomUser, String genreUser, String telephoneUser, byte[] imageUser, Ville idVille, String emailUser, String passwordUser, String roleUser) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.genreUser = genreUser;
        this.telephoneUser = telephoneUser;
        this.imageUser = imageUser;
        this.idVille = idVille;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.roleUser = roleUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getGenreUser() {
        return genreUser;
    }

    public void setGenreUser(String genreUser) {
        this.genreUser = genreUser;
    }

    public String getTelephoneUser() {
        return telephoneUser;
    }

    public void setTelephoneUser(String telephoneUser) {
        this.telephoneUser = telephoneUser;
    }

    public byte[] getImageUser() {
        return imageUser;
    }

    public void setImageUser(byte[] imageUser) {
        this.imageUser = imageUser;
    }

    public Ville getIdVille() {
        return idVille;
    }

    public void setIdVille(Ville idVille) {
        this.idVille = idVille;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }
}
