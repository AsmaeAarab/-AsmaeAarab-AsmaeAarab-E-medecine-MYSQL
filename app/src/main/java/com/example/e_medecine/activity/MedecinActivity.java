package com.example.e_medecine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_medecine.R;
import com.example.e_medecine.adapter.MedecinAdapter;
import com.example.e_medecine.adapter.SpecialitesAdapter;
import com.example.e_medecine.model.Medecin;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedecinActivity extends AppCompatActivity implements  MedecinAdapter.OnMedecinListener {

    GlobalDbHelper db=new GlobalDbHelper(this);;
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
        list=new ArrayList<>(db.getMedecins(id));
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
        /*
        Intent intent=new Intent(this,MenuActivity.class);
        Restaurant restaurant=list.get(position);
        intent.putExtra("id_menu_resto",restaurant.getId_restaurant());
        startActivity(intent);

         */
        Toast.makeText(this, "This is my Toast message!",Toast.LENGTH_LONG).show();
    }
}