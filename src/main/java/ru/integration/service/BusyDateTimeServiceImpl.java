package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.integration.dao.BusyDateTimeDao;
import ru.integration.model.schedule.BusyDateTime;

@Service("BusyDateTimeService")
@Transactional
public class BusyDateTimeServiceImpl implements BusyDateTimeService {

    @Autowired
    private BusyDateTimeDao dateTimeDao;

    @Override
    public List<BusyDateTime> getBusyDateTimeFromPromed(String dateBegin, String dateEnd) {
        ClientResponse response = dateTimeDao.getBusyDateTimeFromPromed(dateBegin, dateBegin);
        return response.getEntity(BusyDateTime.BusyDateTimeList.class).getBusyDateTimeList();
    }

    @Override
    public BusyDateTime.BusyDateTimeList getBusyDateTimeListFromPromed(String dateBegin, String dateEnd) {
        ClientResponse response = dateTimeDao.getBusyDateTimeFromPromed(dateBegin, dateBegin);
        return response.getEntity(BusyDateTime.BusyDateTimeList.class);
    }
}
