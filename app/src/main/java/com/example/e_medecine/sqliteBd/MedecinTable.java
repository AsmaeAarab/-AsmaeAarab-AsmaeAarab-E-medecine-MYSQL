package com.example.e_medecine.sqliteBd;

public class MedecinTable {
    private static final String TABLE_NAME = "medecins";
    private static final String ID = "idMedecin";
    private static final String ID_USER = "idUser";
    private static final String SPECIALITE = "idSpecialite";
    private static final String TYPE = "typeMedecin";// e-doctor or simple doctor
    private static final String LOCALISATION = "localisationMedecin";// localisation du medecin dans le cas du non e-medecin
    private static final String CHARTE = "TermeCondition";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID  +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ID_USER +" INTEGER, "
                    + SPECIALITE + " INTEGER, "
                    + TYPE +" TEXT, "
                    + LOCALISATION +" TEXT, "
                    + CHARTE + " TEXT, "
                    + "FOREIGN KEY(" + ID_USER + ") REFERENCES users(idUser),"
                    + "FOREIGN KEY(" + SPECIALITE + ") REFERENCES specialites(idSpecialite))";

    public static String CREATE_TABLE(){
        return CREATE_TABLE;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }
}
