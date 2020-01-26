package ru.rashgild.dao;


import com.sun.jersey.api.client.ClientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.rashgild.service.AuthService;

import javax.ws.rs.core.MediaType;
import java.util.Map;

@Repository("WorkPlaceDao")
public class WorkPlaceDaoImpl extends AbstractDao implements WorkPlaceDao {

    private final AuthService service;

    @Value("${promed.endpoint.workplace}")
    private String promedWorkPlace;

    public WorkPlaceDaoImpl(AuthService service) {
        this.service = service;
    }

    @Override
    public ClientResponse getWorkPlaceByPersonId(int personId) {
        Map.Entry<String, String> header = service.getHeader();
        return client
                .resource(promedEndpoint)
                .path(promedWorkPlace)
                .queryParam("person_id", String.valueOf(personId))
                .header(header.getKey(), header.getValue())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }
}
