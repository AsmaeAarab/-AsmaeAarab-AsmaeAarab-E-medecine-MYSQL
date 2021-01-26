package com.example.e_medecine.Docteurs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.e_medecine.R;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RendezVousActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Rendezvous> listRdv;
    private RendezvousAdapter adapterRDV = null;
    private SwipeRefreshLayout swipe;
    private GlobalDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendez_vous);
        Bundle ext = getIntent().getExtras();
        int id = ext.getInt("Id");
        System.out.println("Voici Id: " + id);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swiper);
        listView = (ListView) findViewById(R.id.ListRdv);
        listRdv = new ArrayList<>();
        adapterRDV = new RendezvousAdapter(this,R.layout.rendezvousitems,listRdv);
        listView.setAdapter(adapterRDV);
        db = new GlobalDbHelper(this);
        /*int idu = db.GetiduserRDV(id);
        int idp = db.GetidpatientRDV(id);
        int idm = db.GetidmedecinRDV(id);
        byte[] img = db.GetImageRDV(id);
        String nom = db.GetNomUserRDV(id);
        String prenom = db.GetPrenomUserRDV(id);
        String titrerdv = db.GetTitrePatientRDV(id);
        String daterdv = db.GetDatePatientRDV(id);
        listRdv.add(new Rendezvous(idu,idp,idm,img,nom,prenom,titrerdv,daterdv));*/
        Cursor cursor = db.getdataRendezvous(id);
        listRdv.clear();
        while (cursor.moveToNext())
        {
            int idu = cursor.getInt(0);
            int idp = cursor.getInt(1);
            int idm = cursor.getInt(2);
            byte[] img = cursor.getBlob(3);
            String nom = cursor.getString(4);
            String prenom = cursor.getString(5);
            String titrerdv = cursor.getString(6);
            String daterdv = cursor.getString(7);
            listRdv.add(new Rendezvous(idu,idp,idm,img,nom,prenom,titrerdv,daterdv));
        }
        adapterRDV.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent ic = new Intent(RendezVousActivity.this,Calendrier.class);
                    ic.putExtra("IDUser",listRdv.get(position).getId());
                    ic.putExtra("IDPatient",listRdv.get(position).getIdp());
                    ic.putExtra("IDMedecin",listRdv.get(position).getIdm());
                    ic.putExtra("Photo",listRdv.get(position).getImage());
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
                Cursor cursor = db.getdataRendezvous(id);
                listRdv.clear();
                while (cursor.moveToNext())
                {
                    int idu = cursor.getInt(0);
                    int idp = cursor.getInt(1);
                    int idm = cursor.getInt(2);
                    byte[] img = cursor.getBlob(3);
                    String nom = cursor.getString(4);
                    String prenom = cursor.getString(5);
                    String titrerdv = cursor.getString(6);
                    String daterdv = cursor.getString(7);
                    listRdv.add(new Rendezvous(idu,idp,idm,img,nom,prenom,titrerdv,daterdv));
                }
                adapterRDV.notifyDataSetChanged();
                swipe.setRefreshing(false);
            }
        });
    }
}