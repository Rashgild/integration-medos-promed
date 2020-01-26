package ru.rashgild.dao;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import javafx.util.Pair;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import ru.rashgild.service.AuthService;

abstract class AbstractDao {

    protected Client client;

    @Autowired
    protected Environment environment;

    @Autowired
    private AuthService authService;

    @Value("${promed.endpoint}")
    private String promedEndpoint;

    @PostConstruct
    public void init() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(clientConfig);
    }

    public WebResource.Builder baseRequest(String path) {
        Pair<String, String> header = authService.getHeader();
        return client
                .resource(promedEndpoint)
                .path(path)
                .header(header.getKey(), header.getValue())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE);
    }

    public WebResource.Builder baseRequest(String path, Map<String, String> queries) {
        Pair<String, String> header = authService.getHeader();

        WebResource a = client
                .resource(promedEndpoint)
                .path(path);

        for (Map.Entry<String, String> query : queries.entrySet()) {
            a = a.queryParam(query.getKey(), query.getValue());
        }
        return a.header(header.getKey(), header.getValue())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE);
    }
}
