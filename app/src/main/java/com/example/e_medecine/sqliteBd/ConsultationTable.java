package com.example.e_medecine.sqliteBd;

public class ConsultationTable {
    private static final String TABLE_NAME = "consultations";
    private static final String ID = "idConsultation";
    private static final String ID_RDV = "idRDV";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID  +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ID_RDV +" INTEGER, "
                    + "FOREIGN KEY(" + ID_RDV + ") REFERENCES RDVs(idRDV))";

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String CreateTable(){
        return CREATE_TABLE;
    }

}

