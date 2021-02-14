package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_medecine.ApiRest.Apis;
import com.example.e_medecine.ApiRest.PatientService;
import com.example.e_medecine.Docteurs.Updateaccount;
import com.example.e_medecine.model.Users;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientForgetPswdActivity extends AppCompatActivity {
    GlobalDbHelper db;
    @BindView(R.id.Newpmdp)
    EditText Newpmdp;

    @BindView(R.id.confirmNewpmdp)
    EditText confirmNewpmdp;

    @BindView(R.id.email)
    EditText emailPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_forget_pswd);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.reset_pswd)
    public void resetPswd(){
        db = new GlobalDbHelper(this);
        Intent intent = new Intent(this, PatientLoginActivity.class);
        String email=emailPatient.getText().toString();
        String mdp=Newpmdp.getText().toString();
        String confirmMdp=confirmNewpmdp.getText().toString();
        Boolean checkEmail=db.checkEmailPatient(email);
        Users u=new Users(); ///MySQL
        u.setPasswordUser(mdp);/// MySQL
        if ((!email.equals("")) && (!mdp.equals(""))&& (!confirmMdp.equals(""))) {
            if (checkEmail == false) {
                Toast.makeText(getApplicationContext(), "Email does not exist", Toast.LENGTH_SHORT).show();
            } else {
                if (mdp.equals(confirmMdp)) {
                    db.updateusingmail(mdp, email);
                    updatePassword(u,email); ///Mysql
                    Toast.makeText(getApplicationContext(), "your password has been reset successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "mdp and confimMDP not equal", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Fiels are empty",Toast.LENGTH_SHORT).show();
        }
    }

    PatientService service;
    public void updatePassword(Users u, String login){
        Intent intent1 =new Intent(PatientForgetPswdActivity.this,PatientLoginActivity.class);
        service= Apis.getPatientsService();
        Call<Users> call=service.updatePassword(u,login);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Update No", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_SHORT).show();
               // startActivity(intent1);
               // finish();
                Log.e("Error:",t.getMessage());
            }
        });
    }
}