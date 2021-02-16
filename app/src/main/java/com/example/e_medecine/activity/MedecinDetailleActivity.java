package com.example.e_medecine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_medecine.ApiRest.Apis;
import com.example.e_medecine.ApiRest.PatientService;
import com.example.e_medecine.ApiRest.RendezVousService;
import com.example.e_medecine.DatePickerFragment;
import com.example.e_medecine.Docteurs.RendezVousActivity;
import com.example.e_medecine.R;
import com.example.e_medecine.model.Patient;
import com.example.e_medecine.model.RDV;
import com.example.e_medecine.model.Users;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedecinDetailleActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    GlobalDbHelper db = new GlobalDbHelper(this);
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

    @BindView(R.id.appeler)
    Button btnAppeler;

    @BindView(R.id.rendezVous)
    Button btn_rendezVous;

    @BindView(R.id.paiment)
    Button btn_paiment;

    Integer idMedecin;
    String specialite;
    private String typeDoc = "";
    String tele;
    RDV rdv=new RDV();;
    Patient p;
    String login;
    private SharedPreferences prefs;
    private  static final String PREFS_TYPE="PrefPatient";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecin_detaille);
        ButterKnife.bind(this);
        SharedPreferences spDoc = getSharedPreferences("PrefDoc", MODE_PRIVATE);
        typeDoc = spDoc.getString("pref_typeDoc", "valeur par défaut");
        if (typeDoc.equals("Docteur")) {
            btn_rendezVous.setText("Prendre rendez-vous");
        } else if (typeDoc.equals("E-Docteur")) {
            btn_rendezVous.setText("Consultation");
            btn_paiment.setVisibility(View.VISIBLE);
        }

        Bundle extras = getIntent().getExtras();
        idMedecin = new Integer(extras.getInt("idMedecin"));
        String nom = extras.getString("nomMedecin");
        String prenom = extras.getString("prenomMedecin");
        specialite = extras.getString("specialiteMedecin");
        Integer experience = getIntent().getExtras().getInt("experienceMedecin");
        Integer frais = getIntent().getExtras().getInt("fraisMedecin");
        tele = extras.getString("tele");
        String location = extras.getString("location");
        SharedPreferences pref = getSharedPreferences("PrefDoc", MODE_PRIVATE);
        String image = pref.getString("pref_ImgDoc", "valeur par défaut");
        byte[] byteArray = Base64.decode(String.valueOf(image), Base64.DEFAULT);
        Bitmap bmp1 = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        nomMedecin.setText("Dr." + nom);
        prenomMedecin.setText(prenom);
        specialiteMedecin.setText(specialite);
        experienceMedecin.setText(String.valueOf(experience) + "ans");
        fraisMedecin.setText(String.valueOf(frais) + "DH");
        teleMedecin.setText(String.valueOf(tele));
        locationMedecin.setText(location);
        imageMedecin.setImageBitmap(bmp1);


        SharedPreferences sp = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        login = sp.getString("pref_name", "valeur par défaut");
        btn_rendezVous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeDoc.equals("Docteur")) {
                    DialogFragment datePicker = new DatePickerFragment();
                    datePicker.show(getSupportFragmentManager(), "date picker");
                }
            }
        });
        prefs=getSharedPreferences(PREFS_TYPE,MODE_PRIVATE);
    }

    @OnClick(R.id.appeler)
    public void appler(View view) {
        long tel = Long.parseLong(tele);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + tel));
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String rdv_date = simpleDateFormat.format(calendar.getTime());
        //db.addRendezVous(specialite, rdv_date, idPatient, idMedecin);
        rdv.setDateRDV(rdv_date);
        rdv.setTitreRDV(specialite);
        com.example.e_medecine.model.Medecin m = new com.example.e_medecine.model.Medecin(idMedecin);
        rdv.setIdMedecin(m);
        Toast.makeText(MedecinDetailleActivity.this, "Mes Rendez-vous", Toast.LENGTH_SHORT).show();
        getIdPatientByEmail(login);
        getIdPatient(login);
    }
    int idP;
    public void getIdPatientByEmail(String emailUser) {
        PatientService service = Apis.getPatientsService();
        Call<List<Patient>> call = service.getIdPatientByEmail(emailUser);
        call.enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                List<Patient> pList = response.body();
                for (Patient p : pList) {
                    idP = p.getIdPatient();
                }
                p=new Patient(idP);
                rdv.setIdPatient(p);
                addRendezVous(rdv);
            }
            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed ", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }
    public void addRendezVous(RDV rdv) {
        RendezVousService rdvService = Apis.getRDVService();
        Call<RDV> call = rdvService.addRDV(rdv);
        call.enqueue(new Callback<RDV>() {
            @Override
            public void onResponse(Call<RDV> call, Response<RDV> response) {
                Toast.makeText(getApplicationContext(), "Ajout avec succès", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<RDV> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "NoADD ", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    int idX;
    public int getIdPatient(String emailUser){
        PatientService service= Apis.getPatientsService();
        Call<List<Users>>call = service.getIdPatient(emailUser);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>>call, Response<List<Users>>response) {
                List<Users>uList=response.body();
                for(Users u : uList ){
                    idX=u.getIdUser();
                }
                Intent ir = new Intent(MedecinDetailleActivity.this, RendezVousActivity.class);
                ir.putExtra("Id", idX);/////iduser
                ir.putExtra("ADDRESSE",login);
                ir.putExtra("role","patient");/////adresse user
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("Id", idX);
                editor.putString("ADDRESSE", login);
                editor.putString("role", "patient");
                editor.apply();
                startActivity(ir);
                Toast.makeText(getApplicationContext(), "getid Yes "+idX, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Noooo"+idX, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Log.e("Error:",t.getMessage());
            }
        });
        return idX;
    }

}