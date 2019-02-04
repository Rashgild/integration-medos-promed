package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.integration.dao.BusyTimeDao;
import ru.integration.model.schedule.BusyDateTime;
import ru.integration.model.schedule.BusyTime;

@Service("BusyTimeService")
@Transactional
public class BusyTimeServiceImpl implements BusyTimeService {

    @Autowired
    private BusyTimeDao dao;

    @Override
    public void saveBusyTimeFromPromed(BusyDateTime busyDateTime) {
        ClientResponse response = dao.getBusyTimeByIdFromPromed(busyDateTime.getTimeId());
        List<BusyTime> busyTimes = response.getEntity(BusyTime.BusyTimeList.class).getBusyTimes();
        for (BusyTime busyTime : busyTimes) {
            busyTime.setPromedId(busyDateTime.getTimeId());
            dao.save(busyTime);
        }
    }
}
