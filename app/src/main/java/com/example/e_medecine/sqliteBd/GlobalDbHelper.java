package com.example.e_medecine.sqliteBd;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.database.sqlite.SQLiteStatement;
        import android.graphics.Bitmap;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.drawable.Drawable;
        import android.util.Log;

        import com.example.e_medecine.model.Medecin;
        import com.example.e_medecine.R;
        import com.example.e_medecine.model.Specialite;
        import com.example.e_medecine.model.User;

        import java.io.BufferedReader;
        import java.io.ByteArrayOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;

        import static android.database.DatabaseUtils.stringForQuery;

public class GlobalDbHelper extends SQLiteOpenHelper {
    public Context context;
    public static final int Db_Version = 14;
    public static final String TAG = "Database Emedecine";
    public static final String Db_Name = "E_medecine.db";


    public GlobalDbHelper(Context context) {
        super(context, Db_Name, null, Db_Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(VilleTable.CreateTable());
        db.execSQL(SpecialiteTable.CREATE_TABLE());
        db.execSQL(UserTable.CREATE_TABLE());
        db.execSQL(PatientTable.CREATE_TABLE());
        db.execSQL(MedecinTable.CREATE_TABLE());
        db.execSQL(RDVTable.CREATE_TABLE());
        db.execSQL(ConsultationTable.CreateTable());
        db.execSQL(PaiementTable.CREATE_TABLE());
        //insertSpecialites(db);
        try {
            insertFromFile(context, R.raw.villes,db);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //try {
       //     insertFromFile(context, R.raw.medecin,db);
       // } catch (IOException e) {
       //     e.printStackTrace();
       // }
        Log.d(TAG, "database created" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+RDVTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+ConsultationTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+PaiementTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+PatientTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+MedecinTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+UserTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+VilleTable.getTableName());
            db.execSQL("DROP TABLE IF EXISTS "+SpecialiteTable.getTableName());
            onCreate(db);
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public void insertdocteur(ContentValues values)
    {
        getWritableDatabase().insert("medecins",null,values);
    }
    public void insertville(ContentValues values)
    {
        getWritableDatabase().insert("villes",null,values);
    }
    public void insertspecialite(ContentValues values)
    {
        getWritableDatabase().insert("specialites",null,values);
    }
    public boolean isEmailvalid(String email , String password,String Docteur)
    {
        String sql = "select count(*) from users where emailUser='"+email+"' and passwordUser='"+password+"' and roleUser = '"+Docteur+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        Long l = statement.simpleQueryForLong();
        statement.close();
        if (l==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public boolean updateusingmail(String pass,String login)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET passwordUser = "+"'"+pass+"' "+ "WHERE emailUser = "+"'"+login+"'");
        return true;
    }
    public boolean updateusingphone(String pass,String login)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET passwordUser = "+"'"+pass+"' "+ "WHERE telephoneUser = "+"'"+login+"'");
        return true;
    }
    public boolean updatenom(String nom,int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET nomUser = "+" '" +nom+ "' "+" WHERE idUser = "+" '"+id+"'  ");
        return true;
    }
    public boolean updateprenom(String prenom,int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET prenomUser = "+" '" +prenom+ "' "+" WHERE idUser = "+" '"+id+"'  ");
        return true;
    }
    public boolean updatemail(String mail,int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET emailUser = "+" '" +mail+ "' "+" WHERE idUser = "+" '"+id+"'  ");
        return true;
    }
    public boolean updatephone(String phone,int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET telephoneUser = "+" '" +phone+ "' "+" WHERE idUser = "+" '"+id+"'  ");
        return true;
    }
    public boolean updateCalendrier(String dateP,int idp,int idm)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE RDVs SET dateRDV = "+" '"+dateP+"' "+" WHERE idPatient = "+" '"+idp+"' "+" AND idMedecin = "+" '"+idm+"' "+" ");
        return true;
    }
    public boolean updateImage(byte[] image,int id)
    {
        ContentValues values = new ContentValues();
        String i = String.valueOf(id);
        values.put("imageUser",image);
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("UPDATE users SET imageUser = "+" '" +values+ "' "+" WHERE idUser = "+" '"+id+"'  ");
        db.update("users",values,"idUser = ?",new String[]{i});
        return true;
    }
    public boolean isTelephonevalid(String Phone , String password, String Docteur)
    {
        String sql = "select count(*) from users where telephoneUser='"+Phone+"' and passwordUser='"+password+"' and roleUser = '"+Docteur+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        Long d = statement.simpleQueryForLong();
        statement.close();
        if (d==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public void querydata(String sql)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor getData(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    public Cursor getdataRendezvous(int id)
    {
        String sql = "SELECT u.idUser,p.idPatient,R.idMedecin,u.imageUser,u.nomUser,u.prenomUser,R.titreRDV,R.dateRDV" +
                " FROM users AS u, patients AS p, RDVs AS R" +
                " WHERE u.idUser = p.idUser AND p.idPatient = R.idPatient AND R.idMedecin = '"+id+"'" ;
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    public static int insertFromFile(Context context, int resourceId, SQLiteDatabase db) throws IOException {
        // Reseting Counter
        int result = 0;

        // Open the resource
        InputStream insertsStream = context.getResources().openRawResource(resourceId);
        BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

        // Iterate through lines (assuming each insert has its own line and theres no other stuff)
        while (insertReader.ready()) {
            String insertStmt = insertReader.readLine();
            db.execSQL(insertStmt);
            result++;
        }
        insertReader.close();
        // returning number of inserted rows
        return result;
    }

    public ArrayList<String> getAllVilles() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            list.add(0,"Choose a city");
            String selectQuery = "SELECT * FROM villes" ;
                    //+ VilleTable.getTableName()
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String ville = cursor.getString(cursor.getColumnIndex("label"));
                    list.add(ville);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){e.printStackTrace();}
        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }
    public ArrayList<String> getAllSpecialites() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            list.add(0,"Choose speciality");
            String selectQuery = "SELECT * FROM specialites" ;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String specialite = cursor.getString(cursor.getColumnIndex("label"));
                    list.add(specialite);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){e.printStackTrace();}
        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }

    public boolean insertUser(byte[] imageUser,String nomUser,String prenomUser ,String genreUser,String telephoneUser,
                               int idVille ,String emailUser ,String passwordUser,String roleUser)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("imageUser",imageUser);
        contentValues.put("nomUser",nomUser);
        contentValues.put("prenomUser",prenomUser);
        contentValues.put("genreUser",genreUser);
        contentValues.put("telephoneUser",telephoneUser);
        contentValues.put("idVille",idVille);
        contentValues.put("emailUser",emailUser);
        contentValues.put("passwordUser",passwordUser);
        contentValues.put("roleUser",roleUser);

        long ins=db.insert("users",null,contentValues);
        if(ins==-1) return  false;
        else return true;
    }
    public boolean updateUser(byte[] imageUser,String nomUser,String prenomUser ,String telephoneUser,
                         String emailUser){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("imageUser",imageUser);
        contentValues.put("nomUser",nomUser);
        contentValues.put("prenomUser",prenomUser);
        contentValues.put("telephoneUser",telephoneUser);
        //contentValues.put("emailUser",emailUser);
        //contentValues.put("passwordUser",passwordUser);
        long ins=db.update("users", contentValues, "emailUser=?" ,new String[]{emailUser});
        if(ins==-1) return  false;
        else return true;
    }

    public void updatePatient(int idUser,String agePatient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("agePatient",agePatient);
        db.update("patients", contentValues, "idUser=?", new String[]{Integer.toString(idUser)});
    }

    public Boolean checkEmail(String emailUser){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where emailUser=?",new String[]{emailUser});
        if(cursor.getCount()>0) return false;
        else return true;
    }

//String labelVille
    public Integer getIdVille(String labelVille){
        Integer idville=0;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select idVille from villes where label=?",new String[]{labelVille});
        while (cursor.moveToNext()) {
             idville = cursor.getInt(0);
        }
        return idville;
    }
    public Integer getIdSpecialite(String labelspecialite)
    {
        Integer idspec = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT idSpecialite FROM specialites WHERE label = '"+labelspecialite+"' ",null);
        while (cursor.moveToNext())
        {
            idspec = cursor.getInt(0);
        }
        return idspec;
    }
    public Integer getIconSpecialite(String labelspecialite)
    {
        Integer idicon = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT imageSpecialite FROM specialites WHERE label = '"+labelspecialite+"' ",null);
        while (cursor.moveToNext())
        {
            idicon = cursor.getInt(0);
        }
        return idicon;
    }
    public boolean insertMedecin(int iduser,int idspecialite,String typedoc,String Locate,String charte,int frais,int experience)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valuesD = new ContentValues();
        valuesD.put("idUser", iduser);
        valuesD.put("idSpecialite", idspecialite);
        valuesD.put("typeMedecin", typedoc);
        valuesD.put("localisationMedecin", Locate);
        valuesD.put("TermeCondition", charte);
        valuesD.put("frais",frais);
        valuesD.put("experience",experience);
        long insert  = db.insert("medecins",null,valuesD);
        if (insert == -1)
        {
            return false;
        }
        return true;
    }
    public boolean insertPatient(int idUser,String agePatient,String Adresse ,String cnssPatient )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("idUser",idUser);
        contentValues.put("agePatient",agePatient);
        contentValues.put("Adresse",Adresse);
        contentValues.put("cnssPatient",cnssPatient);

        long ins=db.insert("patients",null,contentValues);
        if(ins==-1) return  false;
        else return true;
    }


    public int getIdUser(String email){
        int idUser=0;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select idUser  from users where emailUser=? ",new String[]{email});
        //int idUser = cursor.getInt(cursor.getColumnIndex("idUser"));
        while (cursor.moveToNext()) {
            idUser = cursor.getInt(0);
        }
        return idUser;
    }
    public int getIdUserMailPhone(String mailphone){
        int idUser=0;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT idUser  FROM users WHERE emailUser = '"+mailphone+"' OR telephoneUser = '"+mailphone+"'",null);
        //int idUser = cursor.getInt(cursor.getColumnIndex("idUser"));
        while (cursor.moveToNext()) {
            idUser = cursor.getInt(0);
        }
        return idUser;
    }
    public String getNomUser(String mailphone)
    {
        String nomU = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nomUser FROM users WHERE emailUser = '"+mailphone+"' OR telephoneUser = '"+mailphone+"'",null);
        while (cursor.moveToNext())
        {
            nomU = cursor.getString(0);
        }
        return nomU;
    }
    public String getPrenomUser(String mailphone)
    {
        String PrenomU = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT prenomUser FROM users WHERE emailUser = '"+mailphone+"' OR telephoneUser = '"+mailphone+"'",null);
        while (cursor.moveToNext())
        {
            PrenomU = cursor.getString(0);
        }
        return PrenomU;
    }
    public String getPhoneUser(String mailphone)
    {
        String PhoneU = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT telephoneUser FROM users WHERE emailUser = '"+mailphone+"' OR telephoneUser = '"+mailphone+"'",null);
        while (cursor.moveToNext())
        {
            PhoneU = cursor.getString(0);
        }
        return PhoneU;
    }
    public byte[] getImageUser(String mailphone)
    {
        byte[] ImageU = new byte[0];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT imageUser FROM users WHERE emailUser = '"+mailphone+"' OR telephoneUser = '"+mailphone+"'",null);
        while (cursor.moveToNext())
        {
            ImageU = cursor.getBlob(0);
        }
        return ImageU;
    }
    public String getEmailUser(String mailphone)
    {
        String EmailU = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT emailUser FROM users WHERE emailUser = '"+mailphone+"' OR telephoneUser = '"+mailphone+"'",null);
        while (cursor.moveToNext())
        {
            EmailU = cursor.getString(0);
        }
        return EmailU;
    }
    String Role = "Patient";
    public int GetiduserRDV(int id)
    {
        int iduser = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT u.idUser" +
                " FROM users AS u, patients AS p, RDVs AS R" +
                " WHERE u.idUser = p.idUser AND p.idPatient = R.idPatient AND R.idMedecin = '"+id+"' AND roleUser = '"+Role+"'",null);
        while (cursor.moveToNext())
        {
            iduser = cursor.getInt(0);
        }
        return iduser;
    }
    public int GetidpatientRDV(int id)
    {
        int idpatient = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT p.idPatient" +
                " FROM users AS u, patients AS p, RDVs AS R" +
                " WHERE u.idUser = p.idUser AND p.idPatient = R.idPatient AND R.idMedecin = '"+id+"' AND roleUser = '"+Role+"'",null);
        while (cursor.moveToNext())
        {
            idpatient = cursor.getInt(0);
        }
        return idpatient;
    }
    public int GetidmedecinRDV(int id)
    {
        int idmedecin = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT R.idMedecin" +
                " FROM users AS u, patients AS p, RDVs AS R" +
                " WHERE u.idUser = p.idUser AND p.idPatient = R.idPatient AND R.idMedecin = '"+id+"' AND roleUser = '"+Role+"'",null);
        while (cursor.moveToNext())
        {
            idmedecin = cursor.getInt(0);
        }
        return idmedecin;
    }
    public byte[] GetImageRDV(int id)
    {
        byte[] imageUserRDV = new byte[0];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT u.imageUser" +
                " FROM users AS u, patients AS p, RDVs AS R" +
                " WHERE u.idUser = p.idUser AND p.idPatient = R.idPatient AND R.idMedecin = '"+id+"' AND roleUser = '"+Role+"'",null);
        while (cursor.moveToNext())
        {
            imageUserRDV = cursor.getBlob(0);
        }
        return imageUserRDV;
    }
    public String GetNomUserRDV(int id)
    {
        String NomUserRDv = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT u.nomUser" +
                " FROM users AS u, patients AS p, RDVs AS R" +
                " WHERE u.idUser = p.idUser AND p.idPatient = R.idPatient AND R.idMedecin = '"+id+"' AND roleUser = '"+Role+"'",null);
        while (cursor.moveToNext())
        {
            NomUserRDv = cursor.getString(0);
        }
        return NomUserRDv;
    }
    public String GetPrenomUserRDV(int id)
    {
        String PrenomUserRDV = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT u.prenomUser" +
                " FROM users AS u, patients AS p, RDVs AS R" +
                " WHERE u.idUser = p.idUser AND p.idPatient = R.idPatient AND R.idMedecin = '"+id+"' AND roleUser = '"+Role+"'",null);
        while (cursor.moveToNext())
        {
            PrenomUserRDV = cursor.getString(0);
        }
        return PrenomUserRDV;
    }
    public String GetTitrePatientRDV(int id)
    {
        String TitrePatientRDV = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT R.titreRDV" +
                " FROM users AS u, patients AS p, RDVs AS R" +
                " WHERE u.idUser = p.idUser AND p.idPatient = R.idPatient AND R.idMedecin = '"+id+"' AND roleUser = '"+Role+"'",null);
        while (cursor.moveToNext())
        {
            TitrePatientRDV = cursor.getString(0);
        }
        return TitrePatientRDV;
    }
    public String GetDatePatientRDV(int id)
    {
        String DatePatientRDV = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT R.dateRDV" +
                " FROM users AS u, patients AS p, RDVs AS R" +
                " WHERE u.idUser = p.idUser AND p.idPatient = R.idPatient AND R.idMedecin = '"+id+"' AND roleUser = '"+Role+"' ",null);
        while (cursor.moveToNext())
        {
            DatePatientRDV = cursor.getString(0);
        }
        return DatePatientRDV;
    }
    public Boolean loginpassword(String login,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users where emailUser=? and passwordUser=? and roleUser=?",new String[]{login,password,"patient"});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    public Boolean checkEmailPatient(String login)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users where emailUser=? and roleUser=?",new String[]{login,"patient"});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Specialities
   /* public ArrayList<Specialite> getSpecialites(){
        Log.d(TAG,"invoke read");
        ArrayList<Specialite> specialiteList=new ArrayList<Specialite>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(
                SpecialiteTable.getTableName(),
                null,
                null,
                null,
                null,
                null,
                null
        );
        if(cursor.moveToNext()){
            do{
                Specialite specialite=new Specialite();
                specialite.setId_specialite(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SpecialiteTable.getID()))));
                specialite.setLabe(cursor.getString(cursor.getColumnIndex(SpecialiteTable.getLABEL())));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(SpecialiteTable.getImageSpecialite()));
                specialite.setImageSpecialite(image);
                //specialite.setImageSpecialite(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SpecialiteTable.getImageSpecialite()))));
                specialiteList.add(specialite);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return specialiteList;
    }

    */
    /*
    public void insertSpecialites(SQLiteDatabase db){
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES( \"Allergologie\", "+R.drawable.allergic+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES( \"Cardiologie\", "+R.drawable.cardiologie+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES( \"Dermatologie\", "+R.drawable.dermatologie+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES( \"Gastro-entérologie\", "+R.drawable.stomach+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES( \"Ophtalmologie\", "+R.drawable.ophtalmologie+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES( \"Hématologie\", "+R.drawable.hematology+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES( \"Hépatologie\", "+R.drawable.hepatology+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES( \"Infectiologie\", "+R.drawable.stop+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES( \"Neurologie\", "+R.drawable.neurologie+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES( \"Pédiatrie\", "+R.drawable.pediatry+")");
        db.execSQL("INSERT INTO "+SpecialiteTable.getTableName()+" ("+SpecialiteTable.getLABEL()+","+SpecialiteTable.getImageSpecialite()+") VALUES( \"Psychiatrie\", "+R.drawable.psychiatrie+")");
    }

     */

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////Medecin
    public ArrayList<Medecin> getMedecins(int id,String type){
        Log.d(TAG,"invoke read");
        ArrayList<Medecin> medecinList=new ArrayList<Medecin>();
        String selectQuery="SELECT medecins.idMedecin, specialites.label,medecins.localisationMedecin,users.imageUser,medecins.frais, medecins.experience" +
                ", users.nomUser, users.prenomUser FROM "+MedecinTable.getTableName()+" INNER JOIN "+UserTable.getTableName()+" ON medecins.idUser=users.idUser "+
                "INNER JOIN "+SpecialiteTable.getTableName()+" ON medecins.idSpecialite=specialites.idSpecialite"+
                " WHERE medecins.idSpecialite = "+id+" AND medecins.typeMedecin = '"+type+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToNext()){
            do{
                Medecin medecin=new Medecin();
                medecin.setIdMedecin(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MedecinTable.getID()))));
                medecin.setSpecialite(cursor.getString(cursor.getColumnIndex(SpecialiteTable.getLABEL())));
                medecin.setLocation(cursor.getString(cursor.getColumnIndex(MedecinTable.getLOCALISATION())));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(UserTable.getIMAGE()));
                medecin.setImageMedecin(image);
                medecin.setFrais(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MedecinTable.getFRAIS()))));
                medecin.setExperience(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MedecinTable.getEXPERIENCE()))));
                medecin.setNomMedecin(cursor.getString(cursor.getColumnIndex(UserTable.getNOM())));
                medecin.setPrenomMedecin(cursor.getString(cursor.getColumnIndex(UserTable.getPRENOM())));
                medecinList.add(medecin);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return medecinList;
    }
    //get phone number
    public String getMedecinTele(int id){
        Log.d(TAG,"invoke read");
        SQLiteDatabase db=this.getReadableDatabase();
        String tele= stringForQuery(db, "SELECT telephoneUser FROM "+UserTable.getTableName()+" WHERE idUser = "+id, null);
        return tele;
    }
    //get location
    public String getMedecinLocation(int id){
        Log.d(TAG,"invoke read");
        SQLiteDatabase db=this.getReadableDatabase();
        String location= stringForQuery(db, "SELECT localisationMedecin FROM "+MedecinTable.getTableName()+" WHERE idUser = "+id, null);
        return location;
    }


    ///////////////////////////////////////////////////////////////////////RestApi
    public void addUsers(User user) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(UserTable.getNOM(), user.getNomUser());
        values.put(UserTable.getPRENOM(), user.getPrenomUser());
        values.put(UserTable.getGENRE(), user.getGenreUser());
        values.put(UserTable.getTELE(), user.getTelephoneUser());
        values.put(UserTable.getIMAGE(), user.getImageUser());
        values.put(UserTable.getVILLE(), user.getIdVille());
        values.put(UserTable.getEMAIL(), user.getEmailUser());
        values.put(UserTable.getPASSWORD(), user.getPasswordUser());
        values.put(UserTable.getROLE(), user.getRoleUser());
        db.insert(UserTable.getTableName(),null,values);
        db.close();
    }
    ///delete records
    public  void deleteUsers(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM " + UserTable.getTableName());
        db.execSQL("DELETE FROM sqlite_sequence WHERE name= '"+UserTable.getTableName()+"'");
        db.close();
    }

    /////////////////////////////////////////////////////////////////Rendez-vous
    public void addRendezVous(String titre, String date, int idPatient, int idMedecin) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(RDVTable.getTITRE(), titre);
        values.put(RDVTable.getDATE(), date);
        values.put(RDVTable.getIdPatient(), idPatient);
        values.put(RDVTable.getID_Medecin(), idMedecin);
        db.insert(RDVTable.getTableName(),null,values);
        System.out.println("insertRdv: ");
        db.close();
        System.out.println("close db: ");
    }

    public int getIdPatient(String email) {
        int idPatient = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select idPatient from patients where (select emailUser from users where patients.idUser = users.idUser) = ? ", new String[]{email});
        while (cursor.moveToNext()) {
            idPatient = cursor.getInt(0);
        }
        return idPatient;
    }
    public static byte[] imageViewToByte(int imageId, Context context) {

        Drawable drawable = context.getResources().getDrawable(imageId);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}


