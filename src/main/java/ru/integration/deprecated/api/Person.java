package ru.integration.deprecated.api;

import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import ru.integration.util.Methods;


@Path("/person")
public class Person {

 /*   @GET
    @Path("/getperson")
    @Produces("application/json;charset=UTF-8")
    public static String getPerson(@QueryParam("surname") String surname,
                                   @QueryParam("firstname") String firstname,
                                   @QueryParam("middlename") String middlename,
                                   @QueryParam("birth") String birth,
                                   @QueryParam("snils") String snils) {

        HashMap<String, String> params = new HashMap<>();
        params.put("PersonSurName_SurName", surname);

        if (middlename != null && !middlename.equals("")) {
            params.put("PersonSecName_SecName", middlename);
        }
        if (snils != null && !snils.equals("")) {
            params.put("PersonSnils_Snils", snils);
        }

        params.put("PersonFirName_FirName", firstname);
        params.put("PersonBirthDay_BirthDay", birth);

        return Methods.creteGetRequest("api/Person", params);
    }

    @GET
    @Path("/getPersonById")
    @Produces("application/json;charset=UTF-8")
    public static String getPersonById(@QueryParam("Person_id") String Person_id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("Person_id", Person_id);

        return Methods.creteGetRequest("api/Person", params);
    }

    @GET
    @Path("/setperson")
    @Produces("application/json;charset=UTF-8")
    public static String setPerson(@QueryParam("surname") String surname,
                                   @QueryParam("firstname") String firstname,
                                   @QueryParam("birth") String birth,
                                   @QueryParam("snils") String snils) {
        Response response = null;
        return response.readEntity(String.class);
    }
*/


   /* @POST
    @Path("/setperson")
    @Produces("application/json;charset=UTF-8")
    public static String setPerson(@QueryParam("surname") String surname,
                                    @QueryParam("firstname") String firstname,
                                    @QueryParam("birth") String birth,
                                    @QueryParam("snils") String snils){

        Client client = ClientBuilder.newClient();
        Response response =  client
                .target(endpoint)
                .path("api/Person")
                .queryParam("PersonSurName_SurName",surname)
                .queryParam("PersonFirName_FirName",firstname)
                .queryParam("PersonBirthDay_BirthDay",birth)
                .queryParam("PersonSnils_Snils",snils)
                .request(MediaType.APPLICATION_JSON)
                .post();

        return  response.readEntity(String.class);
    }*/


    //Успешный ответ – пустой ответ с кодом ошибки «0»
    //@PUT
    /*@Path("/updateperson")
    @Produces("application/json;charset=UTF-8")
    public static String updatePerson(@QueryParam("surname") String surname,
                                     @QueryParam("firstname") String firstname,
                                     @QueryParam("birth") String birth,
                                     @QueryParam("snils") String snils){

        Client client = ClientBuilder.newClient();
        Response response =  client
                .target(endpoint)
                .path("api/Person")
                .queryParam("PersonSurName_SurName",surname)
                .queryParam("PersonFirName_FirName",firstname)
                .queryParam("PersonBirthDay_BirthDay",birth)
                .queryParam("PersonSnils_Snils",snils)
                .request(MediaType.APPLICATION_JSON)
                .put();

        return  response.readEntity(String.class);
    }*/
}
