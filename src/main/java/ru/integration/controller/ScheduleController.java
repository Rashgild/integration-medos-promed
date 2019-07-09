package ru.integration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.integration.model.medosEntity.ScheduleEntry;
import ru.integration.model.promedEntity.DateTable;
import ru.integration.model.promedEntity.Person;
import ru.integration.model.promedEntity.TimeTable;
import ru.integration.service.PersonService;
import ru.integration.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/sync-on-date", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity epicExport(@RequestParam(value = "date") String date) {

        System.out.println(date);
        ClientResponse response = service.getDateTables(date);

        if (response.getStatus() == HttpStatus.OK.value()) {
            DateTable.Data data = response.getEntity(DateTable.Data.class);

            if (data != null) {
                List<DateTable> dateTableList = data.getData();

                for (DateTable dateTable : dateTableList) {
                    Person person = personService.getPersonById(dateTable.getPersonId());
                    if (person != null) {
                        response = service.getTimeByDateId(dateTable.getDateId());
                        if (response.getStatus() == HttpStatus.OK.value()) {
                            TimeTable.Data timeDate = response.getEntity(TimeTable.Data.class);
                            TimeTable timeTable = timeDate.getData().get(0);
                            ScheduleEntry entry = service.createMedosModel(person, timeTable);

                            ObjectMapper objectMapper = new ObjectMapper();
                            try {
                                System.out.println(objectMapper.writeValueAsString(entry));
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }
                            response = service.sendToMedos(entry);
                            System.out.println(response.getEntity(String.class));
                        }
                    } else {
                        System.out.println("person is null:" + dateTable.getPersonId());
                    }
                }

            } else {
                return new ResponseEntity("{\"status\":\"За данный период записей не найдено\"}", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity("{\"status\":\"Произошла ошибка\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
