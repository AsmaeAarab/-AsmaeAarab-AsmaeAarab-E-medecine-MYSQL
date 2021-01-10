package com.example.e_medecine.sqliteBd;

import android.content.Context;

public class PatientTable {

    private static final String TABLE_NAME = "patients";
    private static final String ID = "idPatient";
    private static final String ID_USER = "idUser";
    private static final String AGE = "agePatient";
    private static final String ADRESSE = "Adresse";
    private static final String CNSS = "cnssPatient";


    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID  +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + AGE + " TEXT, "
                    + ADRESSE +" TEXT, "
                    + ID_USER +" INTEGER, "
                    + CNSS+" TEXT, "
                    + "FOREIGN KEY(" + ID_USER + ") REFERENCES users(idUser))";

    public static String CREATE_TABLE(){
        return CREATE_TABLE;
    }
    public static String getTableName() {
        return TABLE_NAME;
    }
}
