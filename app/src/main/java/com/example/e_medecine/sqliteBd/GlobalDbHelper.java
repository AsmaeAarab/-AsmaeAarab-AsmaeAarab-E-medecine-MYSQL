package com.example.e_medecine.sqliteBd;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import com.example.e_medecine.R;
        import com.example.e_medecine.model.Medecin;
        import com.example.e_medecine.model.Specialite;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;

public class GlobalDbHelper extends SQLiteOpenHelper {
    public Context context;
    public static final int Db_Version = 14;
    public static final String TAG = "Database Emedecine";
    public static final String Db_Name = "E_medecine.db";


    public GlobalDbHelper(Context context) {
        super(context, Db_Name, null, Db_Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(VilleTable.CreateTable());
        db.execSQL(SpecialiteTable.CREATE_TABLE());
        db.execSQL(UserTable.CREATE_TABLE());
        db.execSQL(PatientTable.CREATE_TABLE());
        db.execSQL(MedecinTable.CREATE_TABLE());
        db.execSQL(RDVTable.CREATE_TABLE());
        db.execSQL(ConsultationTable.CreateTable());
        db.execSQL(PaiementTable.CREATE_TABLE());
        insertSpecialites(db);

        try {
            insertFromFile(context, R.raw.villes,db);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            insertFromFile(context, R.raw.users,db);
        } catch (IOException e) {
            e.printStackTrace();
        }
        insertMedecins(db);
        Log.d(TAG, "database created" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+RDVTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+ConsultationTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+PaiementTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+PatientTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+MedecinTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+UserTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+VilleTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+SpecialiteTable.getTableName());
            onCreate(db);
        }
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static int insertFromFile(Context context, int resourceId, SQLiteDatabase db) throws IOException {
        // Reseting Counter
        int result = 0;

        // Open the resource
        InputStream insertsStream = context.getResources().openRawResource(resourceId);
        BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

        // Iterate through lines (assuming each insert has its own line and theres no other stuff)
        while (insertReader.ready()) {
            String insertStmt = insertReader.readLine();
            db.execSQL(insertStmt);
            result++;
        }
        insertReader.close();
        // returning number of inserted rows
        return result;
    }

    public ArrayList<String> getAllVilles() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            list.add(0,"Choose a city");
            String selectQuery = "SELECT * FROM " + VilleTable.getTableName();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String ville = cursor.getString(cursor.getColumnIndex("label"));
                    list.add(ville);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){e.printStackTrace();}
        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Specialities
    public ArrayList<Specialite> getSpecialites(){
        Log.d(TAG,"invoke read");
        ArrayList<Specialite> specialiteList=new ArrayList<Specialite>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(
                SpecialiteTable.getTableName(),
                null,
                null,
                null,
                null,
                null,
                null
        );
        if(cursor.moveToNext()){
            do{
                Specialite specialite=new Specialite();
                specialite.setId_specialite(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SpecialiteTable.getID()))));
                specialite.setLabe(cursor.getString(cursor.getColumnIndex(SpecialiteTable.getLABEL())));
                specialite.setImageSpecialite(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SpecialiteTable.getImageSpecialite()))));
                specialiteList.add(specialite);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return specialiteList;
    }
    public void insertSpecialites(SQLiteDatabase db){
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getID()+","+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES(1, \"Allergologie\", "+R.drawable.allergic+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getID()+","+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES(2, \"Cardiologie\", "+R.drawable.cardiologie+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getID()+","+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES(3, \"Dermatologie\", "+R.drawable.dermatologie+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getID()+","+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES(4, \"Gastro-entérologie\", "+R.drawable.stomach+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getID()+","+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES(5, \"Ophtalmologie\", "+R.drawable.ophtalmologie+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getID()+","+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES(6, \"Hématologie\", "+R.drawable.hematology+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getID()+","+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES(7, \"Hépatologie\", "+R.drawable.hepatology+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getID()+","+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES(8, \"Infectiologie\", "+R.drawable.stop+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getID()+","+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES(9, \"Neurologie\", "+R.drawable.neurologie+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getID()+","+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES(10, \"Pédiatrie\", "+R.drawable.pediatry+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getID()+","+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES(11, \"Psychiatrie\", "+R.drawable.psychiatrie+")");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////Medecin

    public void insertMedecins(SQLiteDatabase db){
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(1,1,1,\"doctor\",\"190 Rue Mostafa El Maani, Rdc, Centre Ville, 20250, Casablanca\", "+R.drawable.lahlou+", 250,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(2,2,1,\"doctor\",\"Bd Akid El Allam. Imm 89 1er Etg N°2. Bournasel. Casablanca\", "+R.drawable.hachimi+", 250,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(3,3,2,\"doctor\",\"+600, avenue du Cdt Driss Lharti - ex A 1°ét. Ben Msik, Q. Sbata casablanca\", "+R.drawable.doctor+", 300,14)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(4,4,2,\"doctor\",\"1er etage, Residence Ryad Garden , Bd laymoun,Lt Yousra Rue 7,Oulfa.Immeuble N 2-Appart 3, Casablanca \", "+R.drawable.doctor+", 250,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(5,5,3,\"doctor\",\"Résidence Ryad Soundouss 2 apt 5 angle avenue Annakhil et rue Arromane Hay، ryad، Rabat\", "+R.drawable.doctor+", 250,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(6,6,3,\"doctor\",\"76, Bd Abdelmoumen, Residence La Koutoubia. 5 Eme Étage, Casablanca\", "+R.drawable.doctor+", 250,10)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(7,7,4,\"doctor\",\"Sidi Maarouf Residence el Moustakbal gh 19 imm157 n4 rdc، Avenue Abou Bakr el Kadiri, Casablanca\", "+R.drawable.doctor+", 250,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(8,8,4,\"doctor\",\" 432 Boulevard Commandant Driss Al Harti, Casablanca\", "+R.drawable.doctor+", 300,2)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(9,9,5,\"doctor\",\"511 Boulevard Al Qods, Ain Chock Californie Mandarona، En face de Renault Dacia، Casablanca\", "+R.drawable.doctor+", 250,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(10,10,5,\"doctor\",\"18 Avenue Stendhal, Casablanca 2000\", "+R.drawable.doctor+", 250,5)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(11,11,6,\"doctor\",\"IMM RIF 68 BIS,APP 7, 2 ème étage Angle Fal Ould Oumeir et، Avenue de France, Rabat\", "+R.drawable.doctor+", 250,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(12,12,6,\"doctor\",\"74 Rue Montaigne Quartier، Casablanca\", "+R.drawable.doctor+", 250,9)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(13,13,7,\"doctor\",\"Centre ville, près de Bank Al Maghrib, 4 Rue Driss Lahrizi, Casablanca\", "+R.drawable.doctor+", 250,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(14,14,7,\"doctor\",\"BD ABDERRAHMAN SERGHINI IMM BOUARGANE 1er ÉTAGE, Mohammédia\", "+R.drawable.doctor+", 250,5)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(15,15,8,\"doctor\",\"Résidence El Jawahir, 86 Bd Moulay Driss 1er، Casablanca\", "+R.drawable.doctor+", 250,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(16,16,8,\"doctor\",\" Fauchon، 5 Rue Oulad Bouzid، Rue 34, Casablanca\", "+R.drawable.doctor+", 250,8)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(17,17,9,\"doctor\",\"Avenue Abou Bakr el Kadiri, Casablanca\", "+R.drawable.doctor+", 250,3)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(18,18,9,\"doctor\",\"BD Mohamed V Rés Dos Mares N°46 étage 4، Tangier\", "+R.drawable.doctor+", 250,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(19,19,10,\"doctor\",\"249, bd Yacoub El Mansour, résid. El Mansour, 2°ét. appt. n°3, Q، Casablanca\", "+R.drawable.doctor+", 300,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(20,20,10,\"doctor\",\" 3 rue Daraa -ex Danvillers Centre Ville, Casablanca\", "+R.drawable.doctor+", 300,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(21,21,11,\"doctor\",\"75 Rue Abou Alâa Zahr, Casablanca\", "+R.drawable.doctor+", 250,4)");
        db.execSQL("INSERT INTO "+MedecinTable.getTableName()+" ("+MedecinTable.getID()+","+MedecinTable.getIdUser()+","+MedecinTable.getSPECIALITE()+","+MedecinTable.getTYPE()+","+MedecinTable.getLOCALISATION()+","+MedecinTable.getIMAGE_Medecin()+","+MedecinTable.getFRAIS()+","+MedecinTable.getEXPERIENCE()+") VALUES(22,22,11,\"doctor\",\"3ème étage 999 Amal 5 Av Al Massira CYM, Rabat 10000, Maroc، Rabat\", "+R.drawable.doctor+", 250,4)");
    }
    public ArrayList<Medecin> getMedecins(int id){
        Log.d(TAG,"invoke read");
        ArrayList<Medecin> medecinList=new ArrayList<Medecin>();
        String selectQuery="SELECT medecins.idMedecin, specialites.label,medecins.localisationMedecin,medecins.imageMedecin,medecins.frais, medecins.experience" +
                ", users.nomUser, users.prenomUser FROM "+MedecinTable.getTableName()+" INNER JOIN "+UserTable.getTableName()+" ON medecins.idUser=users.idUser "+
                "INNER JOIN "+SpecialiteTable.getTableName()+" ON medecins.idSpecialite=specialites.idSpecialite"+
                " WHERE medecins.idSpecialite = "+id;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToNext()){
            do{
                Medecin medecin=new Medecin();
                medecin.setIdMedecin(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MedecinTable.getID()))));
                medecin.setSpecialite(cursor.getString(cursor.getColumnIndex(SpecialiteTable.getLABEL())));
                medecin.setLocation(cursor.getString(cursor.getColumnIndex(MedecinTable.getLOCALISATION())));
                medecin.setImageMedecin(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MedecinTable.getIMAGE_Medecin()))));
                medecin.setFrais(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MedecinTable.getFRAIS()))));
                medecin.setExperience(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MedecinTable.getEXPERIENCE()))));
                medecin.setNom(cursor.getString(cursor.getColumnIndex(UserTable.getNOM())));
                medecin.setPrenom(cursor.getString(cursor.getColumnIndex(UserTable.getPRENOM())));
                medecinList.add(medecin);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return medecinList;
    }
}


