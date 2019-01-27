package ru.integration.api;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.integration.util.GlobalVariables;


@Path("/evnPLBase")
public class EvnPLBase {

    @GET
    @Path("/getevn")
    @Produces("application/json;charset=UTF-8")
    public static String getEvn(@QueryParam("Person_id") @DefaultValue("") String person_id,
                                @QueryParam("EvnPL_NumCard") @DefaultValue("") String evnPL_NumCard) {

        Response response = ClientBuilder.newClient()
                .target(GlobalVariables.endpoint)
                .path("api/EvnPLBase")
                .queryParam("Person_id", person_id)
                .queryParam("EvnPL_NumCard", evnPL_NumCard)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId)
                .get();

        return response.readEntity(String.class);
    }

    @POST
    @Path("/setevn")
    @Produces("application/json;charset=UTF-8")
    public static String setEvn(@QueryParam("Person_id") @DefaultValue("") String person_id,
                                @QueryParam("EvnPL_NumCard") @DefaultValue("") String evnPL_NumCard) {

        Response response = ClientBuilder.newClient()
                .target(GlobalVariables.endpoint)
                .path("api/EvnPLBase")
                .queryParam("Person_id", person_id)
                .queryParam("EvnPL_NumCard", evnPL_NumCard)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId)
                .get();

        return response.readEntity(String.class);
    }
}
