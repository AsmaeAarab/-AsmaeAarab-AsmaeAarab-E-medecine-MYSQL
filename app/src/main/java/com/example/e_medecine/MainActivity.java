package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.e_medecine.Docteurs.Login;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private String Docteur = "Docteur";
    private String Patient = "Patient";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
    @OnClick(R.id.doctor)
    public void loginDocteur() {
        Intent intent = new Intent(this, Login.class);
        intent.putExtra("Docteur",Docteur);
        startActivity(intent);
    }



}