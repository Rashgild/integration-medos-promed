package ru.rashgild.dao;


import com.sun.jersey.api.client.ClientResponse;
import javafx.util.Pair;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import ru.rashgild.service.AuthService;

@Repository("WorkPlaceDao")
public class WorkPlaceDaoImpl extends AbstractDao implements WorkPlaceDao {

    private final AuthService service;

    @Value("${promed.endpoint}")
    private String endpoint;

    @Value("${promed.endpoint.workplace}")
    private String promedWorkPlace;

    public WorkPlaceDaoImpl(AuthService service) {
        this.service = service;
    }

    @Override
    public ClientResponse getWorkPlaceByPersonId(int personId) {
        Pair<String, String> header = service.getHeader();
        return client
                .resource(endpoint)
                .path(promedWorkPlace)
                .queryParam("person_id", String.valueOf(personId))
                .header(header.getKey(), header.getValue())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }
}
