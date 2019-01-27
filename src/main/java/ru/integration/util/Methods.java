package ru.integration.util;

import com.google.gson.JsonObject;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.integration.entities.AuthEntity;
import ru.integration.api.Auth;

import static ru.integration.api.Auth.login;


public class Methods {

    public static String getJsonObj(JsonObject js, String key) {
        if (js.has(key) && !js.get(key).equals("null")) {
            return js.get(key).toString();
        } else return null;
    }

    public static String checkJsonObj(JsonObject js, String key) {
        if (js.has(key) && !js.get(key).toString().equals("null") && !js.get(key).equals("null")) {
            return js.get(key).getAsString();
        } else return "";
    }

    public static Integer checkJsonObjGetInteger(JsonObject js, String key) {
        if (js.has(key) && !js.get(key).toString().equals("null")) {
            return js.get(key).getAsInt();
        } else return 0;
    }

    public static String creteGetRequest(String endpoint, String path, Map<String, String> params) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(endpoint);
        target = target.path(path);
        for (Map.Entry entry : params.entrySet()) {
            target = target.queryParam(entry.getKey().toString(), entry.getValue().toString());
        }
        Response response = target.request(MediaType.APPLICATION_JSON).header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId).get();
        System.out.println(response);
        return response.readEntity(String.class);
    }

    public static String creteGetRequest(String endpoint, String path) {
        return creteGetRequest(endpoint, path, new HashMap<String, String>());
    }

    public static String creteGetRequest(String path, Map<String, String> params) {
        return creteGetRequest(GlobalVariables.endpoint, path, params);
    }


    public static String cretePostRequest(String path, String json) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(GlobalVariables.endpoint);
        target = target.path(path);
        /*for (Map.Entry entry : params.entrySet()) {
            target = target.queryParam(entry.getKey().toString(),entry.getValue().toString());
        }*/
        Response response = target.request(MediaType.APPLICATION_JSON).header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId).post(Entity.json(json));
        System.out.println(response);
        return response.readEntity(String.class);
    }

   /* public static WebTarget creteGetRequest(String path, Map<String, String> params,String test){

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(endpoint);
        target = target.path(path);
        for (Map.Entry entry : params.entrySet()) {
            target = target.queryParam(entry.getKey().toString(),entry.getValue().toString());
        }
         target.request(MediaType.APPLICATION_JSON).header("Cookie","PHPSESSID="+sessionId).get();
        //System.out.println(response);
        return  target;
    }*/

    public static String getErrorCode(JsonObject jparse) {
        if (jparse.has("error_code")) {
            return jparse.get("error_code").getAsString();
        }
        return "1";
    }

    public static boolean checkCode(JsonObject jparse) {
        if (jparse.has("error_code") && jparse.get("error_code").getAsString().equals("0")) return true;
        else {
            checkCode();
            return false;
        }
    }

    public static void checkCode() {
        AuthEntity auth = Auth.getAuthKey();
        if (auth != null) {
            GlobalVariables.sessionId = auth.getPHPSESSID();
        } else {
            GlobalVariables.sessionId = login();
        }
    }

    public static boolean isStringEmpty(String str) {
        if (!str.equals("")) return true;
        else return false;
    }


    public static String getCurrent_date() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public static String getCurrent_time() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public static Date getDate(String str) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp ts = Timestamp.valueOf(str);
        return Date.valueOf(dateFormat.format(ts));
    }

    public static Time getTime(String str) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Timestamp ts = Timestamp.valueOf(str);
        return Time.valueOf(dateFormat.format(ts));
    }

    public static Time calcTime(Time sqlTime, Integer time) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sqlTime);
        calendar.add(Calendar.MINUTE, time);
        //sqlTime.setTime(calendar.getTimeInMillis());
        Time time1 = new Time(0);
        time1.setTime(calendar.getTimeInMillis());
        return time1;
    }
}
