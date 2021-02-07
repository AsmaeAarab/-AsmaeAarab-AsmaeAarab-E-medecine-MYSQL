package com.example.e_medecine.Docteurs;

import android.content.ContentValues;

import com.example.e_medecine.model.Medecin;
import com.example.e_medecine.model.User;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestApi {
    private String Base_Url = "http://192.168.1.5:8080";
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
    public User findPhone(String Phone,String Password)
    {
        try {
            return restTemplate.exchange(Base_Url + "/find/user/Phone/login/" + Password + "/" + Phone
                    , HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<User>(){}).getBody();
        }catch (Exception e)
        {
            return null;
        }
    }
    public boolean createmedecin(Medecin medecin)
    {
        try {
            Map<String,String> contentValues = new HashMap<String, String>();
            ContentValues valuesD = new ContentValues();
            valuesD.put("idUser",String.valueOf(medecin.getIdUserMedecin()));
            valuesD.put("idSpecialite", String.valueOf(medecin.getIdSpecialiteMedecin()));
            valuesD.put("typeMedecin", medecin.getTypeMedecin());
            valuesD.put("localisationMedecin", medecin.getLocation());
            valuesD.put("TermeCondition", medecin.getTermeCondition());
            valuesD.put("frais",String.valueOf(medecin.getFrais()));
            valuesD.put("experience",String.valueOf(medecin.getExperience()));
            JSONObject jsonObject = new JSONObject(contentValues);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(),headers);
            restTemplate.postForEntity(Base_Url + "/insert/medecin",entity,null);
        }catch (Exception e){
            return false;
        }
    }
    public boolean create(User user)
    {
        try {
            Map<String,String> contentValues = new HashMap<String, String>();
            String s = Base64.getEncoder().encodeToString(user.getImageUser());
            contentValues.put("image_User",s);
            contentValues.put("nom_User",user.getNomUser());
            contentValues.put("prenom_User",user.getPrenomUser());
            contentValues.put("genre_User",user.getGenre());
            contentValues.put("telephone_User",user.getTele());
            contentValues.put("id_Ville",String.valueOf(user.getIdVille()));
            contentValues.put("email_User",user.getEmail());
            contentValues.put("password_User",user.getPassword());
            contentValues.put("role_User",user.getRole());
            JSONObject jsonObject = new JSONObject(contentValues);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(),headers);
            restTemplate.postForEntity(Base_Url + "/insert/user",entity,null);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
