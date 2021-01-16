package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class PatientSignupActivity extends AppCompatActivity {

    GlobalDbHelper db;

    @BindView(R.id.genre)
    Spinner genreSpinner;
    @BindView(R.id.assurance)
    Spinner AssuranceSpinner;
    @BindView(R.id.ville)
    Spinner VillesSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);
        ButterKnife.bind(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.YesNo_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AssuranceSpinner.setAdapter(adapter1);

        db=new GlobalDbHelper(this);
        ArrayList<String> listVilles=db.getAllVilles();
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listVilles);
        VillesSpinner.setAdapter(adapter2);

    }


    //@OnItemSelected()


}
