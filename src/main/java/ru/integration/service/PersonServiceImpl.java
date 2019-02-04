package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.integration.dao.PersonDao;
import ru.integration.model.Person;
import ru.integration.model.schedule.BusyDateTime;

@Service("PersonService")
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao dao;

    public void savePersonByBusyDateTimePersonId(BusyDateTime busyDateTime) {
        ClientResponse response = dao.getPersonByIdFormPromed(busyDateTime.getPersonId());
        List<Person> personList = response.getEntity(Person.PersonList.class).getPersonList();
        for (Person person : personList) {
            dao.save(person);
        }
    }
}
