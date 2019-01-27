package ru.integration.entities.schedule;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.sql.Date;
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
@Table(name = "freeDate", schema = "public", catalog = "integration")
public class FreeDateEntity {

    private Integer id;
    private Integer MedStaffFact_id;
    private Date TimeTableGraf_begTime;
    private Boolean isSync;
    private Boolean isComplete;


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
    @Column(name = "medStaffFact_id")
    public Integer getMedStaffFact_id() {
        return MedStaffFact_id;
    }

    public void setMedStaffFact_id(Integer medStaffFact_id) {
        MedStaffFact_id = medStaffFact_id;
    }

    @Basic
    @Column(name = "timeTableGraf_begTime")
    public Date getTimeTableGraf_begTime() {
        return TimeTableGraf_begTime;
    }

    public void setTimeTableGraf_begTime(Date timeTableGraf_begTime) {
        TimeTableGraf_begTime = timeTableGraf_begTime;
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

    public List<FreeDateEntity> parseJSON(String json, Integer medStaffFact_id) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        if (Methods.checkCode(jparse)) {
            List<FreeDateEntity> timeTableGrafFreeDates = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {

                try {
                    JsonObject sect = medspecs.getAsJsonObject();
                    FreeDateEntity timeTableGrafFreeDate = new FreeDateEntity();

                    timeTableGrafFreeDate.setTimeTableGraf_begTime(Date.valueOf(Methods.checkJsonObj(sect, "TimeTableGraf_begTime")));
                    timeTableGrafFreeDate.setMedStaffFact_id(medStaffFact_id);
                    timeTableGrafFreeDates.add(timeTableGrafFreeDate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return timeTableGrafFreeDates;
        } else return null;
    }
}
