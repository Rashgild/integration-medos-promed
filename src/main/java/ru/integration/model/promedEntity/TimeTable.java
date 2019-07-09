package ru.integration.model.promedEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeTable {

    @JsonProperty("MedStaffFact_id")
    private Long medStaffFactId;

    @JsonProperty("TimeTableGraf_begTime")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    public Long getMedStaffFactId() {
        return medStaffFactId;
    }

    public void setMedStaffFactId(Long medStaffFactId) {
        this.medStaffFactId = medStaffFactId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static class Data {

        @JsonProperty("data")
        private List<TimeTable> data;

        @JsonProperty("error_code")
        private String errorCode;

        public List<TimeTable> getData() {
            return data;
        }

        public void setData(List<TimeTable> data) {
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
