package ru.rashgild.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rashgild.model.dto.ResponseModel;
import ru.rashgild.model.medos.ScheduleEntry;
import ru.rashgild.model.promed.DateTable;
import ru.rashgild.model.promed.Person;
import ru.rashgild.model.promed.TimeTable;
import ru.rashgild.service.AuthService;
import ru.rashgild.service.PersonService;
import ru.rashgild.service.ScheduleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
@Slf4j
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @Autowired
    private PersonService personService;

    @Autowired
    private AuthService authService;

    /**
     * Synchronize patients from promed to medos.
     *
     * @param date date of sync
     * @return ResponseEntity
     */
    @GetMapping(value = "/sync-by-date", produces = "application/json")
    public ResponseEntity syncByDate(@RequestParam(value = "date", required = false) String date) {

        if (date == null) {
            date = String.valueOf(LocalDate.now().plusDays(1L));
        }
        log.info(date);

        ClientResponse response = service.getDateTables(date);
        List<Object> results = new ArrayList<>();
        if (response.getStatus() == HttpStatus.OK.value()) {
            DateTable.Data data = response.getEntity(DateTable.Data.class);

            if (data != null) {
                List<DateTable> dateTableList = data.getDateTables();
                for (DateTable dateTable : dateTableList) {
                    Person person = personService.getPersonById(dateTable.getPersonId());
                    if (person != null) {
                        response = service.getTimeByDateId(dateTable.getDateId());
                        if (response.getStatus() == HttpStatus.OK.value()) {
                            TimeTable.Data timeDate = response.getEntity(TimeTable.Data.class);
                            TimeTable timeTable = timeDate.getTimeTables().get(0);
                            ScheduleEntry entry = service.createMedosModel(person, timeTable);

                            ObjectMapper objectMapper = new ObjectMapper();
                            try {
                                String req = objectMapper.writeValueAsString(entry);
                                response = service.sendToMedos(entry);
                                String resp = response.getEntity(String.class);

                                Map<String, String> json = new HashMap<>();
                                json.put("request", req);
                                json.put("response", resp);
                                results.add(json);
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        log.info("person is null:" + dateTable.getPersonId());
                    }
                }
            } else {
                return new ResponseEntity(new ResponseModel("За данный период записей не найдено"), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(new ResponseModel("Что-то пошло не так"), HttpStatus.BAD_REQUEST);
        }

        authService.logout();
        Map<String, List<Object>> result = new HashMap<>();
        result.put("data", results);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
