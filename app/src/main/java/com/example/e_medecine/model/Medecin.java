package com.example.e_medecine.model;

import java.io.Serializable;

public class Medecin implements Serializable {
    private int idMedecin;
    private int idUserMedecin;
    private int idSpecialiteMedecin;
    private String typeMedecin;
    private String location;
    private int experience;
    private int frais;
    private String TermeCondition;

    public Medecin() {
    }

    public Medecin(int idMedecin, int idUserMedecin, int idSpecialiteMedecin, String typeMedecin, String location, int experience, int frais, String termeCondition) {
        this.idMedecin = idMedecin;
        this.idUserMedecin = idUserMedecin;
        this.idSpecialiteMedecin = idSpecialiteMedecin;
        this.typeMedecin = typeMedecin;
        this.location = location;
        this.experience = experience;
        this.frais = frais;
        TermeCondition = termeCondition;
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

    public String getTermeCondition() {
        return TermeCondition;
    }

    public void setTermeCondition(String termeCondition) {
        TermeCondition = termeCondition;
    }
}
