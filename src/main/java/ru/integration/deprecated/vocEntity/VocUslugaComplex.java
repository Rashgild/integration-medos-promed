package ru.integration.deprecated.vocEntity;

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

@Entity
@Table(name = "vocuslugacomplex", schema = "public", catalog = "integration")
public class VocUslugaComplex {

    private Integer id;
    private Integer promedId;
    private String name;
    private String code;
    private String isSync;

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
    @Column(name = "promedId")
    public Integer getPromedId() {
        return promedId;
    }

    public void setPromedId(Integer promedId) {
        this.promedId = promedId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "isSync")
    public String getIsSync() {
        return isSync;
    }

    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }

    /**
     * Parse json to List of VocUslugaComplex.
     *
     * @param json string Json
     * @return List of VocUslugaComplex
     */
    public List<VocUslugaComplex> parseJson(String json) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        if (Methods.checkCode(jparse)) {
            List<VocUslugaComplex> uslugaComplexEntities = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {

                try {
                    JsonObject sect = medspecs.getAsJsonObject();
                    VocUslugaComplex uslugaComplexEntity = new VocUslugaComplex();

                    uslugaComplexEntity.setName((Methods.checkJsonObj(sect, "Name")));
                    uslugaComplexEntity.setPromedId((Methods.checkJsonObjGetInteger(sect, "id")));
                    uslugaComplexEntity.setCode((Methods.checkJsonObj(sect, "Code")));

                    uslugaComplexEntities.add(uslugaComplexEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return uslugaComplexEntities;
        } else {
            return null;
        }
    }
}