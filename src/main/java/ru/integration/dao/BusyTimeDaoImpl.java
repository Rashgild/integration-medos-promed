package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Repository;

import ru.integration.model.schedule.BusyDateTime;

@Repository("BusyTimeDao")
public class BusyTimeDaoImpl extends AbstractDao implements BusyTimeDao {

    @Override
    public ClientResponse getBusyTimeFromPromed(BusyDateTime busyDateTime) {
        return client
                .resource(environment.getProperty("promed.endpoint"))
                .path("api/TimeTableGraf/TimeTableGrafById")
                .queryParam("TimeTableGraf_id", String.valueOf(busyDateTime.getTimeId()))
                .header("Cookie", "PHPSESSID=" + environment.getProperty("sessionId"))
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

}
