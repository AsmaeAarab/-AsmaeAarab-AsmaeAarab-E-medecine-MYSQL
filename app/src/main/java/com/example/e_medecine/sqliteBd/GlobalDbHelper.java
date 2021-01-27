package com.example.e_medecine.sqliteBd;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import com.example.e_medecine.R;
        import com.example.e_medecine.model.Medecin;
        import com.example.e_medecine.model.Rendezvous;
        import com.example.e_medecine.model.Specialite;
        import com.example.e_medecine.model.User;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;

        import static android.database.DatabaseUtils.stringForQuery;

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
            insertFromFile(context, R.raw.medecin,db);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    public ArrayList<Medecin> getMedecins(int id){
        Log.d(TAG,"invoke read");
        ArrayList<Medecin> medecinList=new ArrayList<Medecin>();
        String selectQuery="SELECT medecins.idMedecin, specialites.label,medecins.localisationMedecin,users.imageUser,medecins.frais, medecins.experience" +
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
                byte[] image = cursor.getBlob(cursor.getColumnIndex(UserTable.getIMAGE()));
                medecin.setImageMedecin(image);
                medecin.setFrais(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MedecinTable.getFRAIS()))));
                medecin.setExperience(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MedecinTable.getEXPERIENCE()))));
                medecin.setNomMedecin(cursor.getString(cursor.getColumnIndex(UserTable.getNOM())));
                medecin.setPrenomMedecin(cursor.getString(cursor.getColumnIndex(UserTable.getPRENOM())));
                medecinList.add(medecin);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return medecinList;
    }
    //get phone number
    public String getMedecinTele(int id){
        Log.d(TAG,"invoke read");
        SQLiteDatabase db=this.getReadableDatabase();
        String tele= stringForQuery(db, "SELECT telephoneUser FROM "+UserTable.getTableName()+" WHERE idUser = "+id, null);
        return tele;
    }
    //get location
    public String getMedecinLocation(int id){
        Log.d(TAG,"invoke read");
        SQLiteDatabase db=this.getReadableDatabase();
        String location= stringForQuery(db, "SELECT localisationMedecin FROM "+MedecinTable.getTableName()+" WHERE idUser = "+id, null);
        return location;
    }
    public String getMedecinImage(int id){
        Log.d(TAG,"invoke read");
        SQLiteDatabase db=this.getReadableDatabase();
        String image= stringForQuery(db, "SELECT imageUser FROM "+UserTable.getTableName()+" WHERE idUser = "+id, null);
        return image;
    }

    ///////////////////////////////////////////////////////////////////////User
    public void addUsers(User user) {
        Log.d(TAG,"invoke insert");
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(UserTable.getID(), user.getIdUser());
        values.put(UserTable.getNOM(), user.getNomUser());
        values.put(UserTable.getPRENOM(), user.getPrenomUser());
        values.put(UserTable.getGENRE(), user.getGenre());
        values.put(UserTable.getTELE(), user.getTele());
        values.put(UserTable.getIMAGE(), user.getImageUser());
        values.put(UserTable.getVILLE(), user.getIdVille());
        values.put(UserTable.getEMAIL(), user.getEmail());
        values.put(UserTable.getPASSWORD(), user.getPassword());
        values.put(UserTable.getROLE(), user.getRole());
        db.insert(UserTable.getTableName(),null,values);
        db.close();
    }
    ///delete records
    public  void deleteUsers(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM " + UserTable.getTableName());
        db.execSQL("DELETE FROM sqlite_sequence WHERE name= '"+UserTable.getTableName()+"'");
        db.close();
    }

    /////////////////////////////////////////////////////////////////Rendez-vous
    public void addRendezVous(String titre, String date, int idPatient, int idMedecin) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(RDVTable.getTITRE(), titre);
        values.put(RDVTable.getDATE(), date);
        values.put(RDVTable.getIdPatient(), idPatient);
        values.put(RDVTable.getID_Medecin(), idMedecin);
        db.insert(RDVTable.getTableName(),null,values);
        db.close();
    }
}


