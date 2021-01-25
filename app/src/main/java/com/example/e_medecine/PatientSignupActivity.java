package com.example.e_medecine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;

public class PatientSignupActivity extends AppCompatActivity {

    GlobalDbHelper db;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    @BindView(R.id.genre)
    Spinner genreSpinner;
    @BindView(R.id.assurance)
    Spinner assuranceSpinner;
    @BindView(R.id.ville)
    Spinner villeSpinner;

    @BindView(R.id.prenom)
    EditText editTextPrenom;
    @BindView(R.id.nom)
    EditText editTextNom;
    @BindView(R.id.email)
    EditText editTextEmail;
    @BindView(R.id.mdp)
    EditText editTextMdp;
    @BindView(R.id.confirmMDP)
    EditText editTextConfirmMdp;
    @BindView(R.id.phone)
    EditText editTextPhone;
    @BindView(R.id.age)
    EditText editTextAge;
    @BindView(R.id.adresse)
    EditText editTextAdresse;

    @BindView(R.id.signUp)
    Button buttonSignup;



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
        assuranceSpinner.setAdapter(adapter1);

        db=new GlobalDbHelper(this);
        ArrayList<String> listVilles=db.getAllVilles();
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listVilles);
        villeSpinner.setAdapter(adapter2);

    }

    private boolean validateEmail(){

        String emailInput=editTextEmail.getText().toString().trim();
        if(emailInput.isEmpty()){
            editTextEmail.setError("Field can't be empty");
            return false;}
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            editTextEmail.setError("Please enter a valid email address");
            return false;
        }
        else {
            editTextEmail.setError(null);
            return true;
        }

    }
    @OnTextChanged({R.id.email,R.id.mdp})
    public void ValidationFields(){

        String emailInput=editTextEmail.getText().toString().trim();
        if(emailInput.isEmpty()){
            editTextEmail.setError("Field can't be empty");
           }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            editTextEmail.setError("Please enter a valid email address");
            buttonSignup.setEnabled(false);
        }
        else {
            editTextEmail.setError(null);
        }

        String passwordInput = editTextMdp.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            editTextMdp.setError("Field can't be empty");
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            editTextMdp.setError("Password too weak");
        } else {
            editTextMdp.setError(null);
        }

    }

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    @OnClick(R.id.signUp)
    public void signUp(){
       String genre= genreSpinner.getSelectedItem().toString();
        String assurance= assuranceSpinner.getSelectedItem().toString();
        String ville= villeSpinner.getSelectedItem().toString();
        String prenom=editTextPrenom.getText().toString();
        String nom=editTextNom.getText().toString();
        String email=editTextEmail.getText().toString();
        String mdp=editTextMdp.getText().toString();
        String confirmMdp=editTextConfirmMdp.getText().toString();
        String phone=editTextPhone.getText().toString();
        String age=editTextAge.getText().toString();
        String adresse=editTextAdresse.getText().toString();

        db = new GlobalDbHelper(this);

        //sqLiteDatabase=db.getReadableDatabase();
        sqLiteDatabase=db.getWritableDatabase();

        //if(genre.equals("")||assurance.equals("")||ville.equals("")||prenom.equals("")||nom.equals("")||email.equals("")
               // ||mdp.equals("")||confirmMdp.equals("")||phone.equals("")||age.equals("")||adresse.equals(""))
        if(ville.equals("")||prenom.equals("")||email.equals("")
                ||mdp.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Fiels are empty",Toast.LENGTH_SHORT).show();
        }
        else {
            Integer Idville=db.getIdVille(ville);
            Boolean checkEmail = db.checkEmail(email);
           // Toast.makeText(getApplicationContext(),Idville+" :id",Toast.LENGTH_SHORT).show();
            if (checkEmail == true) {
                try {
                 sqLiteDatabase.beginTransaction(); ///////////////
                Boolean insert = db.insertUser(nom, prenom, genre, phone, Idville, email, mdp, "patient");
                int idUser=db.getIdUser(email);
                 Boolean insert2 = db.insertPatient(idUser, age, adresse, assurance);
                //|| insert2 ==true
                if (insert == true && insert2 ==true) {
                     sqLiteDatabase.setTransactionSuccessful(); ////////////
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                }
                } finally{
                    sqLiteDatabase.endTransaction();
                    db.close();
                }
            }
            else { Toast.makeText(getApplicationContext(),"Email already exists",Toast.LENGTH_SHORT).show();}
        }
    }


    @OnClick(R.id.age)
    public void onclickage(){
        Calendar c= Calendar.getInstance();
        int day=c.get(Calendar.DAY_OF_MONTH);
        int month=c.get(Calendar.MONTH);
        int year=c.get(Calendar.YEAR);

        DatePickerDialog dpd=new DatePickerDialog(PatientSignupActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                editTextAge.setText(mDay+"/"+(mMonth+1)+"/"+mYear) ;

            }
        },day,month,year);
        dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dpd.show();
    }

}
