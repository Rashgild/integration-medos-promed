package ru.rashgild.dao;

import com.sun.jersey.api.client.ClientResponse;
import javafx.util.Pair;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import ru.rashgild.service.AuthService;

@Repository("DictionaryDao")
public class DictionaryDaoImpl extends AbstractDao implements DictionaryDao {

    private final AuthService service;

    @Value("${promed.endpoint}")
    private String promedEndpoint;

    @Value("${promed.dictionary.lpuSection}")
    private String dictionaryLpuSection;

    @Value("${promed.dictionary.medstaff}")
    private String dictionaryMedstaff;

    @Value("${lpu.id}")
    private String lpuId;

    public DictionaryDaoImpl(AuthService service) {
        this.service = service;
    }

    @Override
    public ClientResponse getLpuDictionarySection() {
        Pair<String, String> header = service.getHeader();
        return client
                .resource(promedEndpoint)
                .path(dictionaryLpuSection)
                .queryParam("Lpu_id", String.valueOf(lpuId))
                .header(header.getKey(), header.getValue())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

    @Override
    public ClientResponse getDictionaryMedstaff(Long medPersonalId, Long LpuSectionId) {
        Pair<String, String> header = service.getHeader();
        return client
                .resource(promedEndpoint)
                .path(dictionaryMedstaff)
                .queryParam("medPersonal_id", String.valueOf(medPersonalId))
                .queryParam("LpuSection_id", String.valueOf(LpuSectionId))
                .header(header.getKey(), header.getValue())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

}
