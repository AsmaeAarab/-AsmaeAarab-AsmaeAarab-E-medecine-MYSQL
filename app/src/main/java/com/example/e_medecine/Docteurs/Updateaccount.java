package com.example.e_medecine.Docteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_medecine.R;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

public class Updateaccount extends AppCompatActivity {
    private EditText ancientlog;
    private EditText newpass;
    private Button update;
    private GlobalDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateaccount);
        initupdate();
        db = new GlobalDbHelper(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anclog = ancientlog.getText().toString();
                String newp = newpass.getText().toString();
                if (anclog.length() > 1 && newp.length() > 1)
                {
                    if (db.isEmailvalid(anclog,newp) || db.isTelephonevalid(anclog,newp))
                    {
                        Toast.makeText(Updateaccount.this, "Votre Nouveau Mot de passe est identique a l'ancien ", Toast.LENGTH_SHORT).show();
                    }else{
                        db.updateusingmail(newp,anclog);
                        db.updateusingphone(newp,anclog);
                        Intent iac = new Intent(Updateaccount.this,Login.class);
                        startActivity(iac);
                        Toast.makeText(Updateaccount.this, "Mot de passe changer avec succes", Toast.LENGTH_SHORT).show();
                    }
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
}