package ru.integration.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.integration.dao.DaoImpl;
import ru.integration.entities.AuthEntity;
import ru.integration.util.GlobalVariables;
import ru.integration.util.Methods;

@Path("/auth")
public class Auth {

    @GET
    @Path("/login")
    @Produces("application/json;charset=UTF-8")
    public static String login(@QueryParam("login") @DefaultValue("Test_api") String login,
                               @QueryParam("Password") @DefaultValue("Test_api12") String password) {

        Map<String, String> params = new HashMap<>();
        params.put("login", login);
        params.put("Password", password);

        return Methods.creteGetRequest("api/user/login", params);
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

    @GET
    @Path("getSessionId")
    @Produces("application/json;charset=UTF-8")
    public static String getSessionId(@QueryParam("test") String test) {
        return "{\"sessionId\":\"" + GlobalVariables.sessionId + "\"}";
    }

    public static String login() {
        String ret = login("Test_api", "Test_api12");
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
}
