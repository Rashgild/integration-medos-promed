package ru.integration.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.integration.util.GlobalVariables;

@Path("/address")
public class Address {

    @GET
    @Path("/getAddress")
    @Produces("application/json;charset=UTF-8")
    public static String getAddress(@QueryParam("Person_id") String personId,
                                    @QueryParam("AddressType_id") String AddressTypeId) {

        /*
         * 1. - Адрес рег.
         * 2. - Адрес прож.
         * 3. - Адрес рож.
         */
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(GlobalVariables.endpoint)
                .path("api/Address")
                .queryParam("Person_id", personId)
                .queryParam("AddressType_id", AddressTypeId)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId)
                .get();

        return response.readEntity(String.class);
    }


    @GET
    @Path("/setAddress")
    @Produces("application/json;charset=UTF-8")
    public static String setAddress(@QueryParam("surname") String surname,
                                    @QueryParam("firstname") String firstname,
                                    @QueryParam("birth") String birth,
                                    @QueryParam("snils") String snils) {

        Client client = ClientBuilder.newClient();
        Response response = client
                .target(GlobalVariables.endpoint)
                .path("api/Address")
                .queryParam("Person_id", surname)
                .queryParam("AddressType_id", snils)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId)
                .get();

        return response.readEntity(String.class);
    }

    @GET
    @Path("/updateAddress")
    @Produces("application/json;charset=UTF-8")
    public static String updateAddress(@QueryParam("surname") String surname,
                                       @QueryParam("firstname") String firstname,
                                       @QueryParam("birth") String birth,
                                       @QueryParam("snils") String snils) {

        Client client = ClientBuilder.newClient();
        Response response = client
                .target(GlobalVariables.endpoint)
                .path("api/Person")
                .queryParam("PersonSurName_SurName", surname)
                .queryParam("PersonFirName_FirName", firstname)
                .queryParam("PersonBirthDay_BirthDay", birth)
                .queryParam("PersonSnils_Snils", snils)
                .request(MediaType.APPLICATION_JSON)
                .get();

        return response.readEntity(String.class);
    }
}
