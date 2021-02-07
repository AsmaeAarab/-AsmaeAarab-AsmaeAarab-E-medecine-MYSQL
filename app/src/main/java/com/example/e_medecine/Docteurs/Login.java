package com.example.e_medecine.Docteurs;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.example.e_medecine.R;
import com.example.e_medecine.model.User;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

public class Login extends AppCompatActivity {

    private GlobalDbHelper globalDbHelper;
    private EditText login ;
    private EditText password ;
    private TextView createcompte;
    private Button signin ;
    private GlobalDbHelper db;
    private String Docteur,log,pass;
    User userTest = null;
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

               // userTest = restApi.findPhone("0522277997","123");
             //   Toast.makeText(Login.this, "Password: "+ userTest.getPasswordUser(), Toast.LENGTH_SHORT).show();
               AsyncTask<Void, Void, User> user = new HttpRequest().execute();

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
   public class HttpRequest extends AsyncTask<Void,Void,User>
    {

        @Override
        protected User doInBackground(Void... voids) {
            RestApi restApi = new RestApi();
            userTest = restApi.findPhone("0522277997","123","Docteur");
            return restApi.findPhone("0522277997","123","Docteur");
        }

        @Override
        protected void onPostExecute(User user) {
            //super.onPostExecute(user);
          //  Toast.makeText(Login.this,"user id: "+userTest.getIdUser(),Toast.LENGTH_LONG).show();
            TextView txt = (TextView) findViewById(R.id.userAffich);
            txt.setText("user id: "+userTest.getIdUser()); // txt.setText(result);
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