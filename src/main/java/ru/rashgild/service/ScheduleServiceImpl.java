package ru.rashgild.service;

import com.sun.jersey.api.client.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rashgild.dao.ScheduleDao;
import ru.rashgild.model.medos.ScheduleEntry;
import ru.rashgild.model.promed.Person;
import ru.rashgild.model.promed.TimeTable;

@Service("CalendarService")
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao dao;

    @Override
    public ClientResponse getDateTables(String date) {
        return dao.getDate(date);
    }

    @Override
    public ClientResponse getTimeByDateId(int dateId) {
        return dao.getTimeByDateId(dateId);
    }

    @Override
    public ScheduleEntry createMedosModel(Person person, TimeTable timeTable) {
        ScheduleEntry scheduleEntry = new ScheduleEntry();

        scheduleEntry.setLastname(person.getSurName());
        scheduleEntry.setFirstname(person.getFirName());
        scheduleEntry.setMiddlename(person.getSecName());
        scheduleEntry.setBirthday(person.getBirthday());
        scheduleEntry.setRecordCalendarTime(timeTable.getDateTime().toLocalTime());
        scheduleEntry.setRecordCalendarDate(timeTable.getDateTime().toLocalDate());
        scheduleEntry.setDoctorPromedId(timeTable.getMedStaffFactId());
        scheduleEntry.setPhone(person.getPhone());
        scheduleEntry.setReserveCode("PROMED");
        scheduleEntry.setRecordType("PROMED");
        scheduleEntry.setToken("66405d38-a173-4cb7-a1b6-3ada51c16ac5");

        return scheduleEntry;
    }

    @Override
    public ClientResponse sendToMedos(ScheduleEntry model) {
        return dao.sendResultToMedos(model);
    }
}
