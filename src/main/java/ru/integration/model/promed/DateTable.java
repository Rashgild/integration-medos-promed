package ru.integration.model.promed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DateTable {

    @JsonProperty("TimeTableGraf_id")
    private int dateId;

    @JsonProperty("Person_id")
    private int personId;

    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public static class Data {

        @JsonProperty("data")
        private List<DateTable> data;

        @JsonProperty("error_code")
        private String errorCode;

        public List<DateTable> getData() {
            return data;
        }

        public void setData(List<DateTable> data) {
            this.data = data;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
    }
}
