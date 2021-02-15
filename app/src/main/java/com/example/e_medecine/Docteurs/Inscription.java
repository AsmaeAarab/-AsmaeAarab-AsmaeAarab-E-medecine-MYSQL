package com.example.e_medecine.Docteurs;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.e_medecine.R;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import java.util.regex.Pattern;

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
            if (nomDoc.length()>1 && prenomDoc.length()>1 && Patterns.EMAIL_ADDRESS.matcher(emailDoc).matches() && PASSWORD_PATTERN.matcher(passwordDoc).matches() && phoneDoc.length()>1)
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
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Please verify that your mail and password (at least 4 Caractere) are in correct form", Toast.LENGTH_SHORT).show();
            }
        }else if (RadF.isChecked())
        {
            if (nomDoc.length()>1 && prenomDoc.length()>1 && Patterns.EMAIL_ADDRESS.matcher(emailDoc).matches() && PASSWORD_PATTERN.matcher(passwordDoc).matches() && phoneDoc.length()>1)
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
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Please verify that your mail and password (at least 4 Caractere) are in correct form", Toast.LENGTH_SHORT).show();
            }
        }
        nom.setText(null);
        prenom.setText(null);
        email.setText(null);
        password.setText(null);
        phone.setText(null);
    }
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    // "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    public void BackInscription(View v)
    {
        finish();
    }
}