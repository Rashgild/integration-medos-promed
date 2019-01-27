package ru.integration.api;

import com.google.gson.Gson;

import java.util.HashMap;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import ru.integration.util.Methods;

import static ru.integration.util.Methods.creteGetRequest;


@Path("/person")
public class Person {

    @GET
    @Path("/getperson")
    @Produces("application/json;charset=UTF-8")
    public static String getPerson(@QueryParam("surname") @DefaultValue("") String surname,
                                   @QueryParam("firstname") @DefaultValue("") String firstname,
                                   @QueryParam("middlename") @DefaultValue("") String middlename,
                                   @QueryParam("birth") @DefaultValue("") String birth,
                                   @QueryParam("snils") @DefaultValue("") String snils) {

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
    public static String getPersonById(@QueryParam("Person_id") @DefaultValue("") String Person_id) {

       /* Response response = ClientBuilder.newClient()
                .target(endpoint)
                .path("api/Person")
                .queryParam("Person_id", Person_id)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie","PHPSESSID="+sessionId)
                .get();*/

        HashMap<String, String> params = new HashMap<>();
        params.put("Person_id", Person_id);

        return Methods.creteGetRequest("api/Person", params);
    }

    @GET
    @Path("/setperson")
    @Produces("application/json;charset=UTF-8")
    public static String setPerson(@QueryParam("login") @DefaultValue("Test_api") String surname,
                                   @QueryParam("Password") @DefaultValue("Test_api12") String firstname,
                                   @QueryParam("Password") @DefaultValue("Test_api12") String birth,
                                   @QueryParam("Password") @DefaultValue("Test_api12") String snils) {

        //1517251
        /*PersonEntity personEntity = new PersonEntity();
        personEntity.setPersonSurName_SurName("Testingov");
        personEntity.setPersonFirName_FirName("Test");
        personEntity.setPersonBirthDay_BirthDay("01.01.1993");
        personEntity.setPerson_Sex_id("1");
        personEntity.setSocStatus_id("10000054");*/

        Gson gson = new Gson();
        /*    String jsonInString = gson.toJson(personEntity);*/

        // System.out.println(jsonInString);
        Response response = null;/*ClientBuilder.newClient()
                .target(endpoint)
                .path("api/Person")
                //.queryParam("PersonSurName_SurName", surname)
                //.queryParam("PersonFirName_FirName", firstname)
                //.queryParam("PersonBirthDay_BirthDay", birth)
                //.queryParam("PersonSnils_Snils", snils)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie","PHPSESSID="+sessionId)
                .post(Entity.json(jsonInString),Response.class);*/
                /*.post(Entity.json("{" +
                        "\"PersonSurName_SurName\":\"Testingov\"" +
                        ",\"PersonFirName_FirName\":\"Test\"" +
                        ",\"PersonBirthDay_BirthDay\":\"01.01.2001\"" +
                        ",\"Person_Sex_id\":\"1\"" +
                        ",\"SocStatus_id\":\"10000056\"}"),Response.class);*/

        // "\"PersonFirName_FirName\":\"Test\"
        return response.readEntity(String.class);
    }



   /* @POST
    @Path("/setperson")
    @Produces("application/json;charset=UTF-8")
    public static String setPerson(@QueryParam("login") @DefaultValue("Test_api") String surname,
                                    @QueryParam("Password") @DefaultValue("Test_api12") String firstname,
                                    @QueryParam("Password") @DefaultValue("Test_api12") String birth,
                                    @QueryParam("Password") @DefaultValue("Test_api12") String snils){

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
    public static String updatePerson(@QueryParam("login") @DefaultValue("Test_api") String surname,
                                     @QueryParam("Password") @DefaultValue("Test_api12") String firstname,
                                     @QueryParam("Password") @DefaultValue("Test_api12") String birth,
                                     @QueryParam("Password") @DefaultValue("Test_api12") String snils){

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
