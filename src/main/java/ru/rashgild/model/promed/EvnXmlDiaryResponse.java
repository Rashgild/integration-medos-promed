package ru.rashgild.model.promed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class EvnXmlDiaryResponse {

    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class ResponseData {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("data")
        private List<EvnXmlDiaryResponse> data;

        public boolean isSuccess() {
            return data != null && !data.isEmpty() && error.equals(0);
        }
    }
}
