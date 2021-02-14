package com.example.e_medecine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class RDV {

    @Expose
    @SerializedName("idPatient")
    private Patient idPatient;
    @Expose
    @SerializedName("idMedecin")
    private Medecin idMedecin;
    @Expose
    @SerializedName("dateRDV")
    private String dateRDV;
    @Expose
    @SerializedName("titreRDV")
    private String titreRDV;
    @Expose
    @SerializedName("idRDV")
    private int idRDV;

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

    public String getDateRDV() {
        return dateRDV;
    }

    public void setDateRDV(String dateRDV) {
        this.dateRDV = dateRDV;
    }

    public String getTitreRDV() {
        return titreRDV;
    }

    public void setTitreRDV(String titreRDV) {
        this.titreRDV = titreRDV;
    }

    public int getIdRDV() {
        return idRDV;
    }

    public void setIdRDV(int idRDV) {
        this.idRDV = idRDV;
    }
}
