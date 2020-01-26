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
        return getPerson(dao.getPersonByData(patient));
    }

    @Override
    public Person getPersonBySnils(String snils) {
        return getPerson(dao.getPersonBySnils(snils));
    }

    @Override
    public Person getPersonById(Long id) {
        return getPerson(dao.getPersonByIdFormPromed(id));
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
