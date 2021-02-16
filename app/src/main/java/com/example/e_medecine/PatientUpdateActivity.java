package com.example.e_medecine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.e_medecine.ApiRest.Apis;
import com.example.e_medecine.ApiRest.PatientService;
import com.example.e_medecine.model.User;
import com.example.e_medecine.model.Users;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PatientUpdateActivity extends AppCompatActivity {


    @BindView(R.id.prenom)
    EditText editTextPrenom;
    @BindView(R.id.nom)
    EditText editTextNom;
    @BindView(R.id.email)
    EditText editTextEmail;
    @BindView(R.id.phone)
    EditText editTextPhone;
    @BindView(R.id.imageProfil)
    ImageView ImageP;

    private final int REQUEST_CODE_GALLERY = 999;
    GlobalDbHelper db;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    String emailUser;
    private byte[] ImageU;

    public static String modif="no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_update);
        ButterKnife.bind(this);
        db=new GlobalDbHelper(this);
        Intent intent=getIntent();
        String emailUser=intent.getStringExtra("EmailUser");

        editTextEmail.setText(emailUser);
       /* editTextNom.setText(db.getNomUser(emailUser));
        editTextPrenom.setText(db.getPrenomUser(emailUser));
        editTextPhone.setText(db.getPhoneUser(emailUser));

        ImageU=db.getImageUser(emailUser);
        Bitmap ImgUser = BitmapFactory.decodeByteArray(ImageU,0,ImageU.length);
        ImageP.setImageBitmap(ImgUser);*/  //////:SQLITE
        //////////MYSQL
         GetElementPatient(emailUser);
        //////////FIN MYSQL
    }
    String email;
    @OnClick(R.id.update)
    public void update(){
        Intent intent = new Intent(this, PatientAccueilActivity.class);
        String nom = editTextNom.getText().toString();
        String prenom = editTextPrenom.getText().toString();
        String phone = editTextPhone.getText().toString();
         email = editTextEmail.getText().toString();
        byte[] imgProfile = imageProToByte(ImageP);

        Boolean update = db.updateUser(imgProfile,nom,prenom ,phone,email);
       /* if (update == true) {
            modif="yes";
            intent.putExtra("EmailUpdate",email);
            Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "update failed", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }*/

        /////////////////MYSQL
        Users u=new Users();
        u.setNomUser(nom);
        u.setPrenomUser(prenom);
        u.setTelephoneUser(phone);
        u.setImageUser(imgProfile);
        updatePatient(u,email);
        ////////////////FIN MYSQL
    }

    @OnClick(R.id.ChooseProfile)
    public void setChoose(){
        ActivityCompat.requestPermissions(PatientUpdateActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY);
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

    PatientService service;
    public void updatePatient(Users u,String login){
        Intent intent1 =new Intent(PatientUpdateActivity.this,PatientAccueilActivity.class);
        service= Apis.getPatientsService();
        Call<Users> call=service.updatePatient(u,login);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Update No", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                modif="yes";
                intent1.putExtra("EmailUpdate",email);
                Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
                Log.e("Error:",t.getMessage());
            }
        });
    }

    private int IdMysql = 0;
    private String NomMysql = "";
    private String PrenomMysql = "";
    private String MailMysql = "";
    private String PhoneMysql = "";
    private String ImageMysql="";
    public boolean GetElementPatient(String login){
        service = Apis.getPatientsService();
        Call<List<Users>> call = service.GetElementPatient(login);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                List<Users> usx = response.body();
                for (Users ulv: usx)
                {
                    IdMysql = ulv.getIdUser();
                    NomMysql = ulv.getNomUser();
                    PrenomMysql = ulv.getPrenomUser();
                    MailMysql = ulv.getEmailUser();
                    PhoneMysql = ulv.getTelephoneUser();
                    ImageMysql = ulv.getImage();
                    Bitmap bm = StringToBitMap(ImageMysql);

                    ImageP.setImageBitmap(bm);
                    editTextNom.setText(NomMysql);
                    editTextPrenom.setText(PrenomMysql);
                    editTextPhone.setText(PhoneMysql);
                }
                Toast.makeText(getApplicationContext(), "Data Retrieved", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
               // Toast.makeText(getApplicationContext(), "Failed retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
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