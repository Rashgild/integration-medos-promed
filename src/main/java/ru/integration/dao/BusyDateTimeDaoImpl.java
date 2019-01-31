package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import java.util.List;
import javax.ws.rs.core.MediaType;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ru.integration.model.schedule.BusyDateTime;
import ru.integration.util.GlobalVariables;

public class BusyDateTimeDaoImpl extends AbstractDao implements BusyDateTimeDao {

    @Override
    public ClientResponse getBusyDateTimeFromPromed(String timeBegin, String timeEnd) {
        //TODO AUTH SESSION ID
        return client
                .resource(environment.getProperty("promed.endpoint"))
                .path("api/TimeTableGraf/TimeTableGrafbyMO")
                .queryParam("Lpu_id", environment.getProperty("lpu.id"))
                .queryParam("TimeTableGraf_beg", timeBegin)
                .queryParam("TimeTableGraf_end", timeEnd)
                .header("Cookie", "PHPSESSID=" + GlobalVariables.sessionId)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);
    }

    @Override
    public void save(BusyDateTime busyDateTime) {
        persist(busyDateTime);
    }

    @Override
    public List<BusyDateTime> getNotSyncedListBusyDateTime() {
        Criteria criteria = getSession().createCriteria(BusyDateTime.class);
        criteria.add(Restrictions.eq("sync", false));
        return (List<BusyDateTime>) criteria.list();
    }
}
