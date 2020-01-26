package ru.rashgild.dao;

import com.sun.jersey.api.client.ClientResponse;

import ru.rashgild.model.medos.Patient;

public interface PersonDao {

    ClientResponse getPersonByIdFormPromed(Long personId);

    ClientResponse getPersonByData(Patient patient);

    ClientResponse getPersonBySnils(String snils);
}
