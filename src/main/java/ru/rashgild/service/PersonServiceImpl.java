package ru.rashgild.service;

import com.sun.jersey.api.client.ClientResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.rashgild.dao.PersonDao;
import ru.rashgild.model.medos.Patient;
import ru.rashgild.model.promed.Person;

@Service("PersonService")
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    public Person getPersonFromPromed(Patient patient) {
        ClientResponse response = dao.getPersonByData(patient);
        return getPerson(response);
    }

    @Override
    public Person getPersonBySnils(String snils) {
        ClientResponse response = dao.getPersonBySnils(snils);
        return getPerson(response);
    }

    @Override
    public Person getPersonById(int id) {
        ClientResponse response = dao.getPersonByIdFormPromed(id);
        return getPerson(response);
    }

    private Person getPerson(ClientResponse response) {
        if (response.getStatus() == HttpStatus.OK.value()) {
            Person.Persons persons = response.getEntity(Person.Persons.class);
            if (persons != null && !persons.getPersonList().isEmpty()) {
                return persons
                        .getPersonList()
                        .stream()
                        .findFirst()
                        .filter(person -> person.getPersonId() != 0).orElse(null);
            }
        }
        return null;
    }
}
