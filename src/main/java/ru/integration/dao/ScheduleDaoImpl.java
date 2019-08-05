package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import java.time.LocalDate;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.integration.model.medos.ScheduleEntry;
import ru.integration.service.AuthService;

@Repository("CalendarDao")
public class ScheduleDaoImpl extends AbstractDao implements ScheduleDao {

    @Autowired
    private AuthService authService;

    @Override
    public ClientResponse getDate(LocalDate date) {

        return client
                .resource(environment.getProperty("promed.endpoint"))
                .path("api/TimeTableGraf/TimeTableGrafbyMO")
                .queryParam("TimeTableGraf_beg", String.valueOf(date))
                .queryParam("TimeTableGraf_end", String.valueOf(date))
                .queryParam("Lpu_id", environment.getProperty("lpu.id"))
                .header("Cookie", "PHPSESSID=" + authService.getToken().getSessId())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

    @Override
    public ClientResponse getTimeByDateId(int dateId) {

        return client
                .resource(environment.getProperty("promed.endpoint"))
                .path("api/TimeTableGraf/TimeTableGrafById")
                .queryParam("TimeTableGraf_id", String.valueOf(dateId))
                .header("Cookie", "PHPSESSID=" + authService.getToken().getSessId())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

    }

    @Override
    public ClientResponse sendResultToMedos(ScheduleEntry json) {

        return client
                .resource("http://192.168.10.20:8080/riams")
                .path("/api/record/makeRecord")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, json);

    }
}
