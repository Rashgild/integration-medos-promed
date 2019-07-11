package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Repository;

import ru.integration.model.MedStaff;

@Repository("MedStaffDao")
public class MedStaffDaoImpl extends AbstractDao implements MedStaffDao {

    @Override
    public ClientResponse getMedstaffById(Integer medstaffId) {
        return client
                .resource(environment.getProperty("promed.endpoint"))
                .path("api/MedStaffFactById")
                .queryParam("MedStaffFact_id", String.valueOf(medstaffId))
                .header("Cookie", "PHPSESSID=" + environment.getProperty("sessionId"))
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }
}
