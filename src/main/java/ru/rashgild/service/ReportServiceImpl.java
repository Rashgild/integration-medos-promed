package ru.rashgild.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Service;

import ru.rashgild.model.dto.ResponseModel;

@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public void saveFile(Object object, String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("report/" + name + ".json"), object);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<ResponseModel> readFile(String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("report/" + name + ".json"), new TypeReference<List<ResponseModel>>() {
            });
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Report> getReportList() {
        File dir = new File("report");
        File[] arrFiles = dir.listFiles();
        List<Report> reports = new ArrayList<>();
        for (File file : arrFiles) {
            Report report = new Report(file.getName().replace(".json", ""));
            reports.add(report);
        }
        return reports;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @XmlRootElement
    static class Report {

        public Report(String date) {
            this.date = date;
            this.link = "<a href=\"report/epicrisis-export?" + date + ".json\">" + date + "</a>";

        }

        private String date;
        private String link;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
