package ru.integration.vocEntity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import ru.integration.util.Methods;

import static ru.integration.util.Methods.checkCode;


@Entity
@Table(name = "medStaffFactList", schema = "public", catalog = "integration")
public class VocMedStaffFactList {

    private Integer id;
    private String medStaffFact_id;
    private String lpuProfile_code;

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
    public String getMedStaffFact_id() {
        return medStaffFact_id;
    }

    public void setMedStaffFact_id(String medStaffFact_id) {
        this.medStaffFact_id = medStaffFact_id;
    }

    @Basic
    @Column(name = "lpuProfile_code")
    public String getLpuProfile_code() {
        return lpuProfile_code;
    }

    public void setLpuProfile_code(String lpuProfile_code) {
        this.lpuProfile_code = lpuProfile_code;
    }

    public List<VocMedStaffFactList> parseJSON(String json, String lpuProfile) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        if (Methods.checkCode(jparse)) {
            List<VocMedStaffFactList> medStaffFactListEntities = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {
                try {
                    JsonObject sect = medspecs.getAsJsonObject();
                    VocMedStaffFactList vocMedStaffFactListEntity = new VocMedStaffFactList();
                    vocMedStaffFactListEntity.setMedStaffFact_id(Methods.checkJsonObj(sect, "MedStaffFact_id"));
                    vocMedStaffFactListEntity.setLpuProfile_code(lpuProfile);

                    medStaffFactListEntities.add(vocMedStaffFactListEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return medStaffFactListEntities;
        } else return null;
    }

}