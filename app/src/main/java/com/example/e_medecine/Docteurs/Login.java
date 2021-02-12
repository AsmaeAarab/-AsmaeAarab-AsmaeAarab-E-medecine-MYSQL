package com.example.e_medecine.Docteurs;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_medecine.ApiRest.Apis;
import com.example.e_medecine.ApiRest.MedecinService;
import com.example.e_medecine.model.User;
import com.example.e_medecine.R;
import com.example.e_medecine.model.Users;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;
import java.net.URL;
import java.util.List;


import org.springframework.http.HttpEntity;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private GlobalDbHelper globalDbHelper;
    private EditText login ;
    private EditText password ;
    private TextView createcompte,txt;
    private Button signin ;
    private GlobalDbHelper db;
    private String Docteur = "";
    private String log = "";
    private String pass = "";
    private String loginPhone = "";
    MedecinService medecinService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getActionBar().hide();
        Bundle ex = getIntent().getExtras();
        Docteur = new String(ex.getString("Medecin"));
        System.out.println("Medecin" + Docteur);
        initViews();
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        db = new GlobalDbHelper(this);
        RestApi restApi = new RestApi();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log = login.getText().toString();
                pass = password.getText().toString();
                isEmailValid(log,pass,Docteur);
                /*TextView txt = (TextView) findViewById(R.id.userAffich);
                txt.setText("user id: "+FindUserPhone(pass,log,Medecin));*/
               //userTest = restApi.findPhone(log,pass,Medecin);
             //   Toast.makeText(Login.this, "Password: "+ userTest.getPasswordUser(), Toast.LENGTH_SHORT).show();
               //AsyncTask<Void, Void, Users> user = new HttpRequest().execute();

                /*if (db.isEmailvalid(log,pass,Docteur) || db.isTelephonevalid(log,pass,Docteur))
                {
                    login.setText(null);
                    password.setText(null);
                    Intent iacceuil = new Intent(Login.this,Acceuil.class);
                    iacceuil.putExtra("Log",log);
                    startActivity(iacceuil);
                    Toast.makeText(Login.this, "Authentification successful", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login.this, "Login or password Incorrect", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
    String mail="";
    String pss ="";
    String sts ="";
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
                    login.setText(null);
                    password.setText(null);
                    Intent iacc = new Intent(Login.this,Acceuil.class);
                    iacc.putExtra("Login",mail);
                    startActivity(iacc);
                    Toast.makeText(Login.this, "Authentification successful", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login.this, "Login or password Incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(Login.this, "Authentification Impossible", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }
    public int findidPhone(String Phone)
    {

        medecinService = Apis.getMedecinService();
        Call<List<Users>> call = medecinService.getIdUser(Phone);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                System.out.println("Je suis la");
                List<Users> users = response.body();
                for (Users users1: users){
                    //users1.getIdUser();

                    txt.setText(users1.getIdUser());
                    Toast.makeText(Login.this, "Succes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(Login.this, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });
        return 0;
    }
    public void initViews()
    {
        login = (EditText) findViewById(R.id.emaillog);
        password = (EditText) findViewById(R.id.password);
        createcompte = (TextView) findViewById(R.id.compte);
        signin = (Button) findViewById(R.id.connect);
        txt = (TextView) findViewById(R.id.userAffich);
    }
    public void count(View v)
    {
        Intent i1 = new Intent(Login.this, Inscription.class);
        startActivity(i1);
    }
    public void changepassword(View v)
    {
        Intent ic = new Intent(Login.this,Updateaccount.class);
        ic.putExtra("Doc",Docteur);
        startActivity(ic);
    }
}