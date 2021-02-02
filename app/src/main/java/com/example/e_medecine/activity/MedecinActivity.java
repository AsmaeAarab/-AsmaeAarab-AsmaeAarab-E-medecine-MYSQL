package com.example.e_medecine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.e_medecine.R;
import com.example.e_medecine.adapter.MedecinAdapter;
import com.example.e_medecine.model.Medecin;
import com.example.e_medecine.model.User;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;
import com.example.e_medecine.sqliteBd.MedecinTable;
import com.example.e_medecine.sqliteBd.UserTable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedecinActivity extends AppCompatActivity implements  MedecinAdapter.OnMedecinListener {

    GlobalDbHelper db=new GlobalDbHelper(this);
    @BindView(R.id.recycleViewMedecin)
    RecyclerView recyclerViewMedecin;

    @BindView(R.id.search_bar_medecin)
    EditText search_edt;
    CharSequence search="";

    ArrayList<Medecin> list;
    MedecinAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecin);
        ButterKnife.bind(this);
        int id = (int) getIntent().getSerializableExtra("specialite");
        SharedPreferences sp= getSharedPreferences("PrefDoc",MODE_PRIVATE);
        String typeDoc = sp.getString ("pref_typeDoc","valeur par défaut");
        list=new ArrayList<>(db.getMedecins(id,typeDoc));
        adapter=new MedecinAdapter(this,list,this);
        recyclerViewMedecin.setAdapter(adapter);

        search_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                search=s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public void onMedecinClick(int position) {
        Intent intent=new Intent(this,MedecinDetailleActivity.class);
        Medecin medecin=list.get(position);
        intent.putExtra("idMedecin",medecin.getIdMedecin());
        intent.putExtra("nomMedecin",medecin.getNomMedecin());
        intent.putExtra("prenomMedecin",medecin.getPrenomMedecin());
        intent.putExtra("specialiteMedecin",medecin.getSpecialite());
        intent.putExtra("experienceMedecin",medecin.getExperience());
        intent.putExtra("fraisMedecin",medecin.getFrais());
        intent.putExtra("imageMedecin",medecin.getImageMedecin());
        startActivity(intent);
    }

}