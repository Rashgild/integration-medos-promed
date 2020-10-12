package ru.rashgild.service;

import com.sun.jersey.api.client.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rashgild.dao.MedStaffDao;
import ru.rashgild.model.MedStaff;

import java.util.ArrayList;
import java.util.List;

@Service("MedStaffService")
@Transactional
@RequiredArgsConstructor
public class MedStaffServiceImpl implements MedStaffService {

    private final MedStaffDao dao;

    @Override
    public List<MedStaff> getMedStaffFromPromed(Integer medStaffId) {
        ClientResponse response = dao.getMedstaffById(medStaffId);
        MedStaff.MedstaffList medstaffList = response.getEntity(MedStaff.MedstaffList.class);
        if (medstaffList.getError().equals(0)) {

            //TODO check it, can a list have many entities?
            return medstaffList.getStaffList();
        }
        return new ArrayList<>();
    }
}
