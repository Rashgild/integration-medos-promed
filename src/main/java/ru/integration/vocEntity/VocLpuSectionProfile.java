package ru.integration.vocEntity;

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

import ru.integration.util.Methods;

import static ru.integration.util.Methods.checkCode;


@Entity
@Table(name = "vocLpuSectionProfile", schema = "public", catalog = "integration")
public class VocLpuSectionProfile {

    private Integer id;
    private String lpuSectionProfile_id;
    private String Name;
    private String Code;

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
    @Column(name = "lpuSectionProfile_id")
    public String getLpuSectionProfile_id() {
        return lpuSectionProfile_id;
    }

    public void setLpuSectionProfile_id(String lpuSectionProfile_id) {
        this.lpuSectionProfile_id = lpuSectionProfile_id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }


    public List<VocLpuSectionProfile> parseJSON(String json) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        if (Methods.checkCode(jparse)) {
            List<VocLpuSectionProfile> vocLpuSections = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {

                try {
                    JsonObject sect = medspecs.getAsJsonObject();
                    VocLpuSectionProfile vocLpuSection = new VocLpuSectionProfile();

                    vocLpuSection.setLpuSectionProfile_id(Methods.checkJsonObj(sect, "id"));
                    vocLpuSection.setName(Methods.checkJsonObj(sect, "Name"));
                    vocLpuSection.setCode(Methods.checkJsonObj(sect, "Code"));
                    vocLpuSections.add(vocLpuSection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return vocLpuSections;
        } else return null;
    }
}
