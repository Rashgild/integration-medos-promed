package ru.rashgild.dao;

import com.sun.jersey.api.client.ClientResponse;

public interface DictionaryDao {
    ClientResponse getLpuDictionarySection();

    ClientResponse getDictionaryMedstaff(Long medPersonalId, Long lpuSectionId);
}
