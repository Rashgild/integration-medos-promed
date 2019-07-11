package ru.integration.controller;

import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.integration.model.medos.Tap;
import ru.integration.service.TapService;

@RestController
@RequestMapping("/ambulatory")
public class AmbulatoryEpicrisisController {

    @Autowired
    private TapService tapService;

    /**
     * Export Ambulatory epicrisis from medos to promed .
     *
     * @param date date of sync
     * @return ResponseEntity
     */
    @RequestMapping(value = "/epicrisis-export", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity epicExport(@RequestParam(value = "date") String date) {

        System.out.println(date);
        ClientResponse response = tapService.getTapFromMedos(date);

        if (response.getStatus() == HttpStatus.OK.value()) {
            Tap.TapList tapList = response.getEntity(Tap.TapList.class);

            if (tapList != null) {
                List<Tap> taps = tapList.getTapList();

                for (Tap tap : taps) {
                    // tapService.convertingTaps(taps);
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
