package ru.rashgild.service;

import ru.rashgild.model.medos.Patient;
import ru.rashgild.model.promed.Person;

public interface PersonService {
    Person getPersonFromPromed(Patient patient);

    Person getPersonBySnils(String snils);

    Person getPersonById(Long id);
}
