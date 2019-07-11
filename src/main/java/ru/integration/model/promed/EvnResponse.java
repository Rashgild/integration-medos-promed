package ru.integration.model.promed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class EvnResponse {

    @JsonProperty("EvnPLBase_id")
    private String evnPlBaseId;

    @JsonProperty("EvnVizitPL_id")
    private String evnVisitPlId;

    public String getEvnPlBaseId() {
        return evnPlBaseId;
    }

    public void setEvnPlBaseId(String evnPlBaseId) {
        this.evnPlBaseId = evnPlBaseId;
    }

    public String getEvnVisitPlId() {
        return evnVisitPlId;
    }

    public void setEvnVisitPlId(String evnVisitPlId) {
        this.evnVisitPlId = evnVisitPlId;
    }

    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("data")
        private List<EvnResponse> data;

        public boolean isSuccess() {
            return data != null && data.size() > 0 && error == 0;
        }

        public String getVisitId() {
            return data.get(0).evnVisitPlId;
        }

        public String getBaseId() {
            return data.get(0).evnPlBaseId;
        }

        public List<EvnResponse> getData() {
            return data;
        }

        public void setData(List<EvnResponse> data) {
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
