package com.example.e_medecine.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.e_medecine.DatePickerFragment;
import com.example.e_medecine.R;
import com.example.e_medecine.model.Medecin;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;
import com.example.e_medecine.utilities.DateValidatorWeekdays;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MedecinDetailleActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    GlobalDbHelper db=new GlobalDbHelper(this);
    @BindView(R.id.image_medecin)
    CircleImageView imageMedecin;
    @BindView(R.id.nom_medecin)
    TextView nomMedecin;
    @BindView(R.id.prenom_medecin)
    TextView prenomMedecin;
    @BindView(R.id.specialite_medecin)
    TextView specialiteMedecin;
    @BindView(R.id.experience_medecin)
    TextView experienceMedecin;
    @BindView(R.id.frais_medecin)
    TextView fraisMedecin;
    @BindView(R.id.tele_medecin)
    TextView teleMedecin;
    @BindView(R.id.location_medecin)
    TextView locationMedecin;
    Medecin medecin=new Medecin();

    @BindView(R.id.appeler)
    Button btnAppeler;

    @BindView(R.id.rendezVous)
    Button btn_rendezVous;

    @BindView(R.id.paiment)
    Button btn_paiment;

    Integer id;
    int idPatient;
    String specialite;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecin_detaille);
        ButterKnife.bind(this);
        SharedPreferences spDoc= getSharedPreferences("PrefDoc",MODE_PRIVATE);
        String typeDoc = spDoc.getString ("pref_typeDoc","valeur par défaut");
        if(typeDoc.equals("Docteur")){
            btn_rendezVous.setText("Prendre rendez-vous");
        }
        else if(typeDoc.equals("E-Docteur")){
            btn_rendezVous.setText("Consultation");
            btn_paiment.setVisibility(View.VISIBLE);
        }
        Bundle extras=getIntent().getExtras();
        id=new Integer(extras.getInt("idMedecin"));

        SharedPreferences sp= getSharedPreferences("PrefsFile",MODE_PRIVATE);
        String login = sp.getString ("pref_name","valeur par défaut");
        idPatient=db.getIdPatient(login);


        String nom= extras.getString("nomMedecin");
        String prenom=extras.getString("prenomMedecin");
        specialite=extras.getString("specialiteMedecin");
        Integer experience= getIntent().getExtras().getInt("experienceMedecin");
        byte[] image=extras.getByteArray("imageMedecin");
        Integer frais=getIntent().getExtras().getInt("fraisMedecin");
        String tele=db.getMedecinTele(id);
        String  location=db.getMedecinLocation(id);


        nomMedecin.setText("Dr."+nom);
        prenomMedecin.setText(prenom);
        specialiteMedecin.setText(specialite);
        experienceMedecin.setText(String.valueOf(experience)+"ans");
        fraisMedecin.setText(String.valueOf(frais)+"DH");
        teleMedecin.setText(String.valueOf(tele));
        locationMedecin.setText(location);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
        imageMedecin.setImageBitmap(bitmap);

    btn_rendezVous.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment datePicker=new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(),"date picker");

        }
    });

    }
    @OnClick(R.id.appeler)
    public void appler(View view) {
        String tele=db.getMedecinTele(id);
        long tel = Long.parseLong(tele);
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+tel));
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String rdv_date= simpleDateFormat.format(calendar.getTime());
        db.addRendezVous(specialite,rdv_date,idPatient, id);

    }
}