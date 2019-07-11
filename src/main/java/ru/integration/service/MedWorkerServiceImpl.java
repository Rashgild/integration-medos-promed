package ru.integration.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.integration.dao.MedWorkerDao;
import ru.integration.model.MedWorker;

@Service("MedWorkerService")
@Transactional
public class MedWorkerServiceImpl implements MedWorkerService {

    @Autowired
    private MedWorkerDao dao;

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
