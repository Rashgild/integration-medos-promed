package ru.rashgild.dao;

import com.sun.jersey.api.client.ClientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.rashgild.model.medos.Patient;
import ru.rashgild.service.AuthService;

import javax.ws.rs.core.MediaType;
import java.util.Map;

@Repository("PersonDao")
public class PersonDaoImpl extends AbstractDao implements PersonDao {

    private final AuthService service;

    @Value("${promed.endpoint.person}")
    private String promedEndpointPerson;

    public PersonDaoImpl(AuthService service) {
        this.service = service;
    }

    @Override
    public ClientResponse getPersonByIdFormPromed(Long personId) {
        Map.Entry<String, String> header = service.getHeader();
        return client
                .resource(promedEndpoint)
                .path(promedEndpointPerson)
                .queryParam("Person_id", String.valueOf(personId))
                .header(header.getKey(), header.getValue())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

    @Override
    public ClientResponse getPersonByData(Patient patient) {
        Map.Entry<String, String> header = service.getHeader();
        return client
                .resource(promedEndpoint)
                .path(promedEndpointPerson)
                .queryParam("PersonSurName_SurName", patient.getLastname())
                .queryParam("PersonFirName_FirName", patient.getFirstname())
                .queryParam("PersonBirthDay_BirthDay", String.valueOf(patient.getBirthday()))
                .queryParam("PersonSnils_Snils", patient.getSnils())
                .header(header.getKey(), header.getValue())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

    @Override
    public ClientResponse getPersonBySnils(String snils) {
        Map.Entry<String, String> header = service.getHeader();
        return client
                .resource(promedEndpoint)
                .path(promedEndpointPerson)
                .queryParam("PersonSnils_Snils",snils)
                .header(header.getKey(), header.getValue())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }
}
