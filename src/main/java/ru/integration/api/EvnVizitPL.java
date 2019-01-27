package ru.integration.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.integration.util.GlobalVariables;


@Path("/evnVizitPL")
public class EvnVizitPL {

    @GET
    @Path("/getVizit")
    @Produces("application/json;charset=UTF-8")
    public static String getEvnVizit(@QueryParam("EvnPLBaseId") String EvnPLBaseId,
                                     @QueryParam("EvnVizitPLDate") String EvnVizitPLDate) {

        Response response = ClientBuilder.newClient()
                .target(GlobalVariables.endpoint)
                .path("api/EvnVizitPLEntity")
                .queryParam("EvnPLBase_id", EvnPLBaseId)
                .queryParam("EvnVizitPL_Date", EvnVizitPLDate)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId)
                .get();

        return response.readEntity(String.class);
    }
}
