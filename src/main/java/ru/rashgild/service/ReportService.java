package ru.rashgild.service;

import java.util.List;

import ru.rashgild.model.dto.ResponseModel;

public interface ReportService {
    void saveFile(Object object, String name);

    List<ResponseModel> readFile(String name);

    List<ReportServiceImpl.Report> getReportList ();
}
