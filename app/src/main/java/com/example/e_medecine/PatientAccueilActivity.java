package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_medecine.ApiRest.Apis;
import com.example.e_medecine.ApiRest.PatientService;
import com.example.e_medecine.Docteurs.Acceuil;
import com.example.e_medecine.model.Users;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import com.example.e_medecine.activity.MedecinDetailleActivity;
import com.example.e_medecine.activity.SpecialitesActivity;
import com.example.e_medecine.model.Specialite;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.e_medecine.PatientUpdateActivity.modif;


public class PatientAccueilActivity extends AppCompatActivity {

    GlobalDbHelper db;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.nom)
    TextView nom;
    @BindView(R.id.prenom)
    TextView prenom;
    @BindView(R.id.email)
    TextView email;

    private byte[] ImageU;
    private SharedPreferences prefs;
    private  static final String PREFS_TYPE="PrefDoc";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_accueil);
        ButterKnife.bind(this);
        db=new GlobalDbHelper(this);

        Intent intent=getIntent();
        String emailUser=intent.getStringExtra("EmailUser");

        /////////////MYSQL
        email.setText(emailUser);
       GetElementPatient(emailUser);
        /////////////FIN MYSQL

       /* email.setText(emailUser);
        nom.setText(db.getNomUser(emailUser));
        prenom.setText(db.getPrenomUser(emailUser));
        ImageU=db.getImageUser(emailUser);
        Bitmap ImgUser = BitmapFactory.decodeByteArray(ImageU,0,ImageU.length);
        imgProfile.setImageBitmap(ImgUser);*/  //////:SQLITE

        if(modif.equals("yes")){
            Intent intent1=getIntent();
            String EmailUpdate=intent1.getStringExtra("EmailUpdate");
          /*  email.setText(EmailUpdate);
            nom.setText(db.getNomUser(EmailUpdate));
            prenom.setText(db.getPrenomUser(EmailUpdate));
            ImageU=db.getImageUser(EmailUpdate);
            Bitmap ImgUser1 = BitmapFactory.decodeByteArray(ImageU,0,ImageU.length);
            imgProfile.setImageBitmap(ImgUser1);*/ ////// SQLITE
            /////////////MYSQL
            email.setText(EmailUpdate);
            GetElementPatient(EmailUpdate);
            /////////////FIN MYSQL
            modif="no";
        }
        prefs=getSharedPreferences(PREFS_TYPE,MODE_PRIVATE);
    }

    PatientService service;
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
                    nom.setText(NomMysql);
                    prenom.setText(PrenomMysql);
                    //PhoneMysql = ulv.getTelephoneUser();
                    ImageMysql = ulv.getImage();
                    Bitmap bm = StringToBitMap(ImageMysql);
                    imgProfile.setImageBitmap(bm);
                }
                System.out.println("Data: image"+ImageMysql);
                Toast.makeText(getApplicationContext(), "Data Retrieved", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed retrieve data", Toast.LENGTH_SHORT).show();
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


    //DARAWER CODE START
    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public  void ClickHome(View view){
        redirectActivity(this,PatientAccueilActivity.class);
        //recreate();
        finish();
    }
    public void ClickUpdate(View view){
        redirectActivity(this,PatientUpdateActivity.class);
    }
    public void ClickLogout(View view){
        redirectActivity(this,PatientLoginActivity.class);finish();
    }

    public void redirectActivity(Activity activity, Class aClass) {
        Intent intent=new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String emailU=email.getText().toString();
        intent.putExtra("EmailUser",emailU);
        activity.startActivity(intent);
    }
    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);

    }
    //DARAWER CODE END

    @OnClick(R.id.rdv)
    public void rendezVous(){
        Intent intent=new Intent(this, SpecialitesActivity.class);
        SharedPreferences.Editor editor = prefs.edit ();
        editor.putString("pref_typeDoc","Docteur");
        editor.apply();
        startActivity(intent);
    }
    @Optional
    @OnClick(R.id.consultation)
    public void consulter(){
        Intent intent=new Intent(this, SpecialitesActivity.class);
        SharedPreferences.Editor editor = prefs.edit ();
        editor.putString("pref_typeDoc","E-Docteur");
        editor.apply();
        startActivity(intent);
    }
}