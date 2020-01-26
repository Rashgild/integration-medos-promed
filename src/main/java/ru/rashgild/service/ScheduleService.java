package ru.rashgild.service;

import com.sun.jersey.api.client.ClientResponse;

import ru.rashgild.model.medos.ScheduleEntry;
import ru.rashgild.model.promed.Person;
import ru.rashgild.model.promed.TimeTable;

public interface ScheduleService {
    ClientResponse getDateTables(String date);

    ClientResponse getTimeByDateId(int dateId);

    ScheduleEntry createMedosModel(Person person, TimeTable timeTable);

    ClientResponse sendToMedos(ScheduleEntry model);
}
