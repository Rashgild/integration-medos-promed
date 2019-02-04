package ru.integration.service;

import ru.integration.model.schedule.BusyDateTime;

public interface BusyTimeService {

    void saveBusyTimeFromPromed(BusyDateTime busyDateTime);
}

