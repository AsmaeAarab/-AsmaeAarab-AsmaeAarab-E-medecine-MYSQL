package com.example.e_medecine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.e_medecine.R;
import com.example.e_medecine.adapter.MedecinAdapter;
import com.example.e_medecine.model.Medecin;
import com.example.e_medecine.model.User;
import com.example.e_medecine.sqliteBd.GlobalDbHelper;
import com.example.e_medecine.sqliteBd.MedecinTable;
import com.example.e_medecine.sqliteBd.UserTable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MedecinActivity extends AppCompatActivity implements  MedecinAdapter.OnMedecinListener {

    GlobalDbHelper db=new GlobalDbHelper(this);
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
        //db.deleteUsers();
        //insertUsers();
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
        Intent intent=new Intent(this,MedecinDetailleActivity.class);
        Medecin medecin=list.get(position);
        Bundle ex = getIntent().getExtras();
        String lo = new String(ex.getString("mail"));
        intent.putExtra("patientEmail",lo);
        intent.putExtra("idMedecin",medecin.getIdMedecin());
        intent.putExtra("nomMedecin",medecin.getNomMedecin());
        intent.putExtra("prenomMedecin",medecin.getPrenomMedecin());
        intent.putExtra("specialiteMedecin",medecin.getSpecialite());
        intent.putExtra("experienceMedecin",medecin.getExperience());
        intent.putExtra("fraisMedecin",medecin.getFrais());
        intent.putExtra("imageMedecin",medecin.getImageMedecin());
        startActivity(intent);
    }
    public static byte[] imageViewToByte(int imageId, Context context) {

        Drawable drawable = context.getResources().getDrawable(imageId);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    /*
    public void insertUsers(){
        db.addUsers(new User( "Lahlou","Najib","homme","0522277997",imageViewToByte(R.drawable.lahlou,this),4,"lahlou@gmail.com","123","Docteur"));
        db.addUsers(new User( "EL Hachimi","Kawtar","femme","0522023933",imageViewToByte(R.drawable.hachimi,this),4,"hachimi@gmail.com","123","Docteur"));
        db.addUsers(new User( "Babou","Balkis","femme","0522567656",imageViewToByte(R.drawable.doctor,this),4,"babou@gmail.com","123","Docteur"));
        db.addUsers(new User( "Askour","Meryem","femme","0522890707",imageViewToByte(R.drawable.doctor,this),4,"askour@gmail.com","123","Docteur"));
        db.addUsers(new User( "Oudghiri","Idrissi","homme","0537714326",imageViewToByte(R.drawable.doctor,this),252,"oudghiri@gmail.com","123","Docteur"));
        db.addUsers(new User( "Alaoui","Siham","femme","0522233530",imageViewToByte(R.drawable.doctor,this),4,"alaoui@gmail.com","123","Docteur"));
        db.addUsers(new User( "Sadeq","Imane","femme","0522972455",imageViewToByte(R.drawable.doctor,this),4,"sadeq@gmail.com","123","Docteur"));
        db.addUsers(new User( "Belahcen","Driss","homme","0522554049",imageViewToByte(R.drawable.doctor,this),4,"hachimi@gmail.com","123","Docteur"));
        db.addUsers(new User( "Mazzouz","Hanane","femme","0522522526",imageViewToByte(R.drawable.doctor,this),4,"mazzouz@gmail.com","123","Docteur"));
        db.addUsers(new User( "Mikou","Reda","homme","0522471594",imageViewToByte(R.drawable.doctor,this),4,"mikou@gmail.com","123","Docteur"));
        db.addUsers(new User( "Mahassin","Fatima","femme","0537670771",imageViewToByte(R.drawable.doctor,this),252,"mahassin@gmail.com","123","Docteur"));
        db.addUsers(new User( "Loukilli","Asmae","femme","0522232212",imageViewToByte(R.drawable.doctor,this),4,"loukili@gmail.com","123","Docteur"));
        db.addUsers(new User( "El Meknassi","Abdelmjid","Homme","0522208719",imageViewToByte(R.drawable.doctor,this),4,"meknassi@gmail.com","123","Docteur"));
        db.addUsers(new User( "Yakine","saloua","femme","0523302944",imageViewToByte(R.drawable.doctor,this),6,"yakine@gmail.com","123","Docteur"));
        db.addUsers(new User( "Zahraoui","Majida","homme","0522861408",imageViewToByte(R.drawable.doctor,this),4,"zahraoui@gmail.com","123","Docteur"));
        db.addUsers(new User( "Heikel","Jaafar","homme","0522369679",imageViewToByte(R.drawable.doctor,this),4,"heikel@gmail.com","123","Docteur"));
        db.addUsers(new User( "Chourkani","Najat","femme","0522784477",imageViewToByte(R.drawable.doctor,this),4,"ckourkani@gmail.com","123","Docteur"));
        db.addUsers(new User( "Lytim","Safaa","femme","0539340279",imageViewToByte(R.drawable.doctor,this),345,"lytim@gmail.com","123","Docteur"));
        db.addUsers(new User( "Aït M'hamed","Laila","femme","0522393904",imageViewToByte(R.drawable.doctor,this),4,"hamed@gmail.com","123","Docteur"));
        db.addUsers(new User( "Bennis","Mohammed","homme","0522221455",imageViewToByte(R.drawable.doctor,this),4,"bennis@gmail.com","123","Docteur"));
        db.addUsers(new User( "Reda","Anouar","homme","0522860465",imageViewToByte(R.drawable.doctor,this),4,"reda@gmail.com","123","Docteur"));
        db.addUsers(new User( "Touzani","Mohamed Ali","homme","0537280038",imageViewToByte(R.drawable.doctor,this),252,"touzani@gmail.com","123","Docteur"));
    }

     */
}