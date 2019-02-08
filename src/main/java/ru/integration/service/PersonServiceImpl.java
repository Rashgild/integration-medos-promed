package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.integration.dao.PersonDao;
import ru.integration.model.Person;

@Service("PersonService")
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao dao;

    @Override
    public Person getPersonFromPromed(Integer id) {
        ClientResponse response = dao.getPersonByIdFormPromed(id);
        Person.PersonList persons = response.getEntity(Person.PersonList.class);
        if (persons.getError().equals(0)) {
            return persons.getPersonList().get(0);
        }
        return null;
    }

    @Override
    public void savePerson(Person person) {
        dao.save(person);
    }
}
