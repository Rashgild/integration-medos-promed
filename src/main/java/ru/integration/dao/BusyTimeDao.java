package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import ru.integration.model.schedule.BusyTime;

public interface BusyTimeDao {

    ClientResponse getBusyTimeByIdFromPromed(Integer timeId);

    void save(BusyTime busyTime);
}
