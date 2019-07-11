package ru.integration.service;

import ru.integration.model.medos.Patient;
import ru.integration.model.promed.Person;

public interface PersonService {
    Person getPersonFromPromed(Patient patient);

    Person getPersonById(int id);
}
