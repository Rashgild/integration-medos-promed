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


@Path("/polis")
public class Polis {

    @GET
    @Path("/getpolis")
    @Produces("application/json;charset=UTF-8")
    public static String getPolis(@QueryParam("Person_id") @DefaultValue("") String personId,
                                  @QueryParam("Polis_Ser") @DefaultValue("") String polisSer,
                                  @QueryParam("Polis_Num") @DefaultValue("") String polisNum,
                                  @QueryParam("Polis_id") @DefaultValue("") String polisId) {

        Client client = ClientBuilder.newClient();
        Response response = client
                .target(GlobalVariables.endpoint)
                .path("api/Polis")
                .queryParam("Person_id", personId)
                .queryParam("Polis_Ser", polisSer)
                .queryParam("Polis_Num", polisNum)
                .queryParam("Polis_id", polisId)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId)
                .get();

        return response.readEntity(String.class);
    }


    @POST
    @Path("/setpolis")
    @Produces("application/json;charset=UTF-8")
    public static String setPolis(@QueryParam("surname") @DefaultValue("Test_api") String surname,
                                  @QueryParam("firstname") @DefaultValue("Test_api12") String firstname,
                                  @QueryParam("birth") @DefaultValue("Test_api12") String birth,
                                  @QueryParam("snils") @DefaultValue("Test_api12") String snils) {

        Client client = ClientBuilder.newClient();
        Response response = client
                .target(GlobalVariables.endpoint)
                .path("api/Polis")
                .queryParam("PersonSurName_SurName", surname)
                .queryParam("PersonFirName_FirName", firstname)
                .queryParam("PersonBirthDay_BirthDay", birth)
                .queryParam("PersonSnils_Snils", snils)
                .request(MediaType.APPLICATION_JSON)
                .get();
/*Если найден один человек, то создается Полис и формируется успешный ответ – информация по полису:
 Person_id (N, О) – идентификатор пациента;
 Polis_id (N, О) – идентификатор полиса*/
        return response.readEntity(String.class);
    }

    @PUT
    @Path("/updatepolis")
    @Produces("application/json;charset=UTF-8")
    public static String updatePolis(@QueryParam("surname") @DefaultValue("Test_api") String surname,
                                     @QueryParam("firstname") @DefaultValue("Test_api12") String firstname,
                                     @QueryParam("birth") @DefaultValue("Test_api12") String birth,
                                     @QueryParam("birth") @DefaultValue("Test_api12") String snils) {

        Client client = ClientBuilder.newClient();
        Response response = client
                .target(GlobalVariables.endpoint)
                .path("api/Polis")
                .queryParam("PersonSurName_SurName", surname)
                .queryParam("PersonFirName_FirName", firstname)
                .queryParam("PersonBirthDay_BirthDay", birth)
                .queryParam("PersonSnils_Snils", snils)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId)
                .get();

        /*Если найден один человек, то редактируются данные полиса и формируется успешный ответ – пустой ответ с кодом ошибки «0»*/
        return response.readEntity(String.class);
    }

}
