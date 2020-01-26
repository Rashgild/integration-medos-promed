package ru.rashgild.model.promed;

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
public class EvnVisitResponse {
    @JsonProperty("Evn_id")
    private String evnId;

    @JsonProperty("EvnVizitPL_id")
    private String evnVisitPlId;

    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class ResponseData {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("data")
        private List<EvnVisitResponse> data;

        public boolean isSuccess() {
            return data != null && !data.isEmpty() && error == 0;
        }

        public String getVisitId() {
            return data.get(0).evnVisitPlId;
        }

        public String getEvnId() {
            return data.get(0).evnVisitPlId;
        }
    }
}
