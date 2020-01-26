package ru.rashgild.model.medos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@Getter
@Setter
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
    private List<Visit> visits;

    public int getVisitsCount() {
        return getVisits().size();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class TapList {
        @JsonProperty("tap")
        private List<Tap> taps;
    }
}
