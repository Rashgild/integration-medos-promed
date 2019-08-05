package ru.integration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.integration.model.medos.ScheduleEntry;
import ru.integration.model.promed.DateTable;
import ru.integration.model.promed.Person;
import ru.integration.model.promed.TimeTable;
import ru.integration.service.PersonService;
import ru.integration.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @Autowired
    private PersonService personService;

    private boolean start = false;
    private Map<String, String> result;

    @RequestMapping(value = "/test")
    public String test() {
        return "hello";
    }

    /**
     * Synchronize patients from promed to medos.
     *
     * @param date date of sync
     * @return ResponseEntity
     */
    @RequestMapping(value = "/sync-by-date", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity syncByDate(@RequestParam(value = "date", required = false) LocalDate date) {

        if(date == null){
            date = LocalDate.now().plusDays(1L);
        }
        System.out.println(date);
        ClientResponse response = service.getDateTables(date);

        if (response.getStatus() == HttpStatus.OK.value()) {
            DateTable.Data data = response.getEntity(DateTable.Data.class);

            if (data != null) {
                List<DateTable> dateTableList = data.getData();
                result = new HashMap<>();
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
                                String req = objectMapper.writeValueAsString(entry);
                                response = service.sendToMedos(entry);
                                String resp = response.getEntity(String.class);

                                System.out.println(">>>>>>>>");
                                System.out.println(req);
                                System.out.println(resp);
                                System.out.println(">>>>>>>>");
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }
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
