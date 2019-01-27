package ru.integration.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.integration.dao.DaoImpl;
import ru.integration.entities.AuthEntity;
import ru.integration.util.GlobalVariables;
import ru.integration.util.Methods;

import static ru.integration.util.Methods.checkCode;
import static ru.integration.util.Methods.creteGetRequest;

@Path("/auth")
public class Auth {

    @GET
    @Path("/login")
    @Produces("application/json;charset=UTF-8")
    public static String login(@QueryParam("login") @DefaultValue("Test_api") String login,
                               @QueryParam("Password") @DefaultValue("Test_api12") String password) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("login", login);
        params.put("Password", password);

        String json = Methods.creteGetRequest("api/user/login", params);
        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        AuthEntity auth = new AuthEntity();
        if (Methods.checkCode(jparse)) {
            GlobalVariables.sessionId = jparse.get("sess_id").getAsString();
            auth.setPHPSESSID(jparse.get("sess_id").getAsString());
            auth.setExpire(false);
            new DaoImpl().save(auth);
        }

        return json;
    }

    public static String login() {

        String ret = Auth.login("Test_api", "Test_api12");
        System.out.println(ret);
        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(ret).getAsJsonObject();
        GlobalVariables.sessionId = "0";

        AuthEntity auth = new AuthEntity();
        if (Methods.checkCode(jparse)) {
            GlobalVariables.sessionId = jparse.get("sess_id").getAsString();
            auth.setPHPSESSID(jparse.get("sess_id").getAsString());
            auth.setExpire(false);
            new DaoImpl().save(auth);
        }
        return GlobalVariables.sessionId;
    }

    @GET
    @Path("/logout")
    @Produces("application/json;charset=UTF-8")
    public static String logout() {

        Client client = ClientBuilder.newClient();
        Response response = client
                .target(GlobalVariables.endpoint)
                .path("api/user/logout")
                .request(MediaType.APPLICATION_JSON)
                .get();

        return response.readEntity(String.class);
    }

    public static AuthEntity getAuthKey() {
        List<AuthEntity> auth = (List<AuthEntity>) new DaoImpl<>().getAllE("AuthEntity", "isExpire!=false");
        if (auth != null) {
            return auth.get(0);
        }
        return null;
    }

    public static List<AuthEntity> annulAuthKey(AuthEntity authEntity) {
        HashMap<String, String> params = new HashMap<>();
        params.put("isExpire", "false");
        new DaoImpl<>().updateRecord("AuthEntity", params, "where id=" + authEntity.getId());
        return (List<AuthEntity>) new DaoImpl<>().getAllE("AuthEntity", "isExpire!=false");
    }

    @GET
    @Path("getSessionId")
    @Produces("application/json;charset=UTF-8")
    public static String getSessionId(@QueryParam("test") String test) {

        System.out.println(test);
        System.out.println(GlobalVariables.sessionId);
        return "{\"sessionId\":\"" + GlobalVariables.sessionId + "\"}";
    }

    @GET
    @Path("getTest")
    @Produces("application/json;charset=UTF-8")
    public static String Test() {

        return "123";
    }


    /**
     *
     *
     **/

    @GET
    @Path("/test2")
    @Produces("application/json;charset=UTF-8")
    public static String test2(@QueryParam("login") @DefaultValue("Test_api") String login,
                               @QueryParam("Password") @DefaultValue("Test_api12") String password) throws IOException {

        Client client = ClientBuilder.newClient();

        Response response = client
                .target("http://192.168.2.45:999")
                .path("app/auth/test3")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json("{\"json\":\"j509\"}"), Response.class);
        return response.readEntity(String.class);
    }


    @GET
    @Path("/list")
    public Response receiveListOfStrings(@QueryParam("list") final List<String> list) {
        System.out.println("receieved list of size=" + list.size());
        return Response.ok().build();
    }

    @GET
    @Path("/test3")
    public static String test4(String data) throws IOException {
        return Resp2();
    }

    private static String Resp2() throws IOException {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target("http://192.168.10.16:8080/riams")
                .path("ecom_loginSave.do")
                .queryParam("username", "testbot")
                .queryParam("password", "123")
                .request(MediaType.APPLICATION_JSON)//.header("Cookie","JSESSIONID=1")
                .post(Entity.json(""), Response.class);

        System.out.println(response.getHeaderString("JSESSIONID"));
        System.out.println(response.getStatus());
        return response.readEntity(String.class);
    }

    private static String Resp3() throws IOException {
        Resp2();
        Client client = ClientBuilder.newClient();
        Response response = client
                .target("http://192.168.10.20:8080/riams")
                .path("mis_patients.do?")
                .queryParam("lastname", "123")


                .request(MediaType.APPLICATION_JSON)
                .header("Cookie", "JSESSIONID=100500")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Connection", "keep-alive")
                .header("Host", "192.168.10.20:8080")
                .header("Referer", "http://192.168.10.20:8080/riams/mis_patients.do?lastname=123")
                .header("Upgrade-Insecure-Requests", "1")
                .get();

        System.out.println(response.getStatus());
        return response.readEntity(String.class);
    }

}
