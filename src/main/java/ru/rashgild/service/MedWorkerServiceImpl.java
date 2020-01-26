package ru.rashgild.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rashgild.dao.MedWorkerDao;
import ru.rashgild.model.MedWorker;

@Service("MedWorkerService")
@Transactional
@RequiredArgsConstructor
public class MedWorkerServiceImpl implements MedWorkerService {

    private final MedWorkerDao dao;

    @Override
    public MedWorker getMedWorkerFromPromed(Integer medWorkerId) {
        ClientResponse response = dao.getMedworkerById(medWorkerId);
        MedWorker.MedWorkerList medWorkers = response.getEntity(MedWorker.MedWorkerList.class);
        if (medWorkers.getError().equals(0)) {

            MedWorker medWorker = new ObjectMapper().convertValue(medWorkers.getMedworkers(), MedWorker.class);
            medWorker.setMedWorkerId(medWorkerId);
            return medWorker;
        }
        return null;
    }
}
