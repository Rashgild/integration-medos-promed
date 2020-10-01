package ru.rashgild.service;

import com.sun.jersey.api.client.ClientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.rashgild.config.Config;
import ru.rashgild.dao.DictionaryDao;
import ru.rashgild.model.promed.dictionary.LpuSectionDictionary;
import ru.rashgild.model.promed.dictionary.MedstaffDictionary;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryDao dictionaryDao;

    private List<LpuSectionDictionary> lpuSections;

    public DictionaryServiceImpl(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    {
        Config.disableSslVerification();
    }

    @PostConstruct
    private void init() {
        System.out.println("initialize dictionaries");
        lpuSectionCacheInit();
    }

    private void lpuSectionCacheInit() {
        ClientResponse response = dictionaryDao.getLpuDictionarySection();
        if (response.getStatus() == HttpStatus.OK.value()) {
            LpuSectionDictionary.ResponseData lpuSections = response.getEntity(LpuSectionDictionary.ResponseData.class);
            if (lpuSections != null && !lpuSections.getData().isEmpty()) {
                this.lpuSections = lpuSections.getData();
                System.out.println("lpu sections success initialisation");
            } else {
                System.err.println(lpuSections.getErrorMessage());
            }
        }
    }

    @Override
    public List<LpuSectionDictionary> getLpuSections() {
        return lpuSections;
    }

    @Override
    public MedstaffDictionary getDictionaryMedstaff(Long lpuSectionId, Long medPersonalId) {
        ClientResponse response = dictionaryDao.getDictionaryMedstaff(medPersonalId, lpuSectionId);
        if (response.getStatus() == HttpStatus.OK.value()) {
            MedstaffDictionary.ResponseData medstaff = response.getEntity(MedstaffDictionary.ResponseData.class);
            if (medstaff != null && !medstaff.getData().isEmpty()) {

                MedstaffDictionary medstaffDictionary = medstaff.getData().stream().findFirst().get();
                medstaffDictionary.setLpuSectionId(lpuSectionId);
                return medstaffDictionary;
            } else {
                System.err.println(medstaff.getErrorMessage());
            }
        }
        return null;
    }

}
