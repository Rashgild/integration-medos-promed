package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.integration.dao.BusyTimeDao;
import ru.integration.model.schedule.BusyTime;

@Service("BusyTimeService")
@Transactional
public class BusyTimeServiceImpl implements BusyTimeService {

    @Autowired
    private BusyTimeDao dao;

    @Override
    public BusyTime getBusyTimeFromPromed(Integer busyTimeId) {
        ClientResponse response = dao.getBusyTimeByIdFromPromed(busyTimeId);

        BusyTime.BusyTimeList busyTimeList = response.getEntity(BusyTime.BusyTimeList.class);
        if (busyTimeList.getError().equals(0)) {
            BusyTime busyTime = busyTimeList.getBusyTimes().get(0);
            busyTime.setPromedId(busyTimeId);
            return busyTime;
        }
        return null;
    }

    @Override
    public void saveBusyTime(BusyTime busyTimes) {
        dao.save(busyTimes);
    }
}
