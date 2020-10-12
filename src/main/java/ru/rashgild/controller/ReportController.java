package ru.rashgild.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rashgild.service.ReportService;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping(value = "/epicrisis-report-export", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity epicExport(@RequestParam(value = "date") String date) {
        return ResponseEntity.ok(reportService.readFile(date));
    }

    @GetMapping(value = "/epicrisis-report-list", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity reportList() {
        return ResponseEntity.ok(reportService.getReportList());
    }

}
