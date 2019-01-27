package ru.integration.api.vocsync;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    public static String sync(@QueryParam("TableName") String tableName) {

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
            sb
                    .append("insert into test(name,code) VALUES ('")
                    .append(v.getName())
                    .append("','")
                    .append(v.getCode())
                    .append("'); ");
        }
        return sb.toString();
    }


    @GET
    @Path("/syncOrgSMO")
    @Produces("application/json;charset=UTF-8")
    public static String syncOrgSMO(@QueryParam("tableName") String tableName) {

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
                    sb
                            .append("update REG_IC set promedcode = '")
                            .append(v.getId())
                            .append("' where id = ")
                            .append(v2.getId())
                            .append("; \n");
                }
            }
        }
        return sb.toString();
    }


    @GET
    @Path("/syncvocworfunction")
    @Produces("application/json;charset=UTF-8")
    public static String syncvocworfunction(@QueryParam("tableName") String tableName) {


        List<Voc> vocList = selecttmp("select * from voctmp");
        List<Voc> vocList1 = selectedVocFromBase("select * from vocworkfunction");

        StringBuilder sb = new StringBuilder();
        for (Voc v : vocList1) {
            for (Voc v2 : vocList) {
                if (v.getName().equals(v2.getName())) {
                    sb
                            .append("update vocworkfunction set fsscode ='")
                            .append(v2.getShortcode())
                            .append("' ,fssshortname= '")
                            .append(v2.getShortname())
                            .append("' where id = ")
                            .append(v.getId()).append("\n")
                            .append(v2.getName())
                            .append("---")
                            .append(v.getName());
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


    private static List selectedVocFromBase(String sql) {

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

    private static String isFull(JsonObject obj, String name) {

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
