package ru.rashgild.model.dto;

import java.util.List;

import ru.rashgild.model.promed.Evn;
import ru.rashgild.model.promed.EvnVisits;
import ru.rashgild.model.promed.EvnXmlDiary;

public class TapRequest {

    private Evn evn;
    private EvnXmlDiary evnDiary;
    private List<EvnVisits> visits;
    private boolean hasError;
    private ResponseModel responseModels;

    public Evn getEvn() {
        return evn;
    }

    public void setEvn(Evn evn) {
        this.evn = evn;
    }

    public EvnXmlDiary getEvnDiary() {
        return evnDiary;
    }

    public void setEvnDiary(EvnXmlDiary evnDiary) {
        this.evnDiary = evnDiary;
    }

    public List<EvnVisits> getVisits() {
        return visits;
    }

    public void setVisits(List<EvnVisits> visits) {
        this.visits = visits;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public ResponseModel getResponseModels() {
        return responseModels;
    }

    public void setResponseModels(ResponseModel responseModels) {
        this.hasError = true;
        this.responseModels = responseModels;
    }
}
