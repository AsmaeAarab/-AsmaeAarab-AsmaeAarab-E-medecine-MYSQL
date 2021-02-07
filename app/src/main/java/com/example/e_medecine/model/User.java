package com.example.e_medecine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

public class User implements Serializable {
    private int idUser;
    private String nomUser;
    private String prenomUser;
    private String genreUser;
    private String telephoneUser;
    private byte[] imageUser;
    private int idVille;
    private String emailUser;
    private String passwordUser;
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

