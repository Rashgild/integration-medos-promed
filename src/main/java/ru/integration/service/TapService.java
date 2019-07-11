package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import ru.integration.model.medos.Tap;

public interface TapService {

    ClientResponse getTapFromMedos(String date);

    void convertingTap(Tap tap);
}
