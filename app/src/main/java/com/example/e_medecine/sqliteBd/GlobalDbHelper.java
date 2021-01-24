package com.example.e_medecine.sqliteBd;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import com.example.e_medecine.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
    public void insertdocteur(ContentValues values)
    {
        getWritableDatabase().insert("medecins",null,values);
    }
    public void insertuser(ContentValues values)
    {
        getWritableDatabase().insert("users",null,values);
    }
    public void insertville(ContentValues values)
    {
        getWritableDatabase().insert("villes",null,values);
    }
    public void insertspecialite(ContentValues values)
    {
        getWritableDatabase().insert("specialites",null,values);
    }
    public boolean isEmailvalid(String email , String password)
    {
        String sql = "select count(*) from users where emailUser='"+email+"' and passwordUser='"+password+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        Long l = statement.simpleQueryForLong();
        statement.close();
        if (l==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public boolean updateusingmail(String pass,String login)
    {
      SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET passwordUser = "+"'"+pass+"' "+ "WHERE emailUser = "+"'"+login+"'");
        return true;
    }
    public boolean updateusingphone(String pass,String login)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET passwordUser = "+"'"+pass+"' "+ "WHERE telephoneUser = "+"'"+login+"'");
        return true;
    }
    public boolean updatenom(String nom,int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET nomUser = "+" '" +nom+ "' "+" WHERE idUser = "+" '"+id+"'  ");
        return true;
    }
    public boolean updateprenom(String prenom,int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET prenomUser = "+" '" +prenom+ "' "+" WHERE idUser = "+" '"+id+"'  ");
        return true;
    }
    public boolean updatemail(String mail,int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET emailUser = "+" '" +mail+ "' "+" WHERE idUser = "+" '"+id+"'  ");
        return true;
    }
    public boolean updatephone(String phone,int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET telephoneUser = "+" '" +phone+ "' "+" WHERE idUser = "+" '"+id+"'  ");
        return true;
    }
    public boolean updateCalendrier(String dateP,int idp,int idm)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE RDVs SET dateRDV = "+" '"+dateP+"' "+" WHERE idPatient = "+" '"+idp+"' "+" AND idMedecin = "+" '"+idm+"' "+" ");
        return true;
    }
    public boolean updateImage(byte[] image,int id)
    {
        ContentValues values = new ContentValues();
        String i = String.valueOf(id);
        values.put("imageUser",image);
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("UPDATE users SET imageUser = "+" '" +values+ "' "+" WHERE idUser = "+" '"+id+"'  ");
        db.update("users",values,"idUser = ?",new String[]{i});
        return true;
    }
    public boolean isTelephonevalid(String Phone , String password)
    {
        String sql = "select count(*) from users where telephoneUser='"+Phone+"' and passwordUser='"+password+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        Long d = statement.simpleQueryForLong();
        statement.close();
        if (d==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public void querydata(String sql)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor getData(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

}
