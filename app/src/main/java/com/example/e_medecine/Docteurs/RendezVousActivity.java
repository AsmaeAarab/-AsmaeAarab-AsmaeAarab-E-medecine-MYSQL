package com.example.e_medecine.Docteurs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.e_medecine.ApiRest.Apis;
import com.example.e_medecine.ApiRest.MedecinService;
import com.example.e_medecine.R;
import com.example.e_medecine.model.Users;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;
import com.example.e_medecine.Docteurs.Medecin;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RendezVousActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Rendezvous> listRdv;
    private RendezvousAdapter adapterRDV = null;
    private SwipeRefreshLayout swipe;
    private GlobalDbHelper db;
    private String mail = "";
    Medecin medecintest = null;
    private int IdMedecinG = 0;
    MedecinService medecinService;
    com.example.e_medecine.model.Rendezvous rdv = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int mede = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendez_vous);
        Bundle ext = getIntent().getExtras();
        int id = ext.getInt("Id");
        System.out.println("Voici Id: " + id);
        mail = new String(ext.getString("ADDRESSE"));
        swipe = (SwipeRefreshLayout) findViewById(R.id.swiper);
        listView = (ListView) findViewById(R.id.ListRdv);
        GetIdMedecin(id);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent ic = new Intent(RendezVousActivity.this,Calendrier.class);
                    ic.putExtra("IDRendezVous",listRdv.get(position).getId());
                    ic.putExtra("IDPatient",listRdv.get(position).getIdp());
                    ic.putExtra("IDMedecin",listRdv.get(position).getIdm());
                    byte[] p = StringToBitMap(listRdv.get(position).getImagenew());
                    ic.putExtra("Photo",p);
                    ic.putExtra("NomPatient",listRdv.get(position).getNom());
                    ic.putExtra("PrenomPatient",listRdv.get(position).getPrenom());
                    ic.putExtra("DateActuelle",listRdv.get(position).getDate());
                    startActivity(ic);
                }catch (Exception e){
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetIdMedecin(id);
                swipe.setRefreshing(false);
            }
        });
    }
    public int GetIdMedecin(int ID)
    {
        medecinService = Apis.getMedecinService();
        Call<List<Medecin>> call = medecinService.GetIdMedecin(ID);
        call.enqueue(new Callback<List<Medecin>>() {
            @Override
            public void onResponse(Call<List<Medecin>> call, Response<List<Medecin>> response) {
                List<Medecin> docM = response.body();
                for (Medecin medval: docM)
                {
                    System.out.println("ID Medecin: " + medval.getIdMedecin());
                    IdMedecinG = medval.getIdMedecin();
                    Toast.makeText(RendezVousActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                }
                GetPatientData(IdMedecinG);
            }

            @Override
            public void onFailure(Call<List<Medecin>> call, Throwable t) {
                Toast.makeText(RendezVousActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        return IdMedecinG;
    }
    public void GetPatientData(int ID)
    {
        medecinService = Apis.getMedecinService();
        Call<List<Rendezvous>> call = medecinService.GetPtaientsData(ID);
        call.enqueue(new Callback<List<Rendezvous>>() {
            @Override
            public void onResponse(Call<List<Rendezvous>> call, Response<List<Rendezvous>> response) {
                listRdv = new ArrayList<>(response.body());
                adapterRDV = new RendezvousAdapter(RendezVousActivity.this,R.layout.rendezvousitems,listRdv);
                listView.setAdapter(adapterRDV);
                adapterRDV.notifyDataSetChanged();
                Toast.makeText(RendezVousActivity.this, "Data Succes", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Rendezvous>> call, Throwable t) {
                Toast.makeText(RendezVousActivity.this, "Data failed", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
    public byte[] StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return encodeByte;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}