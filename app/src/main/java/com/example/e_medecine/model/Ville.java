package com.example.e_medecine.model;

import java.io.Serializable;

public class Ville implements Serializable {
    private int idVille;
    private String Label;

    public Ville(int idVille, String label) {
        this.idVille = idVille;
        Label = label;
    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
