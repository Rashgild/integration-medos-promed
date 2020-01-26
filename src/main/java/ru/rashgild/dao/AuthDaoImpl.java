package ru.rashgild.dao;

import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("AuthDao")
public class AuthDaoImpl extends AbstractDao implements AuthDao {

    @Value("${promed.login}")
    private String promedLogin;

    @Value("${promed.password}")
    private String promedPassword;

    @Value("${promed.endpoint}")
    private String promedEndpoint;

    @Value("${promed.endpoint.login}")
    private String promedEndpointLogin;

    @Value("${promed.endpoint.logout}")
    private String promedEndpointLogout;

    @Override
    public ClientResponse getAuthToken() {
        return client
                .resource(promedEndpoint)
                .path(promedEndpointLogin)
                .queryParam("login", promedLogin)
                .queryParam("password", promedPassword)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

    @Override
    public ClientResponse logout() {
        return client
                .resource(promedEndpoint)
                .path(promedEndpointLogout)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }
}
