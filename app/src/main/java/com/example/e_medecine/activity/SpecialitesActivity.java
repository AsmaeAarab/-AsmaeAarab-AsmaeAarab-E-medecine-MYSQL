package com.example.e_medecine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_medecine.R;
import com.example.e_medecine.adapter.SpecialitesAdapter;
import com.example.e_medecine.model.Medecin;
import com.example.e_medecine.model.Specialite;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialitesActivity extends AppCompatActivity implements SpecialitesAdapter.OnSpecialiteListener {
    GlobalDbHelper db=new GlobalDbHelper(this);;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    @BindView(R.id.search_bar)
    EditText search_edt;
    CharSequence search="";

    ArrayList<Specialite> list;
    SpecialitesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialites);
        ButterKnife.bind(this);

        list=new ArrayList<>(db.getSpecialites());
        adapter=new SpecialitesAdapter(this,list,this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

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
    public void onSpecialiteClick(int position) {

        Intent intent=new Intent(this,MedecinActivity.class);
        Specialite specialite=list.get(position);
        intent.putExtra("specialite",specialite.getId_specialite());
        startActivity(intent);
    }

}