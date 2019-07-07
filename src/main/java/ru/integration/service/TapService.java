package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

import ru.integration.model.medosEntity.Tap;

public interface TapService {
    ClientResponse getTapFromMedos(String date);

    void convertingTaps(List<Tap> taps);
}
