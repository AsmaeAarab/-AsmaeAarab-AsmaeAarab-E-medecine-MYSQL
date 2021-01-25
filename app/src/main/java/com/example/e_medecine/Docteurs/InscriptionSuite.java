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
    private boolean isclicked = false;
    private String namedoc,lastnamedoc,maildoc,passwordoc,phonedoc,roledoc,genderdoc;
    private final int REQUEST_CODE_GALLERY = 999;
    private GlobalDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_suite);
        init();
        db = new GlobalDbHelper(this);
        ArrayList<String> listville = db.getAllVilles();
        //ArrayAdapter<CharSequence> adapterV = ArrayAdapter.createFromResource(this,R.array.ville, android.R.layout.simple_spinner_item);
        ArrayAdapter<String> adapterV = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listville);
        adapterV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerV.setAdapter(adapterV);
        spinnerV.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapterS = ArrayAdapter.createFromResource(this,R.array.specialite,android.R.layout.simple_spinner_item);
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
        roledoc = new String(extras.getString("Role"));
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(InscriptionSuite.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
                isclicked = true;
            }
        });
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locate = localisation.getText().toString();
                charte = Condition.getText().toString();
                byte[] imgprofileval = imageViewToByte(imgpro);
                if (isclicked)
                {
                    if (genderdoc.equals("Homme"))
                    {
                        if (roledoc.equals("Docteur"))
                        {
                            //insert user ville medecin specialite
                            if (Condition.isChecked())
                            {
                                if (locate.length() > 1)
                                {
                                    int idv = 0;
                                    int iduser = 0;
                                    int idspec = 0;
                                    ContentValues values = new ContentValues();
                                    ContentValues valuesv = new ContentValues();
                                    ContentValues valuesS = new ContentValues();
                                    ContentValues valuesD = new ContentValues();
                                    valuesv.put("label",city);
                                    db.insertville(valuesv);
                                    Cursor cursor = db.getData("SELECT * FROM villes WHERE idVille");
                                    if (cursor != null && cursor.moveToLast())
                                    {
                                        idv = cursor.getInt(0);
                                    }
                                    values.put("nomUser",namedoc);
                                    values.put("prenomUser",lastnamedoc);
                                    values.put("genreUser",genderdoc);
                                    values.put("telephoneUser",phonedoc);
                                    values.put("imageUser",imgprofileval);
                                    values.put("idVille",idv);
                                    values.put("emailUser",maildoc);
                                    values.put("passwordUser",passwordoc);
                                    values.put("roleUser",roledoc);
                                    db.insertuser(values);
                                    Cursor cursor1 = db.getData("SELECT * FROM users WHERE idUser");
                                    if (cursor1 != null && cursor1.moveToLast())
                                    {
                                        iduser = cursor1.getInt(0);
                                    }
                                    valuesS.put("label",specialite);
                                    db.insertspecialite(valuesS);
                                    Cursor cursor2 = db.getData("SELECT * FROM specialites WHERE idSpecialite");
                                    if (cursor2 != null && cursor2.moveToLast())
                                    {
                                        idspec = cursor2.getInt(0);
                                    }
                                    valuesD.put("idUser",iduser);
                                    valuesD.put("idSpecialite",idspec);
                                    valuesD.put("typeMedecin",typedoc);
                                    valuesD.put("localisationMedecin",locate);
                                    valuesD.put("TermeCondition",charte);
                                    db.insertdocteur(valuesD);
                                    Toast.makeText(InscriptionSuite.this, "Inscription du Docteur Réussie", Toast.LENGTH_SHORT).show();
                                    Intent ilogin = new Intent(InscriptionSuite.this,Login.class);
                                    startActivity(ilogin);

                                }else {
                                    Toast.makeText(InscriptionSuite.this, "Veuillez Remplir les Champs ", Toast.LENGTH_SHORT).show();
                                }
                            }else
                            {
                                Toast.makeText(InscriptionSuite.this, "Veuillez cochez les termes & conditions", Toast.LENGTH_SHORT).show();
                            }
                        }else if (roledoc.equals("Client")){
                            //insert user et villes
                            if (Condition.isChecked())
                            {
                                int id = 0;
                                ContentValues values = new ContentValues();
                                ContentValues valuesv = new ContentValues();
                                valuesv.put("label",city);
                                db.insertville(valuesv);
                                Cursor cursor = db.getData("SELECT * FROM villes WHERE idVille");
                                if (cursor != null && cursor.moveToLast())
                                {
                                    id = cursor.getInt(0);
                                }
                                values.put("nomUser",namedoc);
                                values.put("prenomUser",lastnamedoc);
                                values.put("genreUser",genderdoc);
                                values.put("telephoneUser",phonedoc);
                                values.put("imageUser",imgprofileval);
                                values.put("idVille",id);
                                values.put("emailUser",maildoc);
                                values.put("passwordUser",passwordoc);
                                values.put("roleUser",roledoc);
                                db.insertuser(values);
                                Toast.makeText(InscriptionSuite.this, "Inscription du Client Réussie", Toast.LENGTH_SHORT).show();
                                Intent ilogin = new Intent(InscriptionSuite.this,Login.class);
                                startActivity(ilogin);
                            }else{
                                Toast.makeText(InscriptionSuite.this, "Veuillez cochez les termes & conditions", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else if (genderdoc.equals("Femme"))
                    {
                        if (roledoc.equals("Docteur"))
                        {
                            //insert user ville medecin specialite
                            if (Condition.isChecked())
                            {
                                if (locate.length() > 1)
                                {
                                    int idv = 0;
                                    int iduser = 0;
                                    int idspec = 0;
                                    ContentValues values = new ContentValues();
                                    ContentValues valuesv = new ContentValues();
                                    ContentValues valuesS = new ContentValues();
                                    ContentValues valuesD = new ContentValues();
                                    valuesv.put("label",city);
                                    db.insertville(valuesv);
                                    Cursor cursor = db.getData("SELECT * FROM villes WHERE idVille");
                                    if (cursor != null && cursor.moveToLast())
                                    {
                                        idv = cursor.getInt(0);
                                    }
                                    values.put("nomUser",namedoc);
                                    values.put("prenomUser",lastnamedoc);
                                    values.put("genreUser",genderdoc);
                                    values.put("telephoneUser",phonedoc);
                                    values.put("imageUser",imgprofileval);
                                    values.put("idVille",idv);
                                    values.put("emailUser",maildoc);
                                    values.put("passwordUser",passwordoc);
                                    values.put("roleUser",roledoc);
                                    db.insertuser(values);
                                    Cursor cursor1 = db.getData("SELECT * FROM users WHERE idUser");
                                    if (cursor1 != null && cursor1.moveToLast())
                                    {
                                        iduser = cursor1.getInt(0);
                                    }
                                    valuesS.put("label",specialite);
                                    db.insertspecialite(valuesS);
                                    Cursor cursor2 = db.getData("SELECT * FROM specialites WHERE idSpecialite");
                                    if (cursor2 != null && cursor2.moveToLast())
                                    {
                                        idspec = cursor2.getInt(0);
                                    }
                                    valuesD.put("idUser",iduser);
                                    valuesD.put("idSpecialite",idspec);
                                    valuesD.put("typeMedecin",typedoc);
                                    valuesD.put("localisationMedecin",locate);
                                    valuesD.put("TermeCondition",charte);
                                    db.insertdocteur(valuesD);
                                    Toast.makeText(InscriptionSuite.this, "Inscription du Docteur Réussie", Toast.LENGTH_SHORT).show();
                                    Intent ilogin = new Intent(InscriptionSuite.this,Login.class);
                                    startActivity(ilogin);
                                }else {
                                    Toast.makeText(InscriptionSuite.this, "Veuillez Remplir les Champs", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(InscriptionSuite.this, "Veuillez cochez les termes & conditions", Toast.LENGTH_SHORT).show();
                            }
                        }else if (roledoc.equals("Client")){
                            //insert user et villes
                            if (Condition.isChecked())
                            {
                                int id = 0;
                                ContentValues values = new ContentValues();
                                ContentValues valuesv = new ContentValues();
                                valuesv.put("label",city);
                                db.insertville(valuesv);
                                Cursor cursor = db.getData("SELECT * FROM villes WHERE idVille");
                                if (cursor != null && cursor.moveToLast())
                                {
                                    id = cursor.getInt(0);
                                }
                                values.put("nomUser",namedoc);
                                values.put("prenomUser",lastnamedoc);
                                values.put("genreUser",genderdoc);
                                values.put("telephoneUser",phonedoc);
                                values.put("imageUser",imgprofileval);
                                values.put("idVille",id);
                                values.put("emailUser",maildoc);
                                values.put("passwordUser",passwordoc);
                                values.put("roleUser",roledoc);
                                db.insertuser(values);
                                Toast.makeText(InscriptionSuite.this, "Inscription du Client Réussie", Toast.LENGTH_SHORT).show();
                                Intent ilogin = new Intent(InscriptionSuite.this,Login.class);
                                startActivity(ilogin);
                            }else{
                                Toast.makeText(InscriptionSuite.this, "Veuillez cochez les termes & conditions", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else{
                    Toast.makeText(InscriptionSuite.this, "Veuillez choisir une photo de profile", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void back(View v)
    {
        finish();
        /*Intent i3 = new Intent(InscriptionSuite.this, Inscription.class);
        startActivity(i3);*/
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