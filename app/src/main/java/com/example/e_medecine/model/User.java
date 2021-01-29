package com.example.e_medecine.model;

public class User {
     private int idUser;
     private String nomUser;
     private String prenomUser;
     private String genre;
     private String tele;
     private byte[] imageUser;
     private int idVille;
     private String email;
     private String password;
     private String role;

    public User() {
    }

    public User( String nomUser, String prenomUser, String genre, String tele, byte[] imageUser, int idVille, String email, String password, String role) {
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

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
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
