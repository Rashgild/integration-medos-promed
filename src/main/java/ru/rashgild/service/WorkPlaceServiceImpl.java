package ru.rashgild.service;

import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.rashgild.dao.WorkPlaceDao;
import ru.rashgild.model.promed.WorkPlaceDto;

@Service("WorkPlaceService")
@Transactional
public class WorkPlaceServiceImpl implements WorkPlaceService {

    private final WorkPlaceDao workPlaceDao;

    public WorkPlaceServiceImpl(WorkPlaceDao workPlaceDao) {
        this.workPlaceDao = workPlaceDao;
    }

    @Override
    public List<WorkPlaceDto> getWorkPlaceByPersonId(int personId) {
        ClientResponse response = workPlaceDao.getWorkPlaceByPersonId(personId);
        WorkPlaceDto.WorkPlacesDto workPlacesDto = response.getEntity(WorkPlaceDto.WorkPlacesDto.class);

        if (workPlacesDto.getError().equals("0") && workPlacesDto != null && workPlacesDto.getWorkPlaceDtoList() != null) {
            return workPlacesDto.getWorkPlaceDtoList();
        } else {
            System.err.println(workPlacesDto.getErrorMessage());
        }
        return null;
    }
}
