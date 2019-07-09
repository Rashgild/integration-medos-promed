package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import ru.integration.model.medosEntity.ScheduleEntry;
import ru.integration.model.promedEntity.Person;
import ru.integration.model.promedEntity.TimeTable;

public interface ScheduleService {
    ClientResponse getDateTables(String date);

    ClientResponse getTimeByDateId(int dateId);

    ScheduleEntry createMedosModel(Person person, TimeTable timeTable);

    ClientResponse sendToMedos(ScheduleEntry model);
}
