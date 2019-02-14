package ru.integration.model.schedule;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "BusyTime")
public class BusyTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "promed_id")
    private Integer promedId;

    @Basic
    @JsonProperty("MedStaffFact_id")
    @Column(name = "medstaff_fact_id")
    private Integer medstaffFactId;

    @Basic
    @JsonProperty("TimeTableGraf_begTime")
    @Column(name = "begin_date_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginDateTime;

    @Basic
    @JsonProperty("TimetableGraf_Time")
    @Column(name = "timeGrafTime")
    private Integer timeGrafTime;

    @Basic
    @JsonProperty("TimeTableType_id")
    @Column(name = "time_type_id")
    private Integer timeTypeId;

    @Basic
    @JsonProperty("TimeTableGraf_IsDop")
    @Column(name = "addition")
    private Boolean isAddition;

    //TODO преобразование в date и time ?


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPromedId() {
        return promedId;
    }

    public void setPromedId(Integer promedId) {
        this.promedId = promedId;
    }

    public Integer getMedstaffFactId() {
        return medstaffFactId;
    }

    public void setMedstaffFactId(Integer medstaffFactId) {
        this.medstaffFactId = medstaffFactId;
    }

    public LocalDateTime getBeginDateTime() {
        return beginDateTime;
    }

    public void setBeginDateTime(LocalDateTime beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public Integer getTimeGrafTime() {
        return timeGrafTime;
    }

    public void setTimeGrafTime(Integer timeGrafTime) {
        this.timeGrafTime = timeGrafTime;
    }

    public Integer getTimeTypeId() {
        return timeTypeId;
    }

    public void setTimeTypeId(Integer timeTypeId) {
        this.timeTypeId = timeTypeId;
    }

    public Boolean getAddition() {
        return isAddition;
    }

    public void setAddition(Boolean addition) {
        isAddition = addition;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BusyTimeList {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("data")
        private List<BusyTime> busyTimes;

        public List<BusyTime> getBusyTimes() {
            return busyTimes;
        }

        public void setBusyTimes(List<BusyTime> busyTimes) {
            this.busyTimes = busyTimes;
        }

        public Integer getError() {
            return error;
        }

        public void setError(Integer error) {
            this.error = error;
        }
    }
}

