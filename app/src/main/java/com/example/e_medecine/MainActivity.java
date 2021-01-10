package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.e_medecine.sqliteBd.GlobalDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        GlobalDbHelper mydb = new GlobalDbHelper(this);
        SQLiteDatabase db = mydb.getWritableDatabase();
    }
}