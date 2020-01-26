package ru.rashgild.service;

import java.util.List;

import ru.rashgild.model.promed.dictionary.LpuSectionDictionary;
import ru.rashgild.model.promed.dictionary.MedstaffDictionary;

public interface DictionaryService {
    List<LpuSectionDictionary> getLpuSections();

    MedstaffDictionary getDictionaryMedstaff(Long lpuSectionId, Long medPersonalId);
}
