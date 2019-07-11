package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

public interface MedWorkerDao {

    ClientResponse getMedworkerById(Integer medstaffId);
}
