package com.example.e_medecine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_medecine.ApiRest.Apis;
import com.example.e_medecine.R;
import com.example.e_medecine.adapter.SpecialitesAdapter;
import com.example.e_medecine.model.Specialite;
import com.example.e_medecine.ApiRest.SpecialiteService;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecialitesActivity extends AppCompatActivity implements SpecialitesAdapter.OnSpecialiteListener {
    GlobalDbHelper db=new GlobalDbHelper(this);;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    @BindView(R.id.search_bar)
    EditText search_edt;
    CharSequence search="";

    ArrayList<Specialite> list= new ArrayList<>();
    SpecialitesAdapter adapter=new SpecialitesAdapter(this,list,this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialites);
        ButterKnife.bind(this);
        getSpecialitiesResponse();

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

    private void getSpecialitiesResponse() {
        SpecialiteService specialiteService=Apis.getSpecialiteService();
        Call<List<Specialite>> call=specialiteService.getSpecialitiesJson();

        call.enqueue(new Callback<List<Specialite>>() {
            @Override
            public void onResponse(Call<List<Specialite>> call, Response<List<Specialite>> response) {
                list=new ArrayList<>(response.body());
                adapter=new SpecialitesAdapter(SpecialitesActivity.this,list,SpecialitesActivity.this);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(SpecialitesActivity.this,2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapter);
                Toast.makeText(SpecialitesActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Specialite>> call, Throwable t) {
                Toast.makeText(SpecialitesActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSpecialiteClick(int position) {

        Intent intent=new Intent(this,MedecinActivity.class);
        Specialite specialite=list.get(position);
        intent.putExtra("specialite",specialite.getIdSpecialite());
        startActivity(intent);
    }

}