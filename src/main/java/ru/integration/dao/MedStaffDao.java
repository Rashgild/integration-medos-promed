package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

public interface MedStaffDao {
    ClientResponse getMedstaffById(Integer medstaffId);
}
