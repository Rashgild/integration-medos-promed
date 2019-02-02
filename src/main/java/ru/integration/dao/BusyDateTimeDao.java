package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

import ru.integration.model.schedule.BusyDateTime;
import ru.integration.model.schedule.BusyDateTimeList;

public interface BusyDateTimeDao {

    ClientResponse getBusyDateTimeFromPromed(String timeBegin, String timeEnd);

    void save(BusyDateTime busyDateTime);

    void saveList(List<BusyDateTime> list);

    List<BusyDateTime> getNotSyncedListBusyDateTime();
}
