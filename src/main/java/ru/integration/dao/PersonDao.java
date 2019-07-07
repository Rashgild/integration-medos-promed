package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import ru.integration.model.Person;
import ru.integration.model.medosEntity.Patient;

public interface PersonDao {

    ClientResponse getPersonByIdFormPromed(Integer personId);

    ClientResponse getPersonByData(Patient patient);

    void save(Person person);
}
