package ru.integration.model.promed;

public class EvnVisits {

    private EvnVisit visit;
    private EvnXmlDiary diary;

    public EvnVisit getVisit() {
        return visit;
    }

    public void setVisit(EvnVisit visit) {
        this.visit = visit;
    }

    public EvnXmlDiary getDiary() {
        return diary;
    }

    public void setDiary(EvnXmlDiary diary) {
        this.diary = diary;
    }
}
