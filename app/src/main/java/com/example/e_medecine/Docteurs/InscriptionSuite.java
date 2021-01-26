package com.example.e_medecine.Docteurs;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.e_medecine.R;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class InscriptionSuite extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerV,spinnerS,spinnerE;
    private EditText localisation;
    private ImageView imgpro;
    private RadioButton Condition;
    private Button ajout,choose;
    private String locate,charte,city,specialite,typedoc;
    private boolean click = false;
    private String namedoc,lastnamedoc,maildoc,passwordoc,phonedoc,roledoc,genderdoc;
    private final int REQUEST_CODE_GALLERY = 999;
    private GlobalDbHelper db;
    private SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_suite);
        init();
        db = new GlobalDbHelper(this);
        ArrayList<String> listville = db.getAllVilles();
        ArrayAdapter<String> adapterV = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listville);
        adapterV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerV.setAdapter(adapterV);
        spinnerV.setOnItemSelectedListener(this);
        ArrayList<String> listspecialite = db.getAllSpecialites();
        ArrayAdapter<String> adapterS = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listspecialite);
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerS.setAdapter(adapterS);
        spinnerS.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapterT = ArrayAdapter.createFromResource(this,R.array.TypeDoc, android.R.layout.simple_spinner_item);
        adapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerE.setAdapter(adapterT);
        spinnerE.setOnItemSelectedListener(this);
        Bundle extras = getIntent().getExtras();
        namedoc = new String(extras.getString("NomD"));
        lastnamedoc = new String(extras.getString("PrenomD"));
        genderdoc = new String(extras.getString("Radio"));
        phonedoc = new String(extras.getString("Phone"));
        maildoc = new String(extras.getString("EmailD"));
        passwordoc = new String(extras.getString("Pass"));
        roledoc = "Docteur";
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(InscriptionSuite.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
                click = true;
            }
        });
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locate = localisation.getText().toString();
                charte = Condition.getText().toString();
                if (genderdoc.equals("Homme")) {
                    if (click == true) {
                        byte[] imgprofileval = imageViewToByte(imgpro);
                        if (Condition.isChecked()) {
                            if (locate.length() > 1) {
                                int Idville = db.getIdVille(city);
                                boolean insertuser = db.insertUser(imgprofileval,namedoc,lastnamedoc,genderdoc,phonedoc,Idville,maildoc,passwordoc,roledoc);
                                int iduser = db.getIdUser(maildoc);
                                int IdSpecialite = db.getIdSpecialite(specialite);
                                boolean insertmedecin = db.insertMedecin(iduser,IdSpecialite,typedoc,locate,charte);
                                if (insertuser == true && insertmedecin == true)
                                {
                                    Toast.makeText(InscriptionSuite.this, "Doctor Registration Succeed", Toast.LENGTH_SHORT).show();
                                    Intent ilogin = new Intent(InscriptionSuite.this, Login.class);
                                    startActivity(ilogin);
                                }else {
                                    Toast.makeText(InscriptionSuite.this, "Doctor Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(InscriptionSuite.this, "Please fill the fields", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(InscriptionSuite.this, "Please check the terms and conditions", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(InscriptionSuite.this, "May sir choose a photo for your profil", Toast.LENGTH_SHORT).show();
                    }
                } else if (genderdoc.equals("Femme")) {
                    if (click == true)
                    {
                        byte[] imgprofileval = imageViewToByte(imgpro);
                        if (Condition.isChecked()) {
                            if (locate.length() > 1) {
                                int Idville = db.getIdVille(city);
                                boolean insertuser = db.insertUser(imgprofileval,namedoc,lastnamedoc,genderdoc,phonedoc,Idville,maildoc,passwordoc,roledoc);
                                int iduser = db.getIdUser(maildoc);
                                int IdSpecialite = db.getIdSpecialite(specialite);
                                boolean insertmedecin = db.insertMedecin(iduser,IdSpecialite,typedoc,locate,charte);
                                if (insertuser == true && insertmedecin == true)
                                {
                                    Toast.makeText(InscriptionSuite.this, "Doctor Registration Succeed", Toast.LENGTH_SHORT).show();
                                    Intent ilogin = new Intent(InscriptionSuite.this, Login.class);
                                    startActivity(ilogin);
                                }else {
                                    Toast.makeText(InscriptionSuite.this, "Doctor Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(InscriptionSuite.this, "Please fill the fields", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(InscriptionSuite.this, "Please check the terms and conditions", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(InscriptionSuite.this, "May Mrs choose a photo for her profil", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void back(View v)
    {
        finish();
    }
    public void init()
    {
        spinnerS = findViewById(R.id.DSpecialite);
        spinnerV = findViewById(R.id.DVille);
        spinnerE = findViewById(R.id.DEducation);
        imgpro = findViewById(R.id.imageProfil);
        localisation = findViewById(R.id.DAdresse);
        Condition = findViewById(R.id.DCharte);
        ajout = findViewById(R.id.Sinscrire);
        choose = findViewById(R.id.ChooseProfile);
    }
    private byte[] imageViewToByte(ImageView imgpro) {
        Bitmap bitmap = ((BitmapDrawable)imgpro.getDrawable()).getBitmap();
        Bitmap bitmapreduced = reduceBitmapSize(bitmap,240000);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapreduced.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    public static Bitmap reduceBitmapSize(Bitmap bitmap,int MAX_SIZE) {
        double ratioSquare;
        int bitmapHeight, bitmapWidth;
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();
        ratioSquare = (bitmapHeight * bitmapWidth) / MAX_SIZE;
        if (ratioSquare <= 1)
            return bitmap;
        double ratio = Math.sqrt(ratioSquare);
        Log.d("mylog", "Ratio: " + ratio);
        int requiredHeight = (int) Math.round(bitmapHeight / ratio);
        int requiredWidth = (int) Math.round(bitmapWidth / ratio);
        return Bitmap.createScaledBitmap(bitmap, requiredWidth, requiredHeight, true);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }else {
                Toast.makeText(this, "Vous n'avez pas la permission d'acceder", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgpro.setImageBitmap(bitmap);
            }catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.DVille:
                city = parent.getItemAtPosition(position).toString();
                break;
            case R.id.DSpecialite:
                specialite = parent.getItemAtPosition(position).toString();
                break;
            case R.id.DEducation:
                typedoc = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}