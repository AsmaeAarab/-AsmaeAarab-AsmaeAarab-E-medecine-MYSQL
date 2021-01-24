package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.e_medecine.activity.MedecinActivity;
import com.example.e_medecine.activity.SpecialitesActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PatientLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.signUp)
    void signup(){
        Intent intent = new Intent(this, PatientSignupActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.singIn)
    void signin(){
        Intent intent = new Intent(this, SpecialitesActivity.class);
        startActivity(intent);
    }

}