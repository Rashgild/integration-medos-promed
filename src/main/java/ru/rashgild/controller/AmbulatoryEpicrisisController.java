package ru.rashgild.controller;

import com.sun.jersey.api.client.ClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rashgild.model.dto.ResponseModel;
import ru.rashgild.model.dto.TapRequest;
import ru.rashgild.model.medos.Tap;
import ru.rashgild.service.ReportService;
import ru.rashgild.service.TapService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/ambulatory")
@RequiredArgsConstructor
@Slf4j
public class AmbulatoryEpicrisisController {

    private final TapService tapService;

    private final ReportService reportService;

    /**
     * Export Ambulatory epicrisis from medos to promed .
     */
    @GetMapping(value = "/epicrisis-export", produces = "application/json")
    public ResponseEntity epicExport(@RequestParam(value = "date") String date,
                                     @RequestParam(value = "dateTo", required = false) String dateTo) {

        LocalDate from = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (dateTo != null) {
            LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            List<ResponseModel> result = new ArrayList<>();
            for (LocalDate start = from; !start.equals(to.plusDays(1L)); start = start.plusDays(1L)) {
                result.addAll(send(start.toString()));
            }
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.ok(send(date));
        }
    }

    private List<ResponseModel> send(String date) {
        log.info("Date = " + date);
        ClientResponse response = tapService.getTapFromMedos(date);
        if (response.getStatus() == HttpStatus.OK.value()) {
            Tap.TapList tapList = response.getEntity(Tap.TapList.class);
            List<TapRequest> tapRequests = new ArrayList<>();

            if (tapList != null) {
                List<Tap> taps = tapList.getTaps();

                for (Tap tap : taps) {
                    TapRequest tapRequest = tapService.convertingTap(tap);
                    tapRequests.add(tapRequest);
                }
            } else {
                return Stream.of(new ResponseModel("", "За данный период записей не найдено")).collect(Collectors.toList());
            }
            List<ResponseModel> responseModels = tapRequests.stream().filter(TapRequest::isHasError)
                    .map(TapRequest::getResponseModels).collect(Collectors.toList());

            responseModels.addAll(tapService.exportTaps(tapRequests));
            reportService.saveFile(responseModels, date);
            return responseModels;
        } else {
            return Stream.of(new ResponseModel("", "BadRequest")).collect(Collectors.toList());
        }
    }
}
