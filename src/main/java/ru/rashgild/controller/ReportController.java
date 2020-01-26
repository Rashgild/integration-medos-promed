package ru.rashgild.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.rashgild.service.ReportService;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/epicrisis-report-export", method = RequestMethod.GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity epicExport(@RequestParam(value = "date") String date) {
        return ResponseEntity.ok(reportService.readFile(date));
    }

    @RequestMapping(value = "/epicrisis-report-list", method = RequestMethod.GET, produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity reportList() {
        return ResponseEntity.ok(reportService.getReportList());
    }

}
