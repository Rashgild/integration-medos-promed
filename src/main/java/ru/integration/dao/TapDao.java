package ru.integration.dao;

import com.sun.jersey.api.client.ClientResponse;

public interface TapDao {
    ClientResponse getTapFromMedos(String date);

    String sendTapToPromed();

    String sendVisitToPromed();

    String sendDiaryToPromed();
}
