package ru.integration.model.promedEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class EvnVisitResponse {
    @JsonProperty("Evn_id")
    private String evnId;

    @JsonProperty("EvnVizitPL_id")
    private String evnVisitPlId;

    public String getEvnId() {
        return evnId;
    }

    public void setEvnId(String evnId) {
        this.evnId = evnId;
    }

    public String getEvnVisitPlId() {
        return evnVisitPlId;
    }

    public void setEvnVisitPlId(String evnVisitPlId) {
        this.evnVisitPlId = evnVisitPlId;
    }

    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class responseData {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("data")
        private List<EvnVisitResponse> data;

        public boolean isSuccess() {
            return data != null && data.size() > 0 && error == 0;
        }

        public String getVisitId() {
            return data.get(0).evnVisitPlId;
        }

        public String getEvnId() {
            return data.get(0).evnVisitPlId;
        }

        public List<EvnVisitResponse> getData() {
            return data;
        }

        public void setData(List<EvnVisitResponse> data) {
            this.data = data;
        }

        public Integer getError() {
            return error;
        }

        public void setError(Integer error) {
            this.error = error;
        }
    }
}
