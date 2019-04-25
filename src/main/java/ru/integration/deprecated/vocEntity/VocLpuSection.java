package ru.integration.deprecated.vocEntity;

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
@Table(name = "vocLpuSection", schema = "public", catalog = "integration")
public class VocLpuSection {

    private Integer id;
    private String lpuSection_id;
    private String lpuBuilding_id;
    private String lpuUnit_Code;
    private Integer lpuUnitType_Code;
    private String lpuUnit_Name;
    //private String LpuSection_setDate;
    //private String LpuSection_disDate;
    private String lpuSectionProfile_Code;
    private String lpuSection_Code;
    private String lpuSection_Name;
    //private String lpuSectionOuter_id;

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
    @Column(name = "lpuSection_id")
    public String getLpuSection_id() {
        return lpuSection_id;
    }

    public void setLpuSection_id(String lpuSection_id) {
        this.lpuSection_id = lpuSection_id;
    }

    @Basic
    @Column(name = "lpuBuilding_id")
    public String getLpuBuilding_id() {
        return lpuBuilding_id;
    }

    public void setLpuBuilding_id(String lpuBuilding_id) {
        this.lpuBuilding_id = lpuBuilding_id;
    }

    @Basic
    @Column(name = "lpuUnit_Code")
    public String getLpuUnit_Code() {
        return lpuUnit_Code;
    }

    public void setLpuUnit_Code(String lpuUnit_Code) {
        this.lpuUnit_Code = lpuUnit_Code;
    }

    @Basic
    @Column(name = "lpuUnitType_Code")
    public Integer getLpuUnitType_Code() {
        return lpuUnitType_Code;
    }

    public void setLpuUnitType_Code(Integer lpuUnitType_Code) {
        this.lpuUnitType_Code = lpuUnitType_Code;
    }

    @Basic
    @Column(name = "lpuUnit_Name")
    public String getLpuUnit_Name() {
        return lpuUnit_Name;
    }

    public void setLpuUnit_Name(String lpuUnit_Name) {
        this.lpuUnit_Name = lpuUnit_Name;
    }

    @Basic
    @Column(name = "lpuSectionProfile_Code")
    public String getLpuSectionProfile_Code() {
        return lpuSectionProfile_Code;
    }

    public void setLpuSectionProfile_Code(String lpuSectionProfile_Code) {
        this.lpuSectionProfile_Code = lpuSectionProfile_Code;
    }

    @Basic
    @Column(name = "lpuSection_Code")
    public String getLpuSection_Code() {
        return lpuSection_Code;
    }

    public void setLpuSection_Code(String lpuSection_Code) {
        this.lpuSection_Code = lpuSection_Code;
    }

    @Basic
    @Column(name = "lpuSection_Name")
    public String getLpuSection_Name() {
        return lpuSection_Name;
    }

    public void setLpuSection_Name(String lpuSection_Name) {
        this.lpuSection_Name = lpuSection_Name;
    }


    public List<VocLpuSection> parseJSON(String json) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        if (Methods.checkCode(jparse)) {
            List<VocLpuSection> vocLpuSections = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {

                try {
                    JsonObject sect = medspecs.getAsJsonObject();
                    VocLpuSection vocLpuSection = new VocLpuSection();

                    vocLpuSection.setLpuSection_id(Methods.checkJsonObj(sect, "LpuSection_id"));
                    vocLpuSection.setLpuBuilding_id(Methods.checkJsonObj(sect, "LpuBuilding_id"));
                    vocLpuSection.setLpuUnit_Code(Methods.checkJsonObj(sect, "LpuUnit_Code"));
                    vocLpuSection.setLpuUnitType_Code(Integer.valueOf(Methods.checkJsonObj(sect, "LpuUnitType_Code")));
                    vocLpuSection.setLpuUnit_Name(Methods.checkJsonObj(sect, "LpuUnit_Name"));
                    vocLpuSection.setLpuSectionProfile_Code(Methods.checkJsonObj(sect, "LpuSectionProfile_Code"));
                    vocLpuSection.setLpuSection_Code(Methods.checkJsonObj(sect, "LpuSection_Code"));
                    vocLpuSection.setLpuSection_Name(Methods.checkJsonObj(sect, "LpuSection_Name"));

                    vocLpuSections.add(vocLpuSection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return vocLpuSections;
        } else return null;
    }


}
