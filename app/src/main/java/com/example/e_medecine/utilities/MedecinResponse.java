package com.example.e_medecine.utilities;


import com.example.e_medecine.model.Medecin;

import java.util.List;

public class MedecinResponse {
    private List<Medecin> medecin;

    public List<Medecin> getMedecin() {
        return medecin;
    }

    public void setMedecin(List<Medecin> medecin) {
        this.medecin = medecin;
    }
}
