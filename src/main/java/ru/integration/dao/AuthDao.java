package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import ru.integration.model.Auth;

public interface AuthDao {

    ClientResponse getAuthToken();

    Auth getSessionId();

    void save(Auth auth);
}
