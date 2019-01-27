package ru.integration.api;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.integration.util.GlobalVariables;


@Path("/document")
public class Document {
    @GET
    @Path("/getDocument")
    @Produces("application/json;charset=UTF-8")
    public static String getDocument(@QueryParam("Person_id") @DefaultValue("") String personId,
                                     @QueryParam("Document_Ser") @DefaultValue("") String documentSer,
                                     @QueryParam("Document_Num") @DefaultValue("") String documentNum,
                                     @QueryParam("Document_id") @DefaultValue("") String documentId) {

        Client client = ClientBuilder.newClient();
        Response response = client
                .target(GlobalVariables.endpoint)
                .path("api/Document")
                .queryParam("Person_id", personId)
                .queryParam("Document_Ser", documentSer)
                .queryParam("Document_Num", documentNum)
                .queryParam("Document_id", documentId)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId)
                .get();

        return response.readEntity(String.class);
    }


    @POST
    @Path("/setDocument")
    @Produces("application/json;charset=UTF-8")
    public static String setDocument(@QueryParam("login") @DefaultValue("Test_api") String surname,
                                     @QueryParam("Password") @DefaultValue("Test_api12") String firstname,
                                     @QueryParam("Password") @DefaultValue("Test_api12") String birth,
                                     @QueryParam("Password") @DefaultValue("Test_api12") String snils) {

        Client client = ClientBuilder.newClient();
        Response response = client
                .target(GlobalVariables.endpoint)
                .path("api/Document")
                .queryParam("PersonSurName_SurName", surname)
                .queryParam("PersonFirName_FirName", firstname)
                .queryParam("PersonBirthDay_BirthDay", birth)
                .queryParam("PersonSnils_Snils", snils)
                .request(MediaType.APPLICATION_JSON)
                .get();

        return response.readEntity(String.class);
    }

    @PUT
    @Path("/updateDocument")
    @Produces("application/json;charset=UTF-8")
    public static String updateDocument(@QueryParam("login") @DefaultValue("Test_api") String surname,
                                        @QueryParam("Password") @DefaultValue("Test_api12") String firstname,
                                        @QueryParam("Password") @DefaultValue("Test_api12") String birth,
                                        @QueryParam("Password") @DefaultValue("Test_api12") String snils) {

        Client client = ClientBuilder.newClient();
        Response response = client
                .target(GlobalVariables.endpoint)
                .path("api/Person")
                .queryParam("Person_id", surname)
                .queryParam("Document_Ser", firstname)
                .queryParam("Document_Num", birth)
                .request(MediaType.APPLICATION_JSON)
                .get();

        return response.readEntity(String.class);
    }
}
