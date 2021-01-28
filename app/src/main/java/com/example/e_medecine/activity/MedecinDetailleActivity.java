package com.example.e_medecine.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
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
    Integer id;
    int idPatient;
    String specialite;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecin_detaille);
        ButterKnife.bind(this);

        Bundle extras=getIntent().getExtras();
        id=new Integer(extras.getInt("idMedecin"));

        String login= extras.getString("patientEmail");
        idPatient=db.getIdPatient(login);


        String nom= extras.getString("nomMedecin");
        String prenom=extras.getString("prenomMedecin");
        specialite=extras.getString("specialiteMedecin");
        Integer experience= getIntent().getExtras().getInt("experienceMedecin");
        Integer image=getIntent().getExtras().getInt("imageMedecin");
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
        byte[] medecin_img=db.getMedecinImage(id).getBytes();
        Bitmap bitmap = BitmapFactory.decodeByteArray(medecin_img, 0, medecin_img.length);
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
        String currentDateString= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE d MMMMM yyyy", Locale.ENGLISH);
        //LocalDate date = LocalDate.parse(currentDateString, formatter);
        //System.out.println("date: "+date);
        db.addRendezVous(specialite,currentDateString,idPatient, id);
        System.out.println("currentDate:  "+currentDateString);
    }
}