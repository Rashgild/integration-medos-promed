package ru.rashgild.service;

import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

import ru.rashgild.model.dto.ResponseModel;
import ru.rashgild.model.dto.TapRequest;
import ru.rashgild.model.medos.Tap;
import ru.rashgild.model.promed.Evn;
import ru.rashgild.model.promed.EvnResponse;
import ru.rashgild.model.promed.EvnVisit;
import ru.rashgild.model.promed.EvnVisitResponse;
import ru.rashgild.model.promed.EvnXmlDiary;
import ru.rashgild.model.promed.EvnXmlDiaryResponse;

public interface TapService {

    ClientResponse getTapFromMedos(String date);

    TapRequest convertingTap(Tap tap);

    List<ResponseModel> exportTaps(List<TapRequest> requestList);

    EvnResponse.ResponseData sendTapToPromed(Evn evn);

    EvnVisitResponse.ResponseData sendVisitToPromed(EvnVisit visit);

    EvnXmlDiaryResponse.ResponseData sendDiaryToPromed(EvnXmlDiary diary);

    String sendDiaryToPromedString(EvnXmlDiary diary);
}
