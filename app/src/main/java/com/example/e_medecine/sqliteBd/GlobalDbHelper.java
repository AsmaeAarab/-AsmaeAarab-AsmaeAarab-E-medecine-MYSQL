package com.example.e_medecine.sqliteBd;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import com.example.e_medecine.R;
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
        insertSpecialite(db);
        try {
            insertFromFile(context, R.raw.villes,db);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //try {
            //insertFromFile(context, R.raw.specialites,db);

        //} catch (IOException e) {
          //  e.printStackTrace();
        //}
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
    public void insertSpecialite(SQLiteDatabase db){
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

    // Specialities
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

}


