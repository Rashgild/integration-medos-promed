package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.integration.dao.PersonDao;
import ru.integration.model.medosEntity.Patient;
import ru.integration.model.promedEntity.Person;

@Service("PersonService")
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao dao;

    @Override
    public Person getPersonFromPromed(Patient patient) {
        ClientResponse response = dao.getPersonByData(patient);
        if (response.getStatus() == HttpStatus.OK.value()) {
            Person.Persons persons = response.getEntity(Person.Persons.class);
            if (persons != null && persons.getPersonList().size() > 0) {
                return persons.getPersonList().get(0);
            }
        }
        return null;
    }
}
