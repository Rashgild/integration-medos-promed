package ru.integration.model.jsonEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class Tap {

    @JsonProperty("patient")
    private Patient patient;

    @JsonProperty("EvnPL_NumCard")
    private Integer numCard;

    @JsonProperty("EvnPL_IsFinish")
    private Integer isFinish;

    @JsonProperty("Diag_lid")
    private Integer diagLid;

    @JsonProperty("ResultClass_id")
    private Integer resultClass;

    @JsonProperty("visits")
    private  List<Visit>  visits;

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getNumCard() {
        return numCard;
    }

    public void setNumCard(Integer numCard) {
        this.numCard = numCard;
    }

    public Integer isFinish() {
        return isFinish;
    }

    public void setFinish(Integer finish) {
        isFinish = finish;
    }

 /*   public void setFinish(int finish) {
        isFinish = finish == 1;
    }*/

    public Integer getDiagLid() {
        return diagLid;
    }

    public void setDiagLid(Integer diagLid) {
        this.diagLid = diagLid;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TapList {

        @JsonProperty("tap")
        private List<Tap> tapList;

        public List<Tap> getTapList() {
            return tapList;
        }

        public void setTapList(List<Tap> tapList) {
            this.tapList = tapList;
        }
    }
}
