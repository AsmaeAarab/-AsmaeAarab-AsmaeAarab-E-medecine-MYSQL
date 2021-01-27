package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.e_medecine.activity.MedecinDetailleActivity;
import com.example.e_medecine.activity.SpecialitesActivity;
import com.example.e_medecine.model.Specialite;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PatientAccueilActivity extends AppCompatActivity {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_accueil);
        ButterKnife.bind(this);
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
        recreate();
    }
    public void ClickUpdate(View view){
        redirectActivity(this,PatientUpdateActivity.class);
    }
    public void ClickLogout(View view){
        redirectActivity(this,PatientLoginActivity.class);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent=new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        Bundle ex = getIntent().getExtras();
        String lo = new String(ex.getString("emailPatient"));
        intent.putExtra("email_patient",lo);
        startActivity(intent);
    }
}