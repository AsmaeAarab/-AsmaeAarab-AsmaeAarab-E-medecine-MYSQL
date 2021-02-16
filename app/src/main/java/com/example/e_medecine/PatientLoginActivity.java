package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_medecine.ApiRest.Apis;
import com.example.e_medecine.ApiRest.PatientService;
import com.example.e_medecine.Docteurs.Acceuil;
import com.example.e_medecine.Docteurs.Login;
import com.example.e_medecine.model.Users;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import butterknife.BindView;
import com.example.e_medecine.activity.SpecialitesActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @OnClick(R.id.forgetPswd)
    void forgetPswd(){
        Intent intent = new Intent(this, PatientForgetPswdActivity.class);
        startActivity(intent);
    }

    PatientService service;
    int idX;
    String email1;
    String password1;
    SharedPreferences sharedPreferences;
    public void loginPatient(String emailUser,String passwordUser){
        service= Apis.getPatientsService();
        Call<List<Users>> call = service.loginPatient(emailUser,passwordUser);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>>call, Response<List<Users>> response) {
                List<Users>uList=response.body();
                for(Users u : uList ){
                    idX=u.getIdUser();
                    email1=u.getEmailUser();
                    password1=u.getPasswordUser();
                }
                if(idX!=0)
                {
                    Intent intent1 = new Intent(PatientLoginActivity.this, PatientAccueilActivity.class);
                    intent1.putExtra("EmailUser",login);
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString("pref_name", login);
                    editor.apply();
                    startActivity(intent1);
                    Toast.makeText(getApplicationContext(), "Authentification avec success!!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Login or password incorect!!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ERROR ", Toast.LENGTH_SHORT).show();
                Log.e("Error:",t.getMessage());
            }
        });
        //return true;
    }

    private String login;
    @OnClick(R.id.signIn)
    void signIn(){
        Intent intent = new Intent(this, PatientAccueilActivity.class);
         login = editTextLogin.getText().toString();
        String password = editTextPassword.getText().toString();

        /////////////MYSQL

        if ((!login.equals("")) && (!password.equals(""))) {
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
            loginPatient(login,password);
        }else{ Toast.makeText(getApplicationContext(),"Fiels are empty!",Toast.LENGTH_SHORT).show();}

        /////////////FIN MYSQL
     /*   db = new GlobalDbHelper(this);

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
                intent.putExtra("EmailUser",login);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Wrong login or password", Toast.LENGTH_SHORT).show();
            }
        }
        else{ Toast.makeText(getApplicationContext(),"Fiels are empty",Toast.LENGTH_SHORT).show();}
        // editTextLogin.getText().clear();
        // editTextPassword.getText().clear();*/ ////////SQLITE

    }
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