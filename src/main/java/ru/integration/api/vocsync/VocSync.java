package ru.integration.api.vocsync;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.integration.entities.Voc;
import ru.integration.util.GlobalVariables;
import ru.integration.util.SQLUtils;


@Path("/vocsync")
public class VocSync {

    @GET
    @Path("/sync")
    @Produces("application/json;charset=UTF-8")
    public static String sync(@QueryParam("TableName") @DefaultValue("") String tableName) {

        //TODO Сессия
        GlobalVariables.sessionId = "fj4ack71b3645667k9auelgbh6";
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(GlobalVariables.endpoint)
                .path("api/Refbook")
                .queryParam("Refbook_TableName", tableName)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId)
                .get();

        System.out.println("SID>>|" + GlobalVariables.sessionId);
        return parseVoc(response.readEntity(String.class));
    }

    private static String parseVoc(String json) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();
        JsonArray array = jparse.getAsJsonArray("data");

        List<Voc> vocs = new ArrayList<>();

        for (JsonElement user : array) {
            Voc voc = new Voc();
            JsonObject userObject = user.getAsJsonObject();
            voc.setName(userObject.get("Code").getAsString());
            voc.setCode(userObject.get("id").getAsString());
            vocs.add(voc);
        }

        StringBuilder sb = new StringBuilder();

        for (Voc v : vocs) {
            sb.append("insert into test(name,code) VALUES ('" + v.getName() + "','" + v.getCode() + "'); ");
        }
        return sb.toString();
    }


    @GET
    @Path("/syncOrgSMO")
    @Produces("application/json;charset=UTF-8")
    public static String syncOrgSMO(@QueryParam("Password") @DefaultValue("Test_api12") String tableName) {

        String json = sync("dbo.OrgSmo");
        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();
        JsonArray array = jparse.getAsJsonArray("data");

        List<Voc> vocList = new ArrayList<>();

        for (JsonElement user : array) {
            JsonObject userObject = user.getAsJsonObject();

            Voc voc = new Voc();
            voc.setId(isFull(userObject, "id"));
            voc.setName(isFull(userObject, "Name"));
            voc.setCode(isFull(userObject, "Code"));
            vocList.add(voc);
        }

        List<Voc> vocList1 = selectedVocFromBase("select * from reg_ic");
        StringBuilder sb = new StringBuilder();
        for (Voc v : vocList) {
            for (Voc v2 : vocList1) {

                if (v.getName().equals(v2.getName())) {
                    sb.append("update REG_IC set promedcode = '" + v.getId() + "' where id = " + v2.getId() + "; \n");
                }
            }
        }
        return sb.toString();
    }


    @GET
    @Path("/syncvocworfunction")
    @Produces("application/json;charset=UTF-8")
    public static String syncvocworfunction(@QueryParam("Password") @DefaultValue("Test_api12") String tableName) {


        List<Voc> vocList = selecttmp("select * from voctmp");
        List<Voc> vocList1 = selectedVocFromBase("select * from vocworkfunction");

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (Voc v : vocList1) {
            for (Voc v2 : vocList) {

                // System.out.println(v.getName()+"|"+v2.getName());
                //if(v.getName().contains(v2.getName())){
                if (v.getName().equals(v2.getName())) {
                    sb.append("update vocworkfunction set fsscode ='" + v2.getShortcode() + "' ,fssshortname= '" + v2.getShortname() + "' where id = " + v.getId() + "\n");
                    sb.append(v2.getName() + "---" + v.getName());
                }
            }
        }
        return sb.toString();
    }


    private static List selecttmp(String sql) {
        ResultSet resultSet = SQLUtils.select(sql);
        List<Voc> vocList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Voc voc = new Voc();
                voc.setName(resultSet.getString("name").trim());
                voc.setShortcode(resultSet.getString("code").trim());
                voc.setShortname(resultSet.getString("shortname").trim());
                vocList.add(voc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vocList;
    }


    public static List selectedVocFromBase(String sql) {

        ResultSet resultSet = SQLUtils.select(sql);
        List<Voc> vocList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Voc voc = new Voc();
                voc.setId(resultSet.getString("id"));
                voc.setName(resultSet.getString("name"));
                vocList.add(voc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vocList;
    }

    public static String isFull(JsonObject obj, String name) {

        if (obj.has(name)) {
            if (obj.get(name).isJsonNull()) {
                return "null";
            } else {
                return obj.get(name).getAsString();
            }
        } else {
            return "null";
        }
    }

}
