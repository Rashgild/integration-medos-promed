package ru.integration.vocentities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import ru.integration.util.Methods;

import static ru.integration.util.Methods.checkCode;


@Entity
@Table(name = "vocMedSpecOmc", schema = "public", catalog = "integration")
public class VocMedSpecOmc {

    private Integer id;
    private String promedId;
    private String name;
    private String code;

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
    public String getPromedId() {
        return promedId;
    }

    public void setPromedId(String promedId) {
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

    public List<VocMedSpecOmc> parseJSON(String json) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        if (Methods.checkCode(jparse)) {
            List<VocMedSpecOmc> vocMedSpecOmcMOS = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {
                try {
                    JsonObject medcpec = medspecs.getAsJsonObject();
                    VocMedSpecOmc vocMedSpecOmcMO = new VocMedSpecOmc();
                    if (!medcpec.get("id").toString().equals("null")) {
                        vocMedSpecOmcMO.setPromedId(medcpec.get("id").getAsString());
                    }
                    if (!medcpec.get("Name").toString().equals("null")) {
                        vocMedSpecOmcMO.setName(medcpec.get("Name").getAsString());
                    }
                    if (!medcpec.get("Code").toString().equals("null")) {
                        vocMedSpecOmcMO.setCode(medcpec.get("Code").getAsString());
                    }
                    vocMedSpecOmcMOS.add(vocMedSpecOmcMO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return vocMedSpecOmcMOS;
        } else return null;
    }

}
