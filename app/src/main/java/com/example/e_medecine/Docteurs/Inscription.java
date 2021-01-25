package com.example.e_medecine.Docteurs;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.e_medecine.R;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

public class Inscription extends AppCompatActivity  {
    private EditText nom, prenom, email, password, phone;
    private String nomDoc,prenomDoc,emailDoc,passwordDoc,phoneDoc,RadioMale,RadioFemale,RadioGlobal;
    private Spinner spinnerR;
    private RadioButton RadM,RadF;
    private GlobalDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        initViews();
    }

    private void initViews() {
        nom = findViewById(R.id.DocteurNom);
        prenom = findViewById(R.id.DPrenom);
        email = findViewById(R.id.DEmail);
        password = findViewById(R.id.DPassword);
        phone = findViewById(R.id.DPhone);
        RadM = findViewById(R.id.GenderMale);
        RadF = findViewById(R.id.GenderFemale);
    }
    public void NextInscription(View v)
    {
        Intent i2 = new Intent(Inscription.this, InscriptionSuite.class);
        nomDoc = nom.getText().toString();
        prenomDoc = prenom.getText().toString();
        emailDoc = email.getText().toString();
        passwordDoc = password.getText().toString();
        phoneDoc = phone.getText().toString();
        RadioMale = RadM.getText().toString();
        RadioFemale = RadF.getText().toString();
        if (RadM.isChecked())
        {
            if (nomDoc.length()>1 && prenomDoc.length()>1 && emailDoc.length()>1 && passwordDoc.length()>1 && phoneDoc.length()>1)
            {
                RadioGlobal = RadioMale;
                i2.putExtra("NomD",nomDoc);
                i2.putExtra("PrenomD",prenomDoc);
                i2.putExtra("EmailD",emailDoc);
                i2.putExtra("Pass",passwordDoc);
                i2.putExtra("Phone",phoneDoc);
                i2.putExtra("Radio",RadioGlobal);
                startActivity(i2);
            }else {
                Toast.makeText(this, "Veuillez remplir tous le champs s'il vous plait", Toast.LENGTH_LONG).show();
            }
        }else if (RadF.isChecked())
        {
            if (nomDoc.length()>1 && prenomDoc.length()>1 && emailDoc.length()>1 && passwordDoc.length()>1 && phoneDoc.length()>1)
            {
                RadioGlobal = RadioFemale;
                i2.putExtra("NomD",nomDoc);
                i2.putExtra("PrenomD",prenomDoc);
                i2.putExtra("EmailD",emailDoc);
                i2.putExtra("Pass",passwordDoc);
                i2.putExtra("Phone",phoneDoc);
                i2.putExtra("Radio",RadioGlobal);
                startActivity(i2);
            }else {
                Toast.makeText(this, "Veuillez remplir tous le champs s'il vous plait", Toast.LENGTH_LONG).show();
            }
        }
    }
}