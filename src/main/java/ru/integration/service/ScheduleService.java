package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import java.time.LocalDate;

import ru.integration.model.medos.ScheduleEntry;
import ru.integration.model.promed.Person;
import ru.integration.model.promed.TimeTable;

public interface ScheduleService {
    ClientResponse getDateTables(LocalDate date);

    ClientResponse getTimeByDateId(int dateId);

    ScheduleEntry createMedosModel(Person person, TimeTable timeTable);

    ClientResponse sendToMedos(ScheduleEntry model);
}
