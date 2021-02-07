package com.example.e_medecine.model;

import java.io.Serializable;

public class Ville implements Serializable {
    private Integer idVille;
    private String label;

    public Ville(int idVille, String label) {
        this.idVille = idVille;
        this.label = label;
    }
    public Ville() {
    }
    public Integer getIdVille() {
        return idVille;
    }

    public void setIdVille(Integer idVille) {
        this.idVille = idVille;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}