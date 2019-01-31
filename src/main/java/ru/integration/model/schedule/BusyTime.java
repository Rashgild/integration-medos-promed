package ru.integration.model.schedule;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@Entity
@Table(name = "BusyDateTime")
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
}
