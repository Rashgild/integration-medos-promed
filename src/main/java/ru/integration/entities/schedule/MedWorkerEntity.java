package ru.integration.entities.schedule;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
@Table(name = "medStaffFactById", schema = "public", catalog = "integration")
public class MedWorkerEntity {

    private Integer id;
    private String person_id;
    private Integer medstaff_id;

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
    @Column(name = "person_id")
    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    @Basic
    @Column(name = "medstaff_id")
    public Integer getMedstaff_id() {
        return medstaff_id;
    }

    public void setMedstaff_id(Integer medstaff_id) {
        this.medstaff_id = medstaff_id;
    }


    public MedWorkerEntity parseJSON(String json, Integer medStaffFact_id) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        if (Methods.checkCode(jparse)) {

            JsonElement projectElement = jparse.get("data");
            jparse = parser.parse(projectElement.toString()).getAsJsonObject();
            MedWorkerEntity medWorkerEntity = new MedWorkerEntity();
            medWorkerEntity.setPerson_id(Methods.checkJsonObj(jparse, "Person_id"));
            medWorkerEntity.setMedstaff_id(medStaffFact_id);
            return medWorkerEntity;

        } else return null;
    }
}
