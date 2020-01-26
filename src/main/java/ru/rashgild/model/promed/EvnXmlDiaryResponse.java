package ru.rashgild.model.promed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class EvnXmlDiaryResponse {

    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("data")
        private List<EvnXmlDiaryResponse> data;

        public boolean isSuccess() {
            return data != null && data.size() > 0 && error.equals(0);
        }

        public List<EvnXmlDiaryResponse> getData() {
            return data;
        }

        public void setData(List<EvnXmlDiaryResponse> data) {
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
