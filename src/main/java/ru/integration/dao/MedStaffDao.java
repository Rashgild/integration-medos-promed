package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

import ru.integration.model.MedStaff;

public interface MedStaffDao {
    ClientResponse getMedstaffById(Integer medstaffId);

    void save(MedStaff medStaff);
}
