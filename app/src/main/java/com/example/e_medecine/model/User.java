package com.example.e_medecine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
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
    private int idVille;
    @SerializedName("emailUser")
    @Expose
    private String emailUser;
    @SerializedName("passwordUser")
    @Expose
    private String passwordUser;
    @SerializedName("roleUser")
    @Expose
    private String roleUser;

    public User() {
    }

    public User( String nomUser, String prenomUser, String genre, String tele, byte[] imageUser, int idVille, String email, String password, String role) {
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.genreUser = genre;
        this.telephoneUser = tele;
        this.imageUser = imageUser;
        this.idVille = idVille;
        this.emailUser = email;
        this.passwordUser = password;
        this.roleUser = role;
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


    public byte[] getImageUser() {
        return imageUser;
    }

    public void setImageUser(byte[] imageUser) {
        this.imageUser = imageUser;
    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
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

