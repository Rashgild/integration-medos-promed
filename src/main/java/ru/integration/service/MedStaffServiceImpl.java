package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.integration.dao.MedStaffDao;
import ru.integration.model.MedStaff;

@Service("MedStaffService")
@Transactional
public class MedStaffServiceImpl implements MedStaffService {

    @Autowired
    private MedStaffDao dao;

    @Override
    public List<MedStaff> getMedStaffFromPromed(Integer medStaffId) {
        ClientResponse response = dao.getMedstaffById(medStaffId);
        MedStaff.MedstaffList medstaffList = response.getEntity(MedStaff.MedstaffList.class);
        if (medstaffList.getError().equals(0)) {

            List<MedStaff> medStaffs = medstaffList.getMedStaffList();
            //medStaffs.se(medStaffId);
            //TODO check it, can a list have many entities?
            return medStaffs;
        }
        return null;
    }

    @Override
    public void saveMedstaff(MedStaff medStaff) {
        dao.save(medStaff);
    }
}
