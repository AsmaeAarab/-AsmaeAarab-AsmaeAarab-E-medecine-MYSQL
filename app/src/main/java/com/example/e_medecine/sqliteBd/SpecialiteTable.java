package com.example.e_medecine.sqliteBd;

public class SpecialiteTable {
    private static final String TABLE_NAME = "specialites";
    private static final String ID = "idSpecialite";
    private static final String LABEL = "label";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID  +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + LABEL +" TEXT)";

    public static String CREATE_TABLE(){
        return CREATE_TABLE;
    }
    public static String getTableName() {
        return TABLE_NAME;
    }
}
