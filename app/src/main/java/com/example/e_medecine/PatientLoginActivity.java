package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import butterknife.BindView;
import com.example.e_medecine.activity.SpecialitesActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PatientLoginActivity extends AppCompatActivity {

    @BindView(R.id.login)
    EditText editTextLogin;
    @BindView(R.id.password)
    EditText editTextPassword;
    @BindView(R.id.RemenberMe)
    CheckBox RemenberMe;

    private SharedPreferences mPrefs;
    private  static final String PREFS_NAME="PrefsFile";
    GlobalDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
        ButterKnife.bind(this);
        mPrefs=getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        getPreferencesData();

    }

    @OnClick(R.id.signUp)
    void signup(){
        Intent intent = new Intent(this, PatientSignupActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.signIn)
    void signin(){
        Intent intent = new Intent(this, SpecialitesActivity.class);
        startActivity(intent);
    }

    /*

    @OnClick(R.id.signIn)
    void signIn(){
        db = new GlobalDbHelper(this);
        Intent intent = new Intent(this, SpecialitesActivity.class);
        String login = editTextLogin.getText().toString();
        String password = editTextPassword.getText().toString();
        Boolean checkloginpass = db.loginpassword(login, password);
        if ((!login.equals("")) && (!password.equals(""))) {
            if (checkloginpass == true) {
                Toast.makeText(getApplicationContext(), "Successfully login", Toast.LENGTH_SHORT).show();
                if (RemenberMe.isChecked()) {
                    Boolean boolIsCheckhed = RemenberMe.isChecked();
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString("pref_name", login);
                    editor.putString("pref_pass", password);
                    editor.putBoolean("pref_check", boolIsCheckhed);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Setting have been saved.", Toast.LENGTH_SHORT).show();
                } else {
                    mPrefs.edit().clear().apply();
                }
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Wrong login or password", Toast.LENGTH_SHORT).show();
            }
        }
        else{ Toast.makeText(getApplicationContext(),"Fiels are empty",Toast.LENGTH_SHORT).show();}
        // editTextLogin.getText().clear();
        // editTextPassword.getText().clear();

    }

     */

    private void getPreferencesData(){
        SharedPreferences sp= getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        if(sp.contains("pref_name")){
            String u=sp.getString("pref_name","not found");
            editTextLogin.setText(u.toString());
        }
        if(sp.contains("pref_pass")){
            String p=sp.getString("pref_pass","not found");
            editTextPassword.setText(p.toString());
        }
        if(sp.contains("pref_check")){
            Boolean b=sp.getBoolean("pref_check",false);
            RemenberMe.setChecked(b);
        }
    }




}