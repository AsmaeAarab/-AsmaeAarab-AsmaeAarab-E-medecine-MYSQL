package com.example.e_medecine.Docteurs;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_medecine.R;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

public class Login extends AppCompatActivity {

    private GlobalDbHelper globalDbHelper;
    private EditText login ;
    private EditText password ;
    private TextView createcompte;
    private Button signin ;
    private GlobalDbHelper db;
    private String Docteur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getActionBar().hide();
        Bundle ex = getIntent().getExtras();
        Docteur = new String(ex.getString("Docteur"));

        initViews();
        db = new GlobalDbHelper(this);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String log = login.getText().toString();
                String pass = password.getText().toString();
                if (db.isEmailvalid(log,pass,Docteur) || db.isTelephonevalid(log,pass,Docteur))
                {
                    login.setText(null);
                    password.setText(null);
                    Intent iacceuil = new Intent(Login.this,Acceuil.class);
                    iacceuil.putExtra("Log",log);
                    startActivity(iacceuil);
                    Toast.makeText(Login.this, "Authentification Réussie", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login.this, "Login ou Mot de passe Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
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