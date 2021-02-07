package com.example.e_medecine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Users implements Serializable {

    @SerializedName("id_user")
    @Expose
    private int idUser;
    @SerializedName("nom_user")
    @Expose
    private String nomUser;
    @SerializedName("prenom_user")
    @Expose
    private String prenomUser;
    @SerializedName("genre_user")
    @Expose
    private String genre;
    @SerializedName("telephone_user")
    @Expose
    private String tele;
    @SerializedName("image_user")
    @Expose
    private byte[] imageUser;
    @SerializedName("id_ville")
    @Expose
    private Ville idVille;
    @SerializedName("email_user")
    @Expose
    private String email;
    @SerializedName("password_user")
    @Expose
    private String password;
    @SerializedName("role_user")
    @Expose
    private String role;



    public Users() {
    }

    public Users( String nomUser, String prenomUser, String genre, String tele, byte[] imageUser, Ville idVille, String email, String password, String role) {
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.genre = genre;
        this.tele = tele;
        this.imageUser = imageUser;
        this.idVille = idVille;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
