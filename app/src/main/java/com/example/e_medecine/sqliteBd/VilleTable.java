package com.example.e_medecine.sqliteBd;

public class VilleTable {
    private static final String TABLE_NAME = "villes";
    private static final String ID = "idVille";
    private static final String LABEL = "label";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID  +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + LABEL +" TEXT)";

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String CreateTable(){
        return CREATE_TABLE;
    }
}
