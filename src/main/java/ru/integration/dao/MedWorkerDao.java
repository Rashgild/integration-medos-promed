package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import ru.integration.model.MedWorker;

public interface MedWorkerDao {

    ClientResponse getMedworkerById(Integer medstaffId);

    void save(MedWorker medWorker);
}
