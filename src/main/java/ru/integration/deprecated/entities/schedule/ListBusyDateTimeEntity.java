package ru.integration.deprecated.entities.schedule;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ru.integration.dao.DaoImpl;
import ru.integration.util.Methods;

import static ru.integration.util.Methods.checkCode;

//TODO DEPRECATED
@Entity
@Table(name = "listBusyDateTime", schema = "public", catalog = "integration")
public class ListBusyDateTimeEntity {

    private Integer id;
    private Integer timeTableGraf_id;
    private Integer person_id;
    private Boolean isSync;

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
    @Column(name = "timeTableGraf_id")
    public Integer getTimeTableGraf_id() {
        return timeTableGraf_id;
    }

    public void setTimeTableGraf_id(Integer timeTableGraf_id) {
        this.timeTableGraf_id = timeTableGraf_id;
    }

    @Basic
    @Column(name = "person_id")
    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }


    @Basic
    @Column(name = "isSync")
    public Boolean getSync() {
        return isSync;
    }

    public void setSync(Boolean sync) {
        isSync = sync;
    }

    private List<ListBusyDateTimeEntity> parseJson(String json) {
        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();
        if (Methods.checkCode(jparse)) {
            List<ListBusyDateTimeEntity> listBusyDateTimeEntities = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {
                try {
                    JsonObject sect = medspecs.getAsJsonObject();
                    ListBusyDateTimeEntity listBusyDateTimeEntity = new ListBusyDateTimeEntity();
                    int temp = Methods.checkJsonObjGetInteger(sect, "TimeTableGraf_id");
                    if (temp > 0) listBusyDateTimeEntity.setTimeTableGraf_id(temp);
                    temp = Methods.checkJsonObjGetInteger(sect, "Person_id");
                    if (temp > 0) listBusyDateTimeEntity.setPerson_id(temp);

                    listBusyDateTimeEntities.add(listBusyDateTimeEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return listBusyDateTimeEntities;
        } else return null;
    }

    public void saveJson(String json) {
        List<ListBusyDateTimeEntity> list = new ListBusyDateTimeEntity().parseJson(json);
        new DaoImpl().saveList(list);
    }
}