package com.example.e_medecine.Docteurs;

public class Docteur  {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String num;
    private String ville;
    private String Specialite;
    private String Education;
    private String Adresse;
    private String Charte;

    public Docteur()
    {

    }
    public Docteur(int id, String nom, String prenom, String email, String password, String num, String ville, String specialite, String education, String adresse, String charte) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.num = num;
        this.ville = ville;
        Specialite = specialite;
        Education = education;
        Adresse = adresse;
        Charte = charte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSpecialite() {
        return Specialite;
    }

    public void setSpecialite(String specialite) {
        Specialite = specialite;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getCharte() {
        return Charte;
    }

    public void setCharte(String charte) {
        Charte = charte;
    }

    @Override
    public String toString() {
        return "Docteurs{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", num='" + num + '\'' +
                ", ville='" + ville + '\'' +
                ", Specialite='" + Specialite + '\'' +
                ", Education='" + Education + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", Charte='" + Charte + '\'' +
                '}';
    }
}
