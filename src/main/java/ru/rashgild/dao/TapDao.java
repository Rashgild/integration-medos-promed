package ru.rashgild.dao;

import com.sun.jersey.api.client.ClientResponse;
import ru.rashgild.model.promed.Evn;
import ru.rashgild.model.promed.EvnVisit;
import ru.rashgild.model.promed.EvnXmlDiary;

public interface TapDao {

    ClientResponse getTapFromMedos(String date);

    void sendResultToMedos(String medcaseId, String evnId);

    ClientResponse sendTapToPromed(Evn evn);

    ClientResponse sendVisitToPromed(EvnVisit evnVisit);

    ClientResponse sendDiaryToPromed(EvnXmlDiary evnXmlDiary);
}
