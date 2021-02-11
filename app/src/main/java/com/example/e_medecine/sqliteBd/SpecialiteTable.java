package com.example.e_medecine.sqliteBd;

public class SpecialiteTable {

    private static final String TABLE_NAME = "specialites";
    private static final String ID = "idSpecialite";
    private static final String LABEL = "label";
    private static final String IMAGE_SPECIALITE="imageSpecialite";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID  +" INTEGER PRIMARY KEY, "
                    + LABEL +" TEXT, "
                    + IMAGE_SPECIALITE + " BLOB )";

    public static String CREATE_TABLE(){
        return CREATE_TABLE;
    }
    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getID() {
        return ID;
    }

    public static String getLABEL() {
        return LABEL;
    }

    public static String getImageSpecialite() {
        return IMAGE_SPECIALITE;
    }

    public static String getCreateTable() {
        return CREATE_TABLE;
    }
}
