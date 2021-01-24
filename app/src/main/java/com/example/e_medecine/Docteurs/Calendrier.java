package com.example.e_medecine.Docteurs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_medecine.R;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Calendrier extends AppCompatActivity {
    private ImageView imgPatient;
    private TextView Name,Lastname,DateAc,DateN;
    private Button ModCal,ButCal;
    private DatePickerDialog.OnDateSetListener setListener;
    private String date = "";
    private GlobalDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrier);
        initPa();
        db = new GlobalDbHelper(this);
        Bundle extrax = getIntent().getExtras();
        int idUser = extrax.getInt("IDUser");
        int idPatient = extrax.getInt("IDPatient");
        int idMedecin = extrax.getInt("IDMedecin");
        System.out.println("Medecin"+ idMedecin);
        byte[] PhotoP = extrax.getByteArray("Photo");
        String NamePatient = new String(extrax.getString("NomPatient"));
        String PrenomPatient = new String(extrax.getString("PrenomPatient"));
        String DateActuel = new String(extrax.getString("DateActuelle"));
        Bitmap bif = BitmapFactory.decodeByteArray(PhotoP,0,PhotoP.length);
        imgPatient.setImageBitmap(bif);
        Name.setText(NamePatient);
        Lastname.setText(PrenomPatient);
        DateAc.setText(DateActuel);
        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("DocteurNotif","NotifDocteur",NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        ButCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Calendrier.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,day,month,year);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date = day + "/" + month + "/" + year;
                DateN.setText(date);
            }
        };
        ModCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Date d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                    db.updateCalendrier(date,idPatient,idMedecin);
                    Toast.makeText(Calendrier.this, "Calendrier Modifier", Toast.LENGTH_SHORT).show();
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Calendrier.this,"DocteurNotif");
                    builder.setContentTitle(NamePatient);
                    builder.setContentText("Le Medecin a changer le rendez-vous en '"+date+"' ");
                    builder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
                    builder.setAutoCancel(true);
                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Calendrier.this);
                    managerCompat.notify(idUser,builder.build());
                    finish();
                    Toast.makeText(Calendrier.this, "Balayer vers le bas pour actualiser", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        });
    }
    public void initPa()
    {
        imgPatient = (ImageView) findViewById(R.id.imagePatientx);
        Name = (TextView) findViewById(R.id.textViewName);
        Lastname = (TextView) findViewById(R.id.textViewPrename);
        DateAc = (TextView) findViewById(R.id.textViewDateA);
        DateN = (TextView) findViewById(R.id.textViewDateN);
        ButCal = (Button) findViewById(R.id.buttonCalendar);
        ModCal = (Button) findViewById(R.id.ModifierCalendrier);
    }
}