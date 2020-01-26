package ru.rashgild.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rashgild.model.dto.ResponseModel;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    private static final String JSON = ".json";

    @Override
    public void saveFile(Object object, String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("report/" + name + JSON), object);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<ResponseModel> readFile(String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("report/" + name + JSON), new TypeReference<List<ResponseModel>>() {
            });
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Report> getReportList() {
        File dir = new File("report");
        File[] arrFiles = dir.listFiles();
        List<Report> reports = new ArrayList<>();
        if (arrFiles != null) {
            for (File file : arrFiles) {
                Report report = new Report(file.getName().replace(JSON, ""));
                reports.add(report);
            }
        }
        return reports;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @XmlRootElement
    @Getter
    @Setter
    static class Report {

        public Report(String date) {
            this.date = date;
            this.link = "<a href=\"report/epicrisis-export?" + date + JSON + "\">" + date + "</a>";

        }

        private String date;
        private String link;
    }
}
