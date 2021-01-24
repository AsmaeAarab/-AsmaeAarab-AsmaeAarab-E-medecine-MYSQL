package com.example.e_medecine.sqliteBd;

public class PaiementTable {
    private static final String TABLE_NAME = "paiements";
    private static final String ID = "idPaiment";
    private static final String ID_PATIENT = "idPatient";
    private static final String MONTANT = "montant";
    private static final String DATE_PAIEMENT = "datePaiement";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID  +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ID_PATIENT +" INTEGER, "
                    + MONTANT + " FLOAT, "
                    + DATE_PAIEMENT +" DATE, "
                    + "FOREIGN KEY(" + ID_PATIENT + ") REFERENCES patients(idPatient))";

    public static String CREATE_TABLE(){
        return CREATE_TABLE;
    }
    public static String getTableName() {
        return TABLE_NAME;
    }
}
