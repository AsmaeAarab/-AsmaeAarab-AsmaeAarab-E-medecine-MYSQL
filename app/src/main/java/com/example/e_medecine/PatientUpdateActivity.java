package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;


public class PatientUpdateActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_update);
        ButterKnife.bind(this);

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
    }
    public void ClickUpdate(View view){
        recreate();
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


}