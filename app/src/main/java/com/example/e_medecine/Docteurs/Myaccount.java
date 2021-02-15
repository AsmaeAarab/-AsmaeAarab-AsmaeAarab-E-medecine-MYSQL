package com.example.e_medecine.Docteurs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.e_medecine.ApiRest.Apis;
import com.example.e_medecine.ApiRest.MedecinService;
import com.example.e_medecine.ApiRest.PatientService;
import com.example.e_medecine.R;
import com.example.e_medecine.model.User;
import com.example.e_medecine.model.Users;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Myaccount extends AppCompatActivity {

    private EditText AccountNom,AccountPrenom,AccountAdresse,AccountTelephone;
    private ImageView ImageP;
    private CheckBox BoxNom,BoxPrenom,BoxAdresse,BoxPhone;
    private Button MAJ,changeImage,saveimage;
    private boolean clicked = false;
    private final int REQUEST_CODE_GALLERY = 999;
    private GlobalDbHelper db;
    MedecinService medecinService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        initAccount();
        Bundle extras = getIntent().getExtras();
        int ID = extras.getInt("ID");
        System.out.println("IDM: " + ID);
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
        ///////////MYSQL
        /*SharedPreferences result = getSharedPreferences("data", Context.MODE_PRIVATE);
        String login1= result.getString("EmailUser", "default-value");*/
        //GetElementusers(login1);
        /////////FIN MYSQL
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
                    Users ui = new Users();
                    ui.setImageUser(imgval);
                    UpdateMedecinImage(ui,ID);
                    db.updateImage(imgval,ID);
                    Toast.makeText(Myaccount.this, "Your Profile Image has been changed", Toast.LENGTH_SHORT).show();
                    finish();
                    Toast.makeText(Myaccount.this, "Swipe Down the Home Page To Actualize your Data ", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Myaccount.this, "Please choose your New Profil Image", Toast.LENGTH_SHORT).show();
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
                        Users un = new Users();
                        un.setNomUser(Dnom);
                        UpdateMedecinNom(un,ID);
                        db.updatenom(Dnom,ID);
                        Toast.makeText(Myaccount.this, "Your Last Name has been successfully changed", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Myaccount.this, "Swipe Down the Home Page To Actualize your Data ", Toast.LENGTH_LONG).show();
                    }
                    if (BoxPrenom.isChecked())
                    {
                        Users up = new Users();
                        up.setPrenomUser(Dprenom);
                        UpdateMedecinPrenom(up,ID);
                        db.updateprenom(Dprenom,ID);
                        Toast.makeText(Myaccount.this, "Your first name has been changed successfully", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Myaccount.this, "Swipe Down the Home Page To Actualize your Data ", Toast.LENGTH_LONG).show();
                    }
                    if (BoxAdresse.isChecked())
                    {
                        Users um = new Users();
                        um.setEmailUser(Dmail);
                        UpdateMedecinEmail(um,ID);
                        db.updatemail(Dmail,ID);
                        Toast.makeText(Myaccount.this, "Your address has been successfully changed", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Myaccount.this, "Swipe Down the Home Page To Actualize your Data ", Toast.LENGTH_LONG).show();
                    }
                    if (BoxPhone.isChecked())
                    {
                        Users uph = new Users();
                        uph.setTelephoneUser(Dphone);
                        UpdateMedecinPhone(uph,ID);
                        db.updatephone(Dphone,ID);
                        Toast.makeText(Myaccount.this, "Your Number Phone has been successfully changed", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Myaccount.this, "Swipe Down the Home Page To Actualize your Data ", Toast.LENGTH_LONG).show();
                    }
                    finish();
                }else {
                    Toast.makeText(Myaccount.this, "Please fill at least one of the fields and check the corresponding checkbox ", Toast.LENGTH_LONG).show();
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
    public void UpdateMedecinNom(Users u ,int ID)
    {
        medecinService = Apis.getMedecinService();
        Call<Users> call = medecinService.UpdateMedecinNom(u,ID);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(Myaccount.this, "Your Name Has been changed successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(Myaccount.this, "Failure please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void UpdateMedecinPrenom(Users u,int ID)
    {
        medecinService = Apis.getMedecinService();
        Call<Users> call = medecinService.UpdateMedecinPrenom(u,ID);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Toast.makeText(Myaccount.this, "Your LastName Has been changed successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(Myaccount.this, "Failure please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void UpdateMedecinPhone(Users u,int ID)
    {
        medecinService = Apis.getMedecinService();
        Call<Users> call = medecinService.UpdateMedecinPhone(u,ID);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Toast.makeText(Myaccount.this, "Your Phone Has been changed successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(Myaccount.this, "Failure please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void UpdateMedecinEmail(Users u,int ID)
    {
        medecinService = Apis.getMedecinService();
        Call<Users> call = medecinService.UpdateMedecinEmail(u,ID);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Toast.makeText(Myaccount.this, "Your Email Has been changed successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(Myaccount.this, "Failure please Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void UpdateMedecinImage(Users u,int  ID)
    {
        medecinService = Apis.getMedecinService();
        Call<Users> call = medecinService.UpdateMedecinImage(u,ID);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Toast.makeText(Myaccount.this, "Your Photo Profil Has been changed successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(Myaccount.this, "Your Photo Profil Failed", Toast.LENGTH_SHORT).show();
            }
        });
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
    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }



}