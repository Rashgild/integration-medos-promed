package ru.rashgild.dao;

import com.sun.jersey.api.client.ClientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.rashgild.model.promed.Evn;
import ru.rashgild.model.promed.EvnVisit;
import ru.rashgild.model.promed.EvnXmlDiary;

import javax.ws.rs.core.MediaType;

@Repository("TapDao")
public class TapDaoImpl extends AbstractDao implements TapDao {

    @Value("${api.endpoint.url}")
    private String medosApiEndpoint;

    @Value("${api.endpoint.polyclinic}")
    private String medosApiEndpointPolyclinic;

    @Value("${api.endpoint.evnResponse}")
    private String medosApiEndpointEvnResponse;

    @Value("${promed.endpoint.evnBase}")
    private String promedEvn;

    @Value("${promed.endpoint.evnVisit}")
    private String promedVisit;

    @Value("${promed.endpoint.diary}")
    private String promedDiary;

    @Override
    public ClientResponse getTapFromMedos(String date) {
        return client
                .resource(medosApiEndpoint)
                .path(medosApiEndpointPolyclinic)
                .queryParam("dateTo", String.valueOf(date))
                .queryParam("isUpload", "true")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

    @Override
    public void sendResultToMedos(String medcaseId, String evnId) {
        client
                .resource(medosApiEndpoint)
                .path(medosApiEndpointEvnResponse)
                .queryParam("medcase_id", medcaseId)
                .queryParam("tap_id", evnId)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class);
    }

    @Override
    public ClientResponse sendTapToPromed(Evn evn) {
        return baseRequest(promedEvn)
                .post(ClientResponse.class, evn);
    }

    @Override
    public ClientResponse sendVisitToPromed(EvnVisit evnVisit) {
        return baseRequest(promedVisit)
                .post(ClientResponse.class, evnVisit);
    }

    @Override
    public ClientResponse sendDiaryToPromed(EvnXmlDiary evnXmlDiary) {
        return baseRequest(promedDiary)
                .post(ClientResponse.class, evnXmlDiary);
    }
}
