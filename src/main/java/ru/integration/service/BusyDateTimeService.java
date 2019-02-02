package ru.integration.service;

import java.util.List;

import ru.integration.model.schedule.BusyDateTime;
import ru.integration.model.schedule.BusyDateTimeList;

public interface BusyDateTimeService {
    List<BusyDateTime> getBusyDateTimeFromPromed(String dateBegin, String dateEnd);

    BusyDateTimeList getBusyDateTimeListFromPromed(String dateBegin, String dateEnd);
}
