package ru.integration.entities.schedule;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "busyTime", schema = "public", catalog = "integration")
public class BusyTimeEntity {

    private Integer id;
    private Date timeTableGraf_begDate;
    private Time time;
    private Timestamp timeTableGraf_begTime;// (DT, O) – Дата и время начала приема
    private Integer timeTableGraf_id;// (N, Н) – идентификатор свободной бирки.
    private Integer timeTableGraf_Time;// (N,O) – Длительность приема
    private Boolean isSync;
    private Boolean isComplete;
    private Integer MedStaffFact_id;
    private Integer patient_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "timeTableGraf_begDate")
    public Date getTimeTableGraf_begDate() {
        return timeTableGraf_begDate;
    }
    public void setTimeTableGraf_begDate(Date timeTableGraf_begDate) {
        this.timeTableGraf_begDate = timeTableGraf_begDate;
    }

    @Basic
    @Column(name = "timeTableGraf_id")
    public Integer getTimeTableGraf_id() {
        return timeTableGraf_id;
    }
    public void setTimeTableGraf_id(Integer timeTableGraf_id) {
        this.timeTableGraf_id = timeTableGraf_id;
    }

    @Basic
    @Column(name = "time")
    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }

    @Basic
    @Column(name = "timeTableGraf_begTime")
    public Timestamp getTimeTableGraf_begTime() {
        return timeTableGraf_begTime;
    }
    public void setTimeTableGraf_begTime(Timestamp timeTableGraf_begTime) {
        this.timeTableGraf_begTime = timeTableGraf_begTime;
    }

    @Basic
    @Column(name = "timeTableGraf_Time")
    public Integer getTimeTableGraf_Time() {
        return timeTableGraf_Time;
    }
    public void setTimeTableGraf_Time(Integer timeTableGraf_Time) {
        this.timeTableGraf_Time = timeTableGraf_Time;
    }

    @Basic
    @Column(name = "isSync")
    public Boolean getSync() {
        return isSync;
    }
    public void setSync(Boolean sync) {
        isSync = sync;
    }

    @Basic
    @Column(name = "isComplete")
    public Boolean getComplete() {
        return isComplete;
    }
    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    @Basic
    @Column(name = "patient_id")
    public Integer getPatient_id() {
        return patient_id;
    }
    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }


    @Basic
    @Column(name = "medStaffFact_id")
    public Integer getMedStaffFact_id() {
        return MedStaffFact_id;
    }
    public void setMedStaffFact_id(Integer medStaffFact_id) {
        MedStaffFact_id = medStaffFact_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusyTimeEntity that = (BusyTimeEntity) o;

        if (timeTableGraf_id != null ? !timeTableGraf_id.equals(that.timeTableGraf_id) : that.timeTableGraf_id != null)
            return false;
        return patient_id != null ? patient_id.equals(that.patient_id) : that.patient_id == null;
    }

    @Override
    public int hashCode() {
        int result = timeTableGraf_id != null ? timeTableGraf_id.hashCode() : 0;
        result = 31 * result + (patient_id != null ? patient_id.hashCode() : 0);
        return result;
    }
}