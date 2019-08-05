package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import java.time.LocalDate;

import ru.integration.model.medos.ScheduleEntry;

public interface ScheduleDao {

    ClientResponse getDate(LocalDate date);

    ClientResponse getTimeByDateId(int dateId);

    ClientResponse sendResultToMedos(ScheduleEntry model);
}
