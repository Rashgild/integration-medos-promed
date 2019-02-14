package ru.integration.service;

import ru.integration.model.MedWorker;

public interface MedWorkerService {

    MedWorker getMedWorkerFromPromed(Integer medWorkerId);

    void saveMedworker(MedWorker medWorker);
}
