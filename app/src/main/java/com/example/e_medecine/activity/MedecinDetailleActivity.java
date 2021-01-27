package com.example.e_medecine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.e_medecine.R;
import com.example.e_medecine.model.Medecin;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;
import com.example.e_medecine.utilities.DateValidatorWeekdays;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;


import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MedecinDetailleActivity extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecin_detaille);
        ButterKnife.bind(this);

        Bundle extras=getIntent().getExtras();
        id=new Integer(extras.getInt("idMedecin"));
        String nom= extras.getString("nomMedecin");
        String prenom=extras.getString("prenomMedecin");
        String specialite=extras.getString("specialiteMedecin");
        Integer experience= getIntent().getExtras().getInt("experienceMedecin");
        Integer image=getIntent().getExtras().getInt("imageMedecin");
        Integer frais=getIntent().getExtras().getInt("fraisMedecin");
        String tele=db.getMedecinTele(id);
        String  location=db.getMedecinLocation(id);


        nomMedecin.setText(nom);
        prenomMedecin.setText(prenom);
        specialiteMedecin.setText(specialite);
        experienceMedecin.setText(String.valueOf(experience)+"yrs");
        fraisMedecin.setText(String.valueOf(frais)+"DH");
        teleMedecin.setText(String.valueOf(tele));
        locationMedecin.setText(location);
        byte[] medecin_img=db.getMedecinImage(id).getBytes();
        Bitmap bitmap = BitmapFactory.decodeByteArray(medecin_img, 0, medecin_img.length);
        imageMedecin.setImageBitmap(bitmap);

        Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("UTC+01:00"));
        calendar.clear();

        Long today=MaterialDatePicker.todayInUtcMilliseconds();
        calendar.setTimeInMillis(today);


        //CalendarConstraints
        CalendarConstraints.Builder constraintBuilder=new CalendarConstraints.Builder();
        constraintBuilder.setValidator(new DateValidatorWeekdays());
        constraintBuilder.setStart(today);

        //Material date picker
        MaterialDatePicker.Builder builder=MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT A DATE");
        builder.setSelection(today);
        builder.setCalendarConstraints(constraintBuilder.build());
        MaterialDatePicker materialDatePicker=builder.build();

       btn_rendezVous.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");
           }
       });

       materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
           @Override
           public void onPositiveButtonClick(Object selection) {
               String date=materialDatePicker.getHeaderText();
               //db.addRendezVous(specialite,date,idpatient,id);
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

}