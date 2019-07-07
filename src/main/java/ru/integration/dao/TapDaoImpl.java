package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Repository;

@Repository("TapDao")
public class TapDaoImpl extends AbstractDao implements TapDao {

    @Override
    public ClientResponse getTapFromMedos(String date) {
        //TODO endpoint hardcoded!
        return client
                .resource("http://192.168.10.20:8080/riams/api/promed/")
                .path("getPolyclinicCase")
                .queryParam("dateTo", String.valueOf(date))
                .queryParam("isUpload", "true")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

    @Override
    public String sendTapToPromed() {
        return "";
    }

    @Override
    public String sendVisitToPromed() {
        return "";
    }

    @Override
    public String sendDiaryToPromed() {
        return "";
    }
}
