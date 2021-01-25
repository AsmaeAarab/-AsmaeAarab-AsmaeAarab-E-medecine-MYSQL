package com.example.e_medecine.Docteurs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.e_medecine.R;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;

public class Myaccount extends AppCompatActivity {

    private EditText AccountNom,AccountPrenom,AccountAdresse,AccountTelephone;
    private ImageView ImageP;
    private CheckBox BoxNom,BoxPrenom,BoxAdresse,BoxPhone;
    private Button MAJ,changeImage,saveimage;
    private boolean clicked = false;
    private final int REQUEST_CODE_GALLERY = 999;
    private GlobalDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        initAccount();
        Bundle extras = getIntent().getExtras();
        int ID = extras.getInt("ID");
        String Nom = new String(extras.getString("NOM"));
        String Prenom = new String(extras.getString("PRENOM"));
        String Adresse = new String(extras.getString("ADDRESSE"));
        String Tele = new String(extras.getString("TELEPHONE"));
        byte[] img = extras.getByteArray("IMAGE");
        Bitmap bm = BitmapFactory.decodeByteArray(img,0,img.length);
        ImageP.setImageBitmap(bm);
        AccountNom.setHint(Nom);
        AccountPrenom.setHint(Prenom);
        AccountAdresse.setHint(Adresse);
        AccountTelephone.setHint(Tele);
        db = new GlobalDbHelper(this);
        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(Myaccount.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
                clicked = true;
            }
        });
        saveimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked)
                {
                    byte[] imgval = imageProToByte(ImageP);
                    db.updateImage(imgval,ID);
                    Toast.makeText(Myaccount.this, "Votre Image de profil a été changé", Toast.LENGTH_SHORT).show();
                    finish();
                    Toast.makeText(Myaccount.this, "Balayer la page Acceuil vers le bas pour l'actualiser ", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Myaccount.this, "Veuillez choisir Votre nouvelle image de profile", Toast.LENGTH_SHORT).show();
                }
            }
        });
        MAJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Dnom = AccountNom.getText().toString();
                String Dprenom = AccountPrenom.getText().toString();
                String Dmail = AccountAdresse.getText().toString();
                String Dphone = AccountTelephone.getText().toString();
                if (Dnom.length() > 1 || Dprenom.length() > 1 || Dmail.length() > 1 || Dphone.length() > 1)
                {
                    if (BoxNom.isChecked())
                    {
                        db.updatenom(Dnom,ID);
                        Toast.makeText(Myaccount.this, "Votre Nom a été changé avec succès", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Myaccount.this, "Balayer la page Acceuil vers le bas pour l'actualiser ", Toast.LENGTH_LONG).show();
                    }
                    if (BoxPrenom.isChecked())
                    {
                        db.updateprenom(Dprenom,ID);
                        Toast.makeText(Myaccount.this, "Votre Prénom a été changé avec succès", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Myaccount.this, "Balayer la page Acceuil vers le bas pour l'actualiser ", Toast.LENGTH_LONG).show();
                    }
                    if (BoxAdresse.isChecked())
                    {
                        db.updatemail(Dmail,ID);
                        Toast.makeText(Myaccount.this, "Votre addresse a été changé avec succès", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Myaccount.this, "Balayer la page Acceuil vers le bas pour l'actualiser ", Toast.LENGTH_LONG).show();
                    }
                    if (BoxPhone.isChecked())
                    {
                        db.updatephone(Dphone,ID);
                        Toast.makeText(Myaccount.this, "Votre Téléphone a été changé avec succès", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Myaccount.this, "Balayer la page Acceuil vers le bas pour l'actualiser ", Toast.LENGTH_LONG).show();
                    }
                    finish();
                }else {
                    Toast.makeText(Myaccount.this, "Veuillez écrire La nouvelle donnée et cocher la case voulue", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void initAccount()
    {
        AccountNom = (EditText) findViewById(R.id.DocteurName);
        AccountPrenom = (EditText) findViewById(R.id.DocteurLastname);
        AccountAdresse = (EditText) findViewById(R.id.DocteurAdresse);
        AccountTelephone = (EditText) findViewById(R.id.DocteurPhone);
        ImageP = (ImageView) findViewById(R.id.imageP);
        BoxNom = (CheckBox) findViewById(R.id.checkNom);
        BoxPrenom = (CheckBox) findViewById(R.id.checkPrenom);
        BoxAdresse = (CheckBox) findViewById(R.id.checkAdresse);
        BoxPhone = (CheckBox) findViewById(R.id.checkTelephone);
        saveimage = (Button) findViewById(R.id.buttonsave);
        changeImage = (Button) findViewById(R.id.ChangeImage);
        MAJ = (Button) findViewById(R.id.ButtonUPdate);
    }
    private byte[] imageProToByte(ImageView ImageP) {
        Bitmap bitmaps = ((BitmapDrawable)ImageP.getDrawable()).getBitmap();
        Bitmap bitmapreduced = reduceBitmapSize(bitmaps,240000);
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
                ImageP.setImageBitmap(bitmap);
            }catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}