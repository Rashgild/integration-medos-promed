package ru.rashgild.service;

import java.util.List;

import ru.rashgild.model.promed.WorkPlaceDto;

public interface WorkPlaceService {
    List<WorkPlaceDto> getWorkPlaceByPersonId(int personId);
}
