package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import ru.integration.model.schedule.BusyDateTime;

public interface BusyTimeDao {

    ClientResponse getBusyTimeFromPromed(BusyDateTime busyDateTime);
}
