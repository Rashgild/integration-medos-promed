package ru.rashgild.model.promed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class DateTable {

    @JsonProperty("TimeTableGraf_id")
    private int dateId;

    @JsonProperty("Person_id")
    private Long personId;

    @Getter
    @Setter
    public static class Data {

        @JsonProperty("data")
        private List<DateTable> dateTables;

        @JsonProperty("error_code")
        private String errorCode;
    }
}
