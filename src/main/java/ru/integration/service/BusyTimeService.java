package ru.integration.service;

import ru.integration.model.schedule.BusyTime;

public interface BusyTimeService {

    BusyTime getBusyTimeFromPromed(Integer busyTimeId);

    void saveBusyTime(BusyTime busyTimes);
}

