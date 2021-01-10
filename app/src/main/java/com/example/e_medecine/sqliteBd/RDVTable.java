package com.example.e_medecine.sqliteBd;

public class RDVTable {

    private static final String TABLE_NAME = "RDVs";
    private static final String ID = "idRDV";
    private static final String TITRE = "titreRDV";
    private static final String DATE = "dateRDV";
    private static final String ID_PATIENT = "idPatient";
    private static final String ID_Medecin = "idMedecin";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID  +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TITRE +" TEXT, "
                    + DATE +" DATE, "
                    + ID_PATIENT +" INTEGER, "
                    + ID_Medecin +" INTEGER, "
                    + "FOREIGN KEY(" + ID_PATIENT + ") REFERENCES patients(idPatient),"
                    + "FOREIGN KEY(" + ID_Medecin + ") REFERENCES medecins(idMedecin))";

    public static String CREATE_TABLE(){
        return CREATE_TABLE;
    }
    public static String getTableName() {
        return TABLE_NAME;
    }
}
