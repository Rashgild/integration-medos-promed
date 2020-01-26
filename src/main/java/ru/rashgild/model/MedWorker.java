package ru.rashgild.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MedWorker {

    private Integer id;

    private Integer medWorkerId;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class MedWorkerList {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("data")
        private Map<String, String> medworkers;
    }
}
