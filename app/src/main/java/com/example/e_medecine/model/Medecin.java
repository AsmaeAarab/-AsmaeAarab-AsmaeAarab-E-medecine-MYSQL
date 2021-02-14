package com.example.e_medecine.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Medecin {


    @Expose
    @SerializedName("user")
    private User user;
    @Expose
    @SerializedName("specialite")
    private Specialite specialite;
    @Expose
    @SerializedName("experience")
    private int experience;
    @Expose
    @SerializedName("frais")
    private int frais;
    @Expose
    @SerializedName("localisationMedecin")
    private String localisationMedecin;
    @Expose
    @SerializedName("idMedecin")
    private int idMedecin;

    public Medecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
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

    public String getLocalisationMedecin() {
        return localisationMedecin;
    }

    public void setLocalisationMedecin(String localisationMedecin) {
        this.localisationMedecin = localisationMedecin;
    }

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public static class User {
        @Expose
        @SerializedName("imageUser")
        private String imageUser;
        @Expose
        @SerializedName("telephoneUser")
        private String telephoneUser;
        @Expose
        @SerializedName("prenomUser")
        private String prenomUser;
        @Expose
        @SerializedName("nomUser")
        private String nomUser;
        @Expose
        @SerializedName("idUser")
        private int idUser;

        public String getImageUser() {
            return imageUser;
        }

        public void setImageUser(String imageUser) {
            this.imageUser = imageUser;
        }

        public String getTelephoneUser() {
            return telephoneUser;
        }

        public void setTelephoneUser(String telephoneUser) {
            this.telephoneUser = telephoneUser;
        }

        public String getPrenomUser() {
            return prenomUser;
        }

        public void setPrenomUser(String prenomUser) {
            this.prenomUser = prenomUser;
        }

        public String getNomUser() {
            return nomUser;
        }

        public void setNomUser(String nomUser) {
            this.nomUser = nomUser;
        }

        public int getIdUser() {
            return idUser;
        }

        public void setIdUser(int idUser) {
            this.idUser = idUser;
        }
    }

    public static class Specialite {
        @Expose
        @SerializedName("label")
        private String label;
        @Expose
        @SerializedName("idSpecialite")
        private int idSpecialite;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getIdSpecialite() {
            return idSpecialite;
        }

        public void setIdSpecialite(int idSpecialite) {
            this.idSpecialite = idSpecialite;
        }
    }
}