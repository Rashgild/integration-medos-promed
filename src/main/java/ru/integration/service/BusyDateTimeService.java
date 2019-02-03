package ru.integration.service;

import java.util.List;

import ru.integration.model.schedule.BusyDateTime;

public interface BusyDateTimeService {

    List<BusyDateTime> getBusyDateTimeFromPromed(String dateBegin, String dateEnd);

    void saveListBusyDateTime(List<BusyDateTime> busyDateTimes);
}
