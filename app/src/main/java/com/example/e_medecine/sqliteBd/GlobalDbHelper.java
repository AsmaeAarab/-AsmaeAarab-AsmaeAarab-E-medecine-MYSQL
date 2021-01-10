package com.example.e_medecine.sqliteBd;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import com.example.e_medecine.R;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;

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
        try {
            insertFromFile(context, R.raw.villes,db);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            insertFromFile(context, R.raw.specialites,db);
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

}


