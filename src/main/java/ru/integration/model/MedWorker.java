package ru.integration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MedWorker {

    private Integer id;

    private Integer medWorkerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedWorkerId() {
        return medWorkerId;
    }

    public void setMedWorkerId(Integer medWorkerId) {
        this.medWorkerId = medWorkerId;
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MedWorkerList {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("data")
        private Map<String, String> medworkers;

        public Integer getError() {
            return error;
        }

        public void setError(Integer error) {
            this.error = error;
        }

        public Map<String, String> getMedworkers() {
            return medworkers;
        }

        public void setMedworkers(Map<String, String> medworkers) {
            this.medworkers = medworkers;
        }
    }
}
