package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import ru.integration.model.medos.ScheduleEntry;
import ru.integration.model.promed.Person;
import ru.integration.model.promed.TimeTable;

public interface ScheduleService {
    ClientResponse getDateTables(String date);

    ClientResponse getTimeByDateId(int dateId);

    ScheduleEntry createMedosModel(Person person, TimeTable timeTable);

    ClientResponse sendToMedos(ScheduleEntry model);
}
