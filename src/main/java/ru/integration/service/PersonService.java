package ru.integration.service;

import ru.integration.model.Person;

public interface PersonService {

    Person getPersonFromPromed(Integer id);

    void savePerson(Person person);
}
