package ru.rashgild.service;

import java.util.List;

import ru.rashgild.model.MedStaff;

public interface MedStaffService {

    List<MedStaff> getMedStaffFromPromed(Integer medStaffId);
}
