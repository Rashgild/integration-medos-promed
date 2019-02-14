package ru.integration.service;

import java.util.List;

import ru.integration.model.MedStaff;

public interface MedStaffService {

    List<MedStaff> getMedStaffFromPromed(Integer medStaffId);

    void saveMedstaff(MedStaff medStaff);
}
