package com.example.e_medecine.sqliteBd;

public class UserTable {
    private static final String TABLE_NAME = "users";

    private static final String ID = "idUser";
    private static final String NOM = "nomUser";
    private static final String PRENOM = "prenomUser";
    private static final String GENRE = "genreUser";
    private static final String TELE = "telephoneUser";
    private static final String IMAGE = "imageUser";
    private static final String VILLE = "idVille";

    private static final String EMAIL = "emailUser"; //login
    private static final String PASSWORD = "passwordUser";
    private static final String ROLE = "roleUser"; // medecin ou patient

    public static String getGENRE() {
        return GENRE;
    }

    public static String getTELE() {
        return TELE;
    }

    public static String getVILLE() {
        return VILLE;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getROLE() {
        return ROLE;
    }

    public static String getCreateTable() {
        return CREATE_TABLE;
    }

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID  +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NOM +" TEXT, "
                    + PRENOM +" TEXT, "
                    + GENRE +" TEXT, "
                    + TELE +" TEXT, "
                    + IMAGE +" BLOB, "
                    + VILLE +" INTEGER, "
                    + EMAIL +" TEXT, "
                    + PASSWORD +" TEXT, "
                    + ROLE +" TEXT, "
                    + "FOREIGN KEY(" + VILLE + ") REFERENCES villes(idVille))";

    public static String CREATE_TABLE(){
        return CREATE_TABLE;
    }
    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getID() {
        return ID;
    }

    public static String getNOM() {
        return NOM;
    }

    public static String getPRENOM() {
        return PRENOM;
    }

    public static String getIMAGE() {
        return IMAGE;
    }
}
