package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_accueil);
        ButterKnife.bind(this);
        db=new GlobalDbHelper(this);

        Intent intent=getIntent();
        String emailUser=intent.getStringExtra("EmailUser");

        email.setText(emailUser);
        nom.setText(db.getNomUser(emailUser));
        prenom.setText(db.getPrenomUser(emailUser));
        ImageU=db.getImageUser(emailUser);
        Bitmap ImgUser = BitmapFactory.decodeByteArray(ImageU,0,ImageU.length);
        imgProfile.setImageBitmap(ImgUser);

        if(modif.equals("yes")){
            Intent intent1=getIntent();
            String EmailUpdate=intent1.getStringExtra("EmailUpdate");
            email.setText(EmailUpdate);
            nom.setText(db.getNomUser(EmailUpdate));
            prenom.setText(db.getPrenomUser(EmailUpdate));
            ImageU=db.getImageUser(EmailUpdate);
            Bitmap ImgUser1 = BitmapFactory.decodeByteArray(ImageU,0,ImageU.length);
            imgProfile.setImageBitmap(ImgUser1);
            modif="no";
        }

    }

    @Optional
    @OnClick(R.id.rdv)
    public void prendreRDV(){
        // Intent intent = new Intent(this, PatientAccueilActivity.class);
        //   startActivity(intent);
    }

    @Optional
    @OnClick(R.id.consultation)
    public void consulter(){
        //  Intent intent = new Intent(this, PatientAccueilActivity.class);
        //  startActivity(intent);
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
}