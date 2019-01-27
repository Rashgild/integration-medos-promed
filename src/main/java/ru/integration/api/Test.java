package ru.integration.api;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("/test")
public class Test {

    @GET
    @Path("/test1")
    @Produces("application/json;charset=UTF-8")
    public static String sync(@QueryParam("id") @DefaultValue("") String id) {
        System.out.println(id);
        return id;
    }
}
