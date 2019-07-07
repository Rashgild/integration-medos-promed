package ru.integration.service;

import ru.integration.model.medosEntity.Patient;
import ru.integration.model.promedEntity.Person;

public interface PersonService {
    Person getPersonFromPromed(Patient patient);
}
