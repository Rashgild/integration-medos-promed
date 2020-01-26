package ru.rashgild.dao;

import com.sun.jersey.api.client.ClientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.rashgild.model.medos.ScheduleEntry;
import ru.rashgild.service.AuthService;

import javax.ws.rs.core.MediaType;
import java.util.Map;

@Repository("CalendarDao")
public class ScheduleDaoImpl extends AbstractDao implements ScheduleDao {

    @Value("${promed.endpoint.url}")
    private String promedEndpoint;

    @Value("${lpu.id}")
    private String lpuId;

    @Value("${api.endpoint.url}")
    private String medosApiEndpoint;

    private final AuthService authService;

    public ScheduleDaoImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ClientResponse getDate(String date) {
        Map.Entry<String, String> header = authService.getHeader();
        return client
                .resource(promedEndpoint)
                .path("api/TimeTableGraf/TimeTableGrafbyMO")
                .queryParam("TimeTableGraf_beg", String.valueOf(date))
                .queryParam("TimeTableGraf_end", String.valueOf(date))
                .queryParam("Lpu_id", lpuId)
                .header(header.getKey(), header.getValue())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

    @Override
    public ClientResponse getTimeByDateId(int dateId) {
        Map.Entry<String, String> header = authService.getHeader();
        return client
                .resource(promedEndpoint)
                .path("api/TimeTableGraf/TimeTableGrafById")
                .queryParam("TimeTableGraf_id", String.valueOf(dateId))
                .header(header.getKey(), header.getValue())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

    @Override
    public ClientResponse sendResultToMedos(ScheduleEntry json) {

        return client
                .resource(medosApiEndpoint)
                .path("/record/makeRecord")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, json);
    }
}
