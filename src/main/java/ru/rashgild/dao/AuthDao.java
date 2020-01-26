package ru.rashgild.dao;

import com.sun.jersey.api.client.ClientResponse;

public interface AuthDao {

    ClientResponse getAuthToken();

    ClientResponse logout();
}
