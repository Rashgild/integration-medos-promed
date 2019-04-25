package ru.integration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.integration.model.Person;
import ru.integration.model.schedule.BusyDateTime;
import ru.integration.model.schedule.BusyTime;
import ru.integration.service.BusyDateTimeService;
import ru.integration.service.BusyTimeService;
import ru.integration.service.PersonService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private BusyDateTimeService busyDateTimeService;

    @Autowired
    private BusyTimeService busyTimeService;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/getFromPromed", method = RequestMethod.GET, produces = "application/json")
    public void sessionId() {

        //Забираем даты
        List<BusyDateTime> busyDateTimes = busyDateTimeService.getBusyDateTimeFromPromed("2019-01-15", "2019-01-15");
        //busyDateTimeService.saveListBusyDateTime(busyDateTimes);

        //Забираем времена и пациентов
        int i=1;
        for (BusyDateTime busyDateTime : busyDateTimes) {
            BusyTime busyTime = busyTimeService.getBusyTimeFromPromed(busyDateTime.getTimeId());
       /*     System.out.println(busyTime.getPromedId());
            Person person = personService.getPersonFromPromed(busyDateTime.getPersonId());
            System.out.println(person.getPromedId());*/
            System.out.println(i);
            i++;
        }
    }
}
