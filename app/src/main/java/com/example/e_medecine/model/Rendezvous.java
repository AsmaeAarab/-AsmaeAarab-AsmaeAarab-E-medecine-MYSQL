package com.example.e_medecine.model;

import java.io.Serializable;

public class Rendezvous implements Serializable {
    private int idRDV;
    private String titreRDV;
    private String dateRDV;
    private Patient idPatient;
    private Medecin idMedecin;

    public Rendezvous() {
    }
    public Rendezvous(int idRDV) {
        this.idRDV = idRDV;
    }
    public Rendezvous(int idRDV, String titreRDV, String dateRDV, Patient idPatient, Medecin idMedecin) {
        this.idRDV = idRDV;
        this.titreRDV = titreRDV;
        this.dateRDV = dateRDV;
        this.idPatient = idPatient;
        this.idMedecin = idMedecin;
    }

    public int getIdRDV() {
        return idRDV;
    }

    public void setIdRDV(int idRDV) {
        this.idRDV = idRDV;
    }

    public String getTitreRDV() {
        return titreRDV;
    }

    public void setTitreRDV(String titreRDV) {
        this.titreRDV = titreRDV;
    }

    public String getDateRDV() {
        return dateRDV;
    }

    public void setDateRDV(String dateRDV) {
        this.dateRDV = dateRDV;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    public Medecin getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Medecin idMedecin) {
        this.idMedecin = idMedecin;
    }

}