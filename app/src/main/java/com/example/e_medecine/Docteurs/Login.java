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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private GlobalDbHelper globalDbHelper;
    private EditText login ;
    private EditText password ;
    private TextView createcompte;
    private Button signin ;
    private GlobalDbHelper db;
    private String Docteur = "";
    private String log = "";
    private String pass = "";
    Users userTest = null;
    MedecinService medecinService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getActionBar().hide();
        Bundle ex = getIntent().getExtras();
        Docteur = new String(ex.getString("Docteur"));
        System.out.println("Docteur" + Docteur);
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
                /*TextView txt = (TextView) findViewById(R.id.userAffich);
                txt.setText("user id: "+FindUserPhone(pass,log,Docteur));*/
               //userTest = restApi.findPhone(log,pass,Docteur);
             //   Toast.makeText(Login.this, "Password: "+ userTest.getPasswordUser(), Toast.LENGTH_SHORT).show();
               AsyncTask<Void, Void, Users> user = new HttpRequest().execute();
               /*if (userM == true)
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
               /* if (db.isEmailvalid(log,pass,Docteur) || db.isTelephonevalid(log,pass,Docteur))
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
    public void FindUserPhone(String Password,String Phone,String Docteur){
        medecinService = Apis.getMedecinService();
        Call<Users> call = medecinService.FinduserbyPhone(Password,Phone,Docteur);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Toast.makeText(getApplicationContext(), "yesFound", Toast.LENGTH_SHORT).show();
                Toast.makeText(Login.this,"Found",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "NoADD ", Toast.LENGTH_SHORT).show();
                Log.e("Error:",t.getMessage());
            }
        });
        /*Intent intent = new Intent(this, PatientLoginActivity.class);
        startActivity(intent);
        finish();*/
    }
    public class HttpRequest extends AsyncTask<Void,Void,Users>
    {

        @Override
        protected Users doInBackground(Void... voids) {
            RestApi restApi = new RestApi();
            userTest = restApi.findPhoneID(log);
            return userTest;
        }

        @Override
        protected void onPostExecute(Users user) {
            TextView txt = (TextView) findViewById(R.id.userAffich);
            txt.setText("user id: "+userTest.getIdUser());
        }

    }


    public void initViews()
    {
        login = (EditText) findViewById(R.id.emaillog);
        password = (EditText) findViewById(R.id.password);
        createcompte = (TextView) findViewById(R.id.compte);
        signin = (Button) findViewById(R.id.connect);
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