package com.example.e_medecine.Docteurs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.e_medecine.R;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;
import com.google.android.material.navigation.NavigationView;

import java.sql.Blob;

public class Acceuil extends AppCompatActivity {
    private NavigationView nav;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TextView adresse,name,prename;
    private ImageView ImageDocteur;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GlobalDbHelper db;
    private String NomU = "";
    private String PrenomU = "";
    private String AdresseU = "";
    private String TelephoneU = "";
    private byte[] ImageU;
    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        db = new GlobalDbHelper(this);
        nav = (NavigationView) findViewById(R.id.navmenu);
        View v = nav.getHeaderView(0);
        Bundle ex = getIntent().getExtras();
        String lo = new String(ex.getString("Log"));
        id = db.getIdUserMailPhone(lo);
        NomU = db.getNomUser(lo);
        PrenomU = db.getPrenomUser(lo);
        TelephoneU = db.getPhoneUser(lo);
        ImageU = db.getImageUser(lo);
        AdresseU = db.getEmailUser(lo);
        name = (TextView) v.findViewById(R.id.TextNom);
        name.setText(NomU);
        prename = (TextView) v.findViewById(R.id.TextPrenom);
        prename.setText(PrenomU);
        adresse = (TextView) v.findViewById(R.id.TextAdresse);
        adresse.setText(AdresseU);
        ImageDocteur = (ImageView) v.findViewById(R.id.PersonalImage);
        Bitmap ImgUser = BitmapFactory.decodeByteArray(ImageU,0,ImageU.length);
        ImageDocteur.setImageBitmap(ImgUser);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.menu_acceuil:
                        Toast.makeText(Acceuil.this, "Home", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_RD:
                        Toast.makeText(Acceuil.this, "Rendez-vous", Toast.LENGTH_SHORT).show();
                        Intent ir = new Intent(Acceuil.this,RendezVousActivity.class);
                        ir.putExtra("Id",id);
                        startActivity(ir);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_Notification:
                        Toast.makeText(Acceuil.this, "Notifications", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_MonCompte:
                        Intent ia = new Intent(Acceuil.this,Myaccount.class);
                        ia.putExtra("ID",id);
                        ia.putExtra("NOM",NomU);
                        ia.putExtra("PRENOM",PrenomU);
                        ia.putExtra("ADDRESSE",AdresseU);
                        ia.putExtra("TELEPHONE",TelephoneU);
                        ia.putExtra("IMAGE",ImageU);
                        startActivity(ia);
                        Toast.makeText(Acceuil.this, "Mon Compte", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_calender:
                        Toast.makeText(Acceuil.this, "Modifier calendrier", Toast.LENGTH_SHORT).show();
                        Intent icalendar = new Intent(Acceuil.this,RendezVousActivity.class);
                        icalendar.putExtra("Id",id);
                        startActivity(icalendar);
                        Toast.makeText(Acceuil.this, "Veuillez Appuyer sur un patient pour modifier le Rendez-Vous", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_TC:
                        Toast.makeText(Acceuil.this, "Termes et Conditions", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_logout:
                        finish();
                        Toast.makeText(Acceuil.this, "Se déconnécter", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                nav = (NavigationView) findViewById(R.id.navmenu);
                View v = nav.getHeaderView(0);
                Bundle ex = getIntent().getExtras();
                String lo = new String(ex.getString("Log"));
                id = db.getIdUserMailPhone(lo);
                NomU = db.getNomUser(lo);
                PrenomU = db.getPrenomUser(lo);
                TelephoneU = db.getPhoneUser(lo);
                ImageU = db.getImageUser(lo);
                AdresseU = db.getEmailUser(lo);
                name = (TextView) v.findViewById(R.id.TextNom);
                name.setText(NomU);
                prename = (TextView) v.findViewById(R.id.TextPrenom);
                prename.setText(PrenomU);
                adresse = (TextView) v.findViewById(R.id.TextAdresse);
                adresse.setText(AdresseU);
                ImageDocteur = (ImageView) v.findViewById(R.id.PersonalImage);
                Bitmap ImgUser = BitmapFactory.decodeByteArray(ImageU,0,ImageU.length);
                ImageDocteur.setImageBitmap(ImgUser);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
