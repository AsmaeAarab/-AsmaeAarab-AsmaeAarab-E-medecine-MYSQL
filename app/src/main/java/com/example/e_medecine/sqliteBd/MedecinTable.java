package com.example.e_medecine.sqliteBd;

import android.database.sqlite.SQLiteDatabase;

import com.example.e_medecine.R;

public class MedecinTable {

    private static final String TABLE_NAME = "medecins";
    private static final String ID = "idMedecin";
    private static final String ID_USER = "idUser";
    private static final String SPECIALITE = "idSpecialite";
    private static final String TYPE = "typeMedecin";// e-doctor or simple doctor
    private static final String LOCALISATION = "localisationMedecin";// localisation du medecin dans le cas du non e-medecin
    private static final String IMAGE_Medecin="imageMedecin";
    private static final String FRAIS="frais";
    private static final String EXPERIENCE="experience";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID  +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ID_USER +" INTEGER, "
                    + SPECIALITE + " INTEGER, "
                    + TYPE +" TEXT, "
                    + LOCALISATION +" TEXT, "
                    + IMAGE_Medecin+" INTEGER, "
                    + FRAIS+" INTEGER,"
                    + EXPERIENCE+" INTEGER,"
                    + "FOREIGN KEY(" + ID_USER + ") REFERENCES users(idUser),"
                    + "FOREIGN KEY(" + SPECIALITE + ") REFERENCES specialites(idSpecialite))";

    public static String CREATE_TABLE(){
        return CREATE_TABLE;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getFRAIS() {
        return FRAIS;
    }

    public static String getEXPERIENCE() {
        return EXPERIENCE;
    }

    public static String getID() {
        return ID;
    }

    public static String getIdUser() {
        return ID_USER;
    }

    public static String getSPECIALITE() {
        return SPECIALITE;
    }

    public static String getTYPE() {
        return TYPE;
    }

    public static String getLOCALISATION() {
        return LOCALISATION;
    }

    public static String getIMAGE_Medecin() {
        return IMAGE_Medecin;
    }

    public static String getCreateTable() {
        return CREATE_TABLE;
    }

    public void insertMedecin(SQLiteDatabase db){
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(1,1,1,\"doctor\",\"190 Rue Mostafa El Maani, Rdc, Centre Ville, 20250, Casablanca\", "+ R.drawable.lahlou+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(2,2,1,\"doctor\",\"Bd Akid El Allam. Imm 89 1er Etg N°2. Bournasel. Casablanca\", "+R.drawable.hachimi+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(3,3,2,\"doctor\",\"+600, avenue du Cdt Driss Lharti - ex A 1°ét. Ben Msik, Q. Sbata casablanca\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(4,4,2,\"doctor\",\"1er etage, Residence Ryad Garden , Bd laymoun,Lt Yousra Rue 7,Oulfa.Immeuble N 2-Appart 3, Casablanca \", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(5,5,3,\"doctor\",\"Résidence Ryad Soundouss 2 apt 5 angle avenue Annakhil et rue Arromane Hay، ryad، Rabat\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(6,6,3,\"doctor\",\"76, Bd Abdelmoumen, Residence La Koutoubia. 5 Eme Étage, Casablanca, "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(7,7,4,\"doctor\",\"Sidi Maarouf Residence el Moustakbal gh 19 imm157 n4 rdc، Avenue Abou Bakr el Kadiri, Casablanca\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(8,8,4,\"doctor\",\" 432 Boulevard Commandant Driss Al Harti, Casablanca\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(9,9,5,\"doctor\",\"511 Boulevard Al Qods, Ain Chock Californie Mandarona، En face de Renault Dacia، Casablanca\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(10,10,5,\"doctor\",\"18 Avenue Stendhal, Casablanca 2000\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(11,11,6,\"doctor\",\"IMM RIF 68 BIS,APP 7, 2 ème étage Angle Fal Ould Oumeir et، Avenue de France, Rabat\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(12,12,6,\"doctor\",\"74 Rue Montaigne Quartier، Casablanca\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(13,13,7,\"doctor\",\"Centre ville, près de Bank Al Maghrib, 4 Rue Driss Lahrizi, Casablanca\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(14,14,7,\"doctor\",\"BD ABDERRAHMAN SERGHINI IMM BOUARGANE 1er ÉTAGE, Mohammédia\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(15,15,8,\"doctor\",\"Résidence El Jawahir, 86 Bd Moulay Driss 1er، Casablanca\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(16,16,8,\"doctor\",\" Fauchon، 5 Rue Oulad Bouzid، Rue 34, Casablanca\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(17,17,9,\"doctor\",\"Avenue Abou Bakr el Kadiri, Casablanca\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(18,18,9,\"doctor\",\"BD Mohamed V Rés Dos Mares N°46 étage 4، Tangier\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(19,19,10,\"doctor\",\"249, bd Yacoub El Mansour, résid. El Mansour, 2°ét. appt. n°3, Q، Casablanca\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(20,20,10,\"doctor\",\" 3 rue Daraa -ex Danvillers Centre Ville, Casablanca\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(21,21,11,\"doctor\",\"75 Rue Abou Alâa Zahr, Casablanca\", "+R.drawable.doctor+")");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+") VALUES(22,22,11,\"doctor\",\"3ème étage 999 Amal 5 Av Al Massira CYM, Rabat 10000, Maroc، Rabat\", "+R.drawable.doctor+")");
    }
}
