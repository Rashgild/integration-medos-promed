package ru.integration.entities.schedule;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ru.integration.util.Methods;

import static ru.integration.util.Methods.checkCode;


@Entity
@Table(name = "freeTime", schema = "public", catalog = "integration")
public class FreeTimeEntity {

    private Integer id;
    private Date timeTableGraf_begDate;
    private Timestamp timeTableGraf_begTime;// (DT, O) – Дата и время начала приема
    private Integer timeTableGraf_id;// (N, Н) – идентификатор свободной бирки.
    private Integer timeTableGraf_Time;// (N,O) – Длительность приема
    private Boolean isSync;
    private Boolean isComplete;
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

    public List<FreeTimeEntity> parseJSON(String json, Date timeTableGraf_begDate) {
        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();
        if (Methods.checkCode(jparse)) {
            List<FreeTimeEntity> timeTableGrafFreeTimes = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {

                try {
                    JsonObject sect = medspecs.getAsJsonObject();
                    FreeTimeEntity timeTableGrafFreeTime = new FreeTimeEntity();
                    timeTableGrafFreeTime.setTimeTableGraf_begDate(timeTableGraf_begDate);
                    timeTableGrafFreeTime.setTimeTableGraf_begTime(Timestamp.valueOf(Methods.checkJsonObj(sect, "TimeTableGraf_begTime")));
                    if (Methods.checkJsonObjGetInteger(sect, "TimeTableGraf_Time") > 0) {
                        timeTableGrafFreeTime.setTimeTableGraf_Time(Methods.checkJsonObjGetInteger(sect, "TimeTableGraf_Time"));
                    }
                    if (Methods.checkJsonObjGetInteger(sect, "TimeTableGraf_id") > 0) {
                        timeTableGrafFreeTime.setTimeTableGraf_id(Methods.checkJsonObjGetInteger(sect, "TimeTableGraf_id"));
                        timeTableGrafFreeTime.setSync(true);
                    }

                    timeTableGrafFreeTimes.add(timeTableGrafFreeTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return timeTableGrafFreeTimes;
        } else return null;
    }
}