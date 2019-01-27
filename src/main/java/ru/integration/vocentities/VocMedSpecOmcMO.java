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
@Table(name = "vocMedSpecOmcMO", schema = "public", catalog = "integration")
public class VocMedSpecOmcMO {
    private Integer id;
    private String medSpecOmc_id;

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
    @Column(name = "medSpecOmc_id")
    public String getMedSpecOmc_id() {
        return medSpecOmc_id;
    }

    public void setMedSpecOmc_id(String medSpecOmc_id) {
        this.medSpecOmc_id = medSpecOmc_id;
    }

    public List<VocMedSpecOmcMO> parseJSON(String json) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        if (Methods.checkCode(jparse)) {
            List<VocMedSpecOmcMO> vocMedSpecOmcMOS = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {

                try {
                    JsonObject medcpec = medspecs.getAsJsonObject();
                    if (!medcpec.get("MedSpecOms_id").toString().equals("null")) {
                        VocMedSpecOmcMO vocMedSpecOmcMO = new VocMedSpecOmcMO();
                        vocMedSpecOmcMO.setMedSpecOmc_id(medcpec.get("MedSpecOms_id").getAsString());
                        vocMedSpecOmcMOS.add(vocMedSpecOmcMO);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return vocMedSpecOmcMOS;
        } else return null;
    }

    @Override
    public String toString() {
        return "VocMedSpecOmcMO{" +
                "id=" + id +
                ", medSpecOmc_id='" + medSpecOmc_id + '\'' +
                '}';
    }
}
