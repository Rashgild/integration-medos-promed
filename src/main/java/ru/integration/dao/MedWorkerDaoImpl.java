package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Repository;

import ru.integration.model.MedWorker;

@Repository("MedWorkerDao")
public class MedWorkerDaoImpl extends AbstractDao implements MedWorkerDao {

    @Override
    public ClientResponse getMedworkerById(Integer medstaffId) {
        return client
                .resource(environment.getProperty("promed.endpoint"))
                .path("api/MedWorkerById")
                .queryParam("MedWorker_id", String.valueOf(medstaffId))
                .header("Cookie", "PHPSESSID=" + environment.getProperty("sessionId"))
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }
}
