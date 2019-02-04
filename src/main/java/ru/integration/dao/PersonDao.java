package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import ru.integration.model.Person;

public interface PersonDao {

    ClientResponse getPersonByIdFormPromed(Integer personId);

    void save(Person person);
}
