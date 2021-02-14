package com.example.e_medecine.Docteurs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.e_medecine.ApiRest.Apis;
import com.example.e_medecine.ApiRest.MedecinService;
import com.example.e_medecine.R;
import com.example.e_medecine.model.User;
import com.example.e_medecine.model.Users;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;
import com.google.android.material.navigation.NavigationView;

import java.sql.Blob;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private int IdMysql = 0;
    private String NomMysql = "";
    private String PrenomMysql = "";
    private String MailMysql = "";
    private String PhoneMysql = "";
    private byte[] ImageMysql;
    private String lo = "";
    MedecinService medecinService;
    Users userTest = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        db = new GlobalDbHelper(this);
        nav = (NavigationView) findViewById(R.id.navmenu);
        View v = nav.getHeaderView(0);
        Bundle ex = getIntent().getExtras();
        lo = new String(ex.getString("Login"));
        System.out.println("Login: " + lo);
        AsyncTask<Void,Void,Users> user = new HttpRequest().execute();
        //GetElementusers(lo);
        id = db.getIdUserMailPhone(lo);
        NomU = db.getNomUser(lo);
        PrenomU = db.getPrenomUser(lo);
        TelephoneU = db.getPhoneUser(lo);
        ImageU = db.getImageUser(lo);
        AdresseU = db.getEmailUser(lo);
        name = (TextView) v.findViewById(R.id.TextNom);
        //name.setText(NomU);
        name.setText(NomMysql);
        prename = (TextView) v.findViewById(R.id.TextPrenom);
        //prename.setText(PrenomU);
        prename.setText(PrenomMysql);
        adresse = (TextView) v.findViewById(R.id.TextAdresse);
        //adresse.setText(AdresseU);
        adresse.setText(MailMysql);
        ImageDocteur = (ImageView) v.findViewById(R.id.PersonalImage);
        //Bitmap ImgUser = BitmapFactory.decodeByteArray(ImageU,0,ImageU.length);
        //Bitmap ImgSql = BitmapFactory.decodeByteArray(ImageMysql,0,ImageMysql.length);
        //ImageDocteur.setImageBitmap(ImgSql);
        //ImageDocteur.setImageBitmap(ImgUser);
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
                        ir.putExtra("Id",IdMysql);
                        ir.putExtra("ADDRESSE",MailMysql);
                        startActivity(ir);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_Notification:
                        Toast.makeText(Acceuil.this, "Notifications", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_MonCompte:
                        Intent ia = new Intent(Acceuil.this,Myaccount.class);
                        /*ia.putExtra("ID",id);
                        ia.putExtra("NOM",NomU);
                        ia.putExtra("PRENOM",PrenomU);
                        ia.putExtra("ADDRESSE",AdresseU);
                        ia.putExtra("TELEPHONE",TelephoneU);
                        ia.putExtra("IMAGE",ImageU);*/
                        ia.putExtra("ID",IdMysql);
                        ia.putExtra("NOM",NomMysql);
                        ia.putExtra("PRENOM",PrenomMysql);
                        ia.putExtra("ADDRESSE",MailMysql);
                        ia.putExtra("TELEPHONE",PhoneMysql);
                        ia.putExtra("IMAGE",ImageMysql);
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
                String lo = new String(ex.getString("Login"));
                id = db.getIdUserMailPhone(lo);
                /*NomU = db.getNomUser(lo);
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
                ImageDocteur.setImageBitmap(ImgUser);*/
                AsyncTask<Void,Void,Users> user = new HttpRequest().execute();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    public class HttpRequest extends AsyncTask<Void,Void, Users> {
        @Override
        protected Users doInBackground(Void... voids) {
            RestApi api = new RestApi();
            userTest = api.findPhoneID(lo);
            return userTest;
        }

        @Override
        protected void onPostExecute(Users users) {
            name.setText(userTest.getNomUser());
            prename.setText(userTest.getPrenomUser());
            adresse.setText(userTest.getEmailUser());
            ImageMysql = userTest.getImageUser();
            Bitmap bm = BitmapFactory.decodeByteArray(ImageMysql,0,ImageMysql.length);
            ImageDocteur.setImageBitmap(bm);
            IdMysql = userTest.getIdUser();
            NomMysql = userTest.getNomUser();
            PrenomMysql = userTest.getPrenomUser();
            MailMysql = userTest.getEmailUser();
            PhoneMysql = userTest.getTelephoneUser();


            SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPreferences.edit();
            prefEditor.putString("EmailUser", userTest.getEmailUser());
            prefEditor.apply();
        }

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

    /*public void GetElementusers(String Logn)
    {
        medecinService = Apis.getMedecinService();
        Call<List<Users>> call = medecinService.GetElementUsers(Logn);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                List<Users> usx = response.body();
                for (Users ulv: usx)
                {
                    IdMysql = ulv.getIdUser();
                    NomMysql = ulv.getNomUser();
                    name.setText(NomMysql);
                    PrenomMysql = ulv.getPrenomUser();
                    prename.setText(PhoneMysql);
                    MailMysql = ulv.getEmailUser();
                    adresse.setText(MailMysql);
                    //PhoneMysql = ulv.getTelephoneUser();
                    //ImageMysql = ulv.getImageUser();
                }
                System.out.println("Data: "+ImageMysql+NomMysql+PrenomMysql+MailMysql+PhoneMysql+ImageMysql);
                Toast.makeText(Acceuil.this, "Data Retrieved", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(Acceuil.this, "Failed retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
