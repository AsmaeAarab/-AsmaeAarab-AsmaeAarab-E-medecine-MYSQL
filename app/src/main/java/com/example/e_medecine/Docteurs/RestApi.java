package com.example.e_medecine.Docteurs;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.e_medecine.model.User;

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
    private String Base_Url = "http://192.168.1.104:5000/user/";
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
    public User findPhone(String Phone,String Password){
        Map<String, String> params = new HashMap<String, String>();
        params.put("Password", "123");
        params.put("Phone", "0522277997");
        String URL = Base_Url+"find/user/Phone/login/{Password}/{Phone}";
        URI uri = UriComponentsBuilder.fromUriString(URL)
                .buildAndExpand(params)
                .toUri();
        uri = UriComponentsBuilder
                .fromUri(uri)
                .build()
                .toUri();
        try {
            Log.i("url_User",uri.toString());
            User user = restTemplate.exchange(//URL + "/etudiants",
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<User>() {
                    }
            ).getBody();
            Log.i("url_user_email",user.toString());
            return user;
        }catch (Exception e){
            Log.e("url_error", "Exception: "+Log.getStackTraceString(e));
            return null;

        }
    }
 /*   @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean create(User user)
    {
        try {
            Map<String,String> contentValues = new HashMap<String, String>();
            String s = Base64.getEncoder().encodeToString(user.getImageUser());
            contentValues.put("image_User",s);
            contentValues.put("nom_User",user.getNomUser());
            contentValues.put("prenom_User",user.getPrenomUser());
            contentValues.put("genre_User",user.getGenreUser());
            contentValues.put("telephone_User",user.getTelephoneUser());
            String str1 = Integer.toString(user.getIdVille().getIdVille());
            contentValues.put("id_Ville",str1);
            contentValues.put("email_User",user.getEmailUser());
            contentValues.put("password_User",user.getPasswordUser());
            contentValues.put("role_User",user.getRoleUser());
            JSONObject jsonObject = new JSONObject(contentValues);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(),headers);
            restTemplate.postForEntity(Base_Url + "create",entity,null);
            return true;
        }catch (Exception e){
            return false;
        }
    }*/
}
