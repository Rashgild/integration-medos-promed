package ru.rashgild.service;

import com.sun.jersey.api.client.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.rashgild.config.Config;
import ru.rashgild.dao.DictionaryDao;
import ru.rashgild.model.promed.dictionary.LpuSectionDictionary;
import ru.rashgild.model.promed.dictionary.MedstaffDictionary;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryDao dictionaryDao;

    private List<LpuSectionDictionary> lpuSections;

    public DictionaryServiceImpl(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    static {
        Config.disableSslVerification();
    }

    @PostConstruct
    private void init() {
        log.info("initialize dictionaries");
        try {
            lpuSectionCacheInit();
            log.info("initialize dictionaries success");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }

    private void lpuSectionCacheInit() {
        ClientResponse response = dictionaryDao.getLpuDictionarySection();
        if (response.getStatus() == HttpStatus.OK.value()) {
            LpuSectionDictionary.ResponseData sections = response.getEntity(LpuSectionDictionary.ResponseData.class);
            if (sections != null && sections.getData() != null && !sections.getData().isEmpty()) {
                this.lpuSections = sections.getData();
                log.info("lpu sections success initialisation: {}", sections);
            } else {
                log.error(sections != null ? sections.getErrorMessage() : "NPE");
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
            if (medstaff != null && medstaff.getData() != null && !medstaff.getData().isEmpty()) {
                MedstaffDictionary medstaffDictionary = medstaff.getData().get(0);
                medstaffDictionary.setLpuSectionId(lpuSectionId);
                return medstaffDictionary;
            } else {
                log.error("err: {}", medstaff);
            }
        }
        return null;
    }

}
