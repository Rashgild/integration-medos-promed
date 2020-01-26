package ru.rashgild.dao;

import com.sun.jersey.api.client.ClientResponse;

public interface WorkPlaceDao {
    ClientResponse getWorkPlaceByPersonId(int personId);
}
