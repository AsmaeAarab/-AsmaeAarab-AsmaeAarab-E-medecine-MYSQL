package com.example.e_medecine.sqliteBd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class VilleTable {
    private static final String TABLE_NAME = "villes";
    private static final String ID = "idVille";
    private static final String LABEL = "label";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID  +" INTEGER PRIMARY KEY, "
                    + LABEL +" TEXT)";

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String CreateTable(){
        return CREATE_TABLE;
    }

}
