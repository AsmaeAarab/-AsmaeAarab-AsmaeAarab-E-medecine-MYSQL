package com.example.e_medecine.Docteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_medecine.ApiRest.Apis;
import com.example.e_medecine.ApiRest.MedecinService;
import com.example.e_medecine.R;
import com.example.e_medecine.model.Users;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;
import com.firebase.ui.auth.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Updateaccount extends AppCompatActivity {
    private EditText ancientlog;
    private EditText newpass;
    private Button update;
    private GlobalDbHelper db;
    private String anclog = "";
    private String newp = "";
    MedecinService medecinService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateaccount);
        initupdate();
        Bundle extraterrestre = getIntent().getExtras();
        String Doc = new String(extraterrestre.getString("Doc"));
        db = new GlobalDbHelper(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anclog = ancientlog.getText().toString();
                newp = newpass.getText().toString();
                if (anclog.length() > 1 && newp.length() > 1)
                {
                    isEmailValid(anclog,newp,Doc);
                    /*if (db.isEmailvalid(anclog,newp,Doc) || db.isTelephonevalid(anclog,newp,Doc))
                    {
                        Toast.makeText(Updateaccount.this, "Votre Nouveau Mot de passe est identique a l'ancien ", Toast.LENGTH_SHORT).show();
                    }else{
                        db.updateusingmail(newp,anclog);
                        db.updateusingphone(newp,anclog);
                        Intent iac = new Intent(Updateaccount.this,Login.class);
                        startActivity(iac);
                        Toast.makeText(Updateaccount.this, "Mot de passe changer avec succes", Toast.LENGTH_SHORT).show();
                    }*/
                }else {
                    Toast.makeText(Updateaccount.this, "Veuillez Remplir les champs s'il vous plait", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void initupdate()
    {
        ancientlog = (EditText) findViewById(R.id.ancienlog);
        newpass = (EditText) findViewById(R.id.Newpass);
        update = (Button) findViewById(R.id.UpdatePass);
    }
    String mail="";
    String pss ="";
    String sts ="";
    Users ux = new Users();
    public boolean isEmailValid(String Email,String Password,String Status)
    {
        medecinService = Apis.getMedecinService();
        Call<List<Users>> call = medecinService.isEmailValid(Email,Password,Status);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                List<Users> u = response.body();
                for (Users uvalid: u)
                {
                    mail = uvalid.getEmailUser();
                    pss = uvalid.getPasswordUser();
                    sts = uvalid.getRoleUser();
                }
                if (mail.equals(Email)&&pss.equals(Password)&&sts.equals(Status))
                {
                    Toast.makeText(Updateaccount.this, "This Password is identical to the ancient ", Toast.LENGTH_SHORT).show();
                }else {
                    ux.setPasswordUser(newp);
                    updateMedecinPassword(ux,anclog);
                    Toast.makeText(Updateaccount.this, "Good Your Password has been set", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(Updateaccount.this, "Authentification Impossible", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }
    public void updateMedecinPassword(Users u,String Mail)
    {
        medecinService = Apis.getMedecinService();
        Call<Users> call = medecinService.UpdateMedecin(u,Mail);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(Updateaccount.this, "Your Password has bean Updated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(Updateaccount.this, "Failure please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}