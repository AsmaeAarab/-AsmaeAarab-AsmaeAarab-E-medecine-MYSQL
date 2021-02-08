package com.example.e_medecine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_medecine.R;
import com.example.e_medecine.adapter.SpecialitesAdapter;
import com.example.e_medecine.model.Medecin;
import com.example.e_medecine.model.Specialite;
import com.example.e_medecine.service.SpecialiteService;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;
import com.example.e_medecine.utilities.APIs;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecialitesActivity extends AppCompatActivity implements SpecialitesAdapter.OnSpecialiteListener {
    SpecialiteService specialiteService;
    GlobalDbHelper db=new GlobalDbHelper(this);;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    @BindView(R.id.search_bar)
    EditText search_edt;
    CharSequence search="";

    List<Specialite> list= new ArrayList<>();
    SpecialitesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialites);
        ButterKnife.bind(this);
        listSpecialite();
        /*

        list=new ArrayList<>(db.getSpecialites());
        adapter=new SpecialitesAdapter(this,list,this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

         */

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

    public void listSpecialite(){
        specialiteService= APIs.getSpecialiteService();
        Call<List<Specialite>> call=specialiteService.getspecialite();
        call.enqueue(new Callback<List<Specialite>>() {
            @Override
            public void onResponse(Call<List<Specialite>> call, Response<List<Specialite>> response) {
                if(response.isSuccessful()) {

                    list=response.body();
                    adapter=new SpecialitesAdapter(SpecialitesActivity.this,list,SpecialitesActivity.this);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(SpecialitesActivity.this,2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Specialite>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
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