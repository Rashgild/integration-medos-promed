package ru.integration.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.integration.util.GlobalVariables;


@Path("/EvnUsluga")
public class EvnUsluga {

    @GET
    @Path("/getUsluga")
    @Produces("application/json;charset=UTF-8")
    public static String getEvnVizit(@QueryParam("EvnPid") String EvnPid,
                                     @QueryParam("EvnSetDT") String EvnSetDT,
                                     @QueryParam("UslugaComplex_id") String UslugaComplexId) {

        Response response = ClientBuilder.newClient()
                .target(GlobalVariables.endpoint)
                .path("api/EvnUsluga")
                .queryParam("Evn_pid", EvnPid)
                .queryParam("Evn_setDT", EvnSetDT)
                .queryParam("UslugaComplex_id", UslugaComplexId)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId)
                .get();

        return response.readEntity(String.class);
    }
}
