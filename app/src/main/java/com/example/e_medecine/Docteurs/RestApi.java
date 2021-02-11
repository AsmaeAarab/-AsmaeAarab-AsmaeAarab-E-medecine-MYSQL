package com.example.e_medecine.Docteurs;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


import com.example.e_medecine.model.User;
import com.example.e_medecine.model.Users;


import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestApi {
    private String Base_Url = "http://192.168.1.5:8080/user/";
    private RestTemplate restTemplate = new RestTemplate();
    public List<User> findAll()
    {
        try {
            return restTemplate.exchange(Base_Url + "findall",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<User>>(){}).getBody();
        }catch (Exception e)
        {
            return null;
        }
    }
    public Boolean findPhone(String Phone, String Password, String Docteur){
        Map<String, String> params = new HashMap<String, String>();
        params.put("Password", Password);
        params.put("Phone", Phone);
        params.put("Medecin",Docteur);
        String URL = Base_Url+"find/user/Phone/login/"+Docteur+"/"+Password+"/"+Phone;
        URI uri = UriComponentsBuilder.fromUriString(URL)
                .buildAndExpand(params)
                .toUri();
        uri = UriComponentsBuilder
                .fromUri(uri)
                .build()
                .toUri();
        try {
            Log.i("url_User",uri.toString());
            User user = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<User>() {
                    }
            ).getBody();
            Log.i("url_user_email",user.toString());
            return true;
        }catch (Exception e){
            Log.e("url_error", "Exception: "+Log.getStackTraceString(e));
            return false;

        }
    }
    public Users findPhoneID(String Phone){
        Map<String, String> params = new HashMap<String, String>();
        params.put("Phone", Phone);
        String URL = Base_Url+"find/user/"+Phone;
        URI uri = UriComponentsBuilder.fromUriString(URL)
                .buildAndExpand(params)
                .toUri();
        uri = UriComponentsBuilder
                .fromUri(uri)
                .build()
                .toUri();
        try {
            Log.i("url_User",uri.toString());
            Users user = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Users>() {
                    }
            ).getBody();
            Log.i("url_user_email",user.toString());
            return user;
        }catch (Exception e){
            Log.e("url_error", "Exception: "+Log.getStackTraceString(e));
            return null;

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)

    public boolean createmedecin(Medecin docteur)
    {
        try {
            Map<String,String> valuesD = new HashMap<String, String>();
            valuesD.put("idUser",String.valueOf(docteur.getIdUserMedecin()));
            valuesD.put("idSpecialite", String.valueOf(docteur.getIdSpecialiteMedecin()));
            valuesD.put("typeMedecin", docteur.getTypeMedecin());
            valuesD.put("localisationMedecin", docteur.getLocation());
            valuesD.put("TermeCondition", docteur.getTermeCondition());
            valuesD.put("frais",String.valueOf(docteur.getFrais()));
            valuesD.put("experience",String.valueOf(docteur.getExperience()));
            JSONObject json = new JSONObject(valuesD);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(json.toString(),headers);
            restTemplate.postForEntity(Base_Url + "find/insert/medecin",entity,null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean create(User user)
    {
        try {
            Map<String,String> contentValues = new HashMap<String, String>();
            String s = Base64.getEncoder().encodeToString(user.getImageUser());
            contentValues.put("imageUser",s);
            contentValues.put("nomUser",user.getNomUser());
            contentValues.put("prenomUser",user.getPrenomUser());
            contentValues.put("genreUser",user.getGenreUser());
            contentValues.put("telephoneUser",user.getTelephoneUser());
            contentValues.put("idVille",String.valueOf(user.getIdVille()));
            contentValues.put("emailUser",user.getEmailUser());
            contentValues.put("passwordUser",user.getPasswordUser());
            contentValues.put("roleUser",user.getRoleUser());
            JSONObject jsonObject = new JSONObject(contentValues);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(),headers);
            restTemplate.postForEntity(Base_Url + "find/insert/user",entity,null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
