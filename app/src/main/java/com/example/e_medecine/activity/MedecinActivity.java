package com.example.e_medecine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_medecine.ApiRest.Apis;
import com.example.e_medecine.ApiRest.MedecinService;
import com.example.e_medecine.R;
import com.example.e_medecine.adapter.MedecinAdapter;
import com.example.e_medecine.model.Medecin;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;
import com.example.e_medecine.utilities.MedecinResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
/*
        list=new ArrayList<>(db.getMedecins(id,typeDoc));
        adapter=new MedecinAdapter(this,list,this);
        recyclerViewMedecin.setAdapter(adapter);


 */
        getMedecinListResponse(id,typeDoc);
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
        /*Medecin medecin=list.get(position);
        intent.putExtra("idMedecin",medecin.getIdMedecin());
        intent.putExtra("nomMedecin",medecin.getUser().getNomUser());
        intent.putExtra("prenomMedecin",medecin.getUser().getPrenomUser());
        intent.putExtra("specialiteMedecin",medecin.getSpecialite().getLabel());
        intent.putExtra("experienceMedecin",medecin.getExperience());
        intent.putExtra("fraisMedecin",medecin.getFrais());
        intent.putExtra("imageMedecin",medecin.getUser().getImageUser());
        startActivity(intent);

         */
    }

    private void getMedecinListResponse(int id, String typeDoc) {
        MedecinService medecinService= Apis.getMedecinService();
        System.out.println("api");
        Call<List<Medecin>> call=medecinService.getMedecinList(id,typeDoc);
        System.out.println("calleds");
        call.enqueue(new Callback<List<Medecin>>() {
            @Override
            public void onResponse(Call<List<Medecin>> call, Response<List<Medecin>> response) {
                list=new ArrayList<>(response.body());
                adapter=new MedecinAdapter(MedecinActivity.this, list,MedecinActivity.this);
                recyclerViewMedecin.setAdapter(adapter);
                Toast.makeText(MedecinActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Medecin>> call, Throwable t) {
                Toast.makeText(MedecinActivity.this,"Failed ",Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

}