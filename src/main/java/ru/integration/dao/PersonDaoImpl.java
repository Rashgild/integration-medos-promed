package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.integration.model.medos.Patient;
import ru.integration.service.AuthService;

@Repository("PersonDao")
public class PersonDaoImpl extends AbstractDao implements PersonDao {

    @Autowired
    private AuthService service;

    @Override
    public ClientResponse getPersonByIdFormPromed(Integer personId) {

        return client
                .resource(environment.getProperty("promed.endpoint"))
                .path("api/Person")
                .queryParam("Person_id", String.valueOf(personId))
                .header("Cookie", "PHPSESSID=" + service.getToken().getSessId())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

    @Override
    public ClientResponse getPersonByData(Patient patient) {

        return client
                .resource(environment.getProperty("promed.endpoint"))
                .path("api/Person")
                .queryParam("PersonSurName_SurName", patient.getLastname())
                .queryParam("PersonFirName_FirName", patient.getFirstname())
                .queryParam("PersonBirthDay_BirthDay", String.valueOf(patient.getBirthday()))
                .queryParam("PersonSnils_Snils", patient.getSnils())
                .header("Cookie", "PHPSESSID=" + service.getToken().getSessId())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

    }
}
