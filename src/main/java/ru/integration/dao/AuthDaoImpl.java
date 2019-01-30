package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import ru.integration.model.Auth;

@Repository("AuthDao")
public class AuthDaoImpl extends AbstractDao implements AuthDao {

    @Autowired
    private Environment environment;

    @Override
    public ClientResponse getAuthToken() {
        return client
                .resource(environment.getProperty("promed.endpoint"))
                .path("api/user/login")
                .queryParam("login", environment.getProperty("promed.login"))
                .queryParam("password", environment.getProperty("promed.password"))
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

    @Override
    public Auth getSessionId() {
        Auth e = new Auth();
        return e;
    }

    @Override
    public void save(Auth auth) {
        persist(auth);
    }
}
