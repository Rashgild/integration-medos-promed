package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import ru.integration.model.medos.Patient;

public interface PersonDao {

    ClientResponse getPersonByIdFormPromed(Integer personId);

    ClientResponse getPersonByData(Patient patient);
}
