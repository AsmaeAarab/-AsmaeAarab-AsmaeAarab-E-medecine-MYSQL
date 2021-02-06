package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_medecine.Docteurs.Login;
import com.example.e_medecine.model.User;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import java.io.ByteArrayOutputStream;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    GlobalDbHelper bd = new GlobalDbHelper(this);
    private String Docteur = "Docteur";
    private String Patient = "Patient";
    private Button btDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btDoc = (Button) findViewById(R.id.doctor);
        btDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                intent.putExtra("Docteur",Docteur);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        GlobalDbHelper mydb = new GlobalDbHelper(this);
        SQLiteDatabase db = mydb.getWritableDatabase();
    }

    @OnClick(R.id.patient)
    public void loginPatient() {
        Intent intent = new Intent(this, PatientLoginActivity.class);
        intent.putExtra("Patient",Patient);
        startActivity(intent);
    }
    /*@OnClick(R.id.doctor)
    public void loginDocteur() {
        Intent intent = new Intent(MainActivity.this, Login.class);
        intent.putExtra("Docteur",Docteur);
        startActivity(intent);
    }*/

}