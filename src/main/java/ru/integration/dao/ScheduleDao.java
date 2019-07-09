package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import ru.integration.model.medosEntity.ScheduleEntry;

public interface ScheduleDao {

    ClientResponse getDate(String date);

    ClientResponse getTimeByDateId(int dateId);

    ClientResponse sendResultToMedos(ScheduleEntry model);
}
