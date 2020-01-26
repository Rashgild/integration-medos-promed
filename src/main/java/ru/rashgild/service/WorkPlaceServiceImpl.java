package ru.rashgild.service;

import com.sun.jersey.api.client.ClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rashgild.dao.WorkPlaceDao;
import ru.rashgild.model.promed.WorkPlaceDto;

import java.util.ArrayList;
import java.util.List;

@Service("WorkPlaceService")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class WorkPlaceServiceImpl implements WorkPlaceService {

    private final WorkPlaceDao workPlaceDao;

    @Override
    public List<WorkPlaceDto> getWorkPlaceByPersonId(int personId) {
        ClientResponse response = workPlaceDao.getWorkPlaceByPersonId(personId);
        WorkPlaceDto.WorkPlacesDto workPlacesDto = response.getEntity(WorkPlaceDto.WorkPlacesDto.class);

        if (workPlacesDto != null && workPlacesDto.getError().equals("0") && workPlacesDto.getWorkPlaceDtoList() != null) {
            return workPlacesDto.getWorkPlaceDtoList();
        } else {
            log.error(workPlacesDto != null ? workPlacesDto.getErrorMessage() : "null");
        }
        return new ArrayList<>();
    }
}
