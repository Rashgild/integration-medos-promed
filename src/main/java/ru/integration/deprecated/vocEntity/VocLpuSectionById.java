package ru.integration.deprecated.vocEntity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import ru.integration.util.Methods;

@Entity
@Table(name = "vocLpuSectionbyid", schema = "public", catalog = "integration")
public class VocLpuSectionById {

    /*   LpuSection_setDate	null
       LpuSection_disDate	null*/
    private Integer id;
    private Long LpuSection_id;
    private Integer LpuSectionProfile_id;//	"35173"
    private String LpuSectionProfile_Code;//	"53"
    private String LpuSection_Code;//	"2800"
    private String LpuSection_Name;//	"НЕВРОЛОГИЯ. пол-ка"
    private Integer LpuUnitType_id;//	"2"
/*    MesAgeGroup_id	null
    MESLevel_id	null
    LpuSection_IsHTMedicalCare	0
    LpuSection_KolAmbul	null
    LpuSection_KolJob	null
    LpuSection_PlanAutopShift	null
    LpuSection_PlanResShift	null
    LpuSection_PlanTrip	null
    LpuSection_PlanVisitDay	null
    LpuSection_PlanVisitShift	null
    LpuSectionAge_id	null
    LpuSectionBedProfile_id	null
    LpuSectionOuter_id	null*/

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
    public Long getLpuSection_id() {
        return LpuSection_id;
    }

    public void setLpuSection_id(Long lpuSection_id) {
        LpuSection_id = lpuSection_id;
    }

    @Basic
    @Column(name = "lpuSectionProfile_id")
    public Integer getLpuSectionProfile_id() {
        return LpuSectionProfile_id;
    }

    public void setLpuSectionProfile_id(Integer lpuSectionProfile_id) {
        LpuSectionProfile_id = lpuSectionProfile_id;
    }

    @Basic
    @Column(name = "lpuSectionProfile_Code")
    public String getLpuSectionProfile_Code() {
        return LpuSectionProfile_Code;
    }

    public void setLpuSectionProfile_Code(String lpuSectionProfile_Code) {
        LpuSectionProfile_Code = lpuSectionProfile_Code;
    }

    @Basic
    @Column(name = "lpuSection_Code")
    public String getLpuSection_Code() {
        return LpuSection_Code;
    }

    public void setLpuSection_Code(String lpuSection_Code) {
        LpuSection_Code = lpuSection_Code;
    }

    @Basic
    @Column(name = "lpuSection_Name")
    public String getLpuSection_Name() {
        return LpuSection_Name;
    }

    public void setLpuSection_Name(String lpuSection_Name) {
        LpuSection_Name = lpuSection_Name;
    }

    @Basic
    @Column(name = "lpuUnitType_id")
    public Integer getLpuUnitType_id() {
        return LpuUnitType_id;
    }

    public void setLpuUnitType_id(Integer lpuUnitType_id) {
        LpuUnitType_id = lpuUnitType_id;
    }

    public List<VocLpuSectionById> parseJSON(String json, String lpuSection_id) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        if (Methods.checkCode(jparse)) {
            List<VocLpuSectionById> vocLpuSections = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {

                try {
                    JsonObject sect = medspecs.getAsJsonObject();
                    VocLpuSectionById vocLpuSection = new VocLpuSectionById();

                    //vocLpuSection.setLpuSection_Code(checkJsonObj(sect,"LpuSection_id"));

                    vocLpuSection.setLpuSectionProfile_id(Integer.valueOf(Methods.checkJsonObjGetInteger(sect, "LpuSectionProfile_id")));
                    vocLpuSection.setLpuSectionProfile_Code(Methods.checkJsonObj(sect, "LpuSectionProfile_Code"));
                    vocLpuSection.setLpuSection_Code(Methods.checkJsonObj(sect, "LpuSection_Code"));
                    vocLpuSection.setLpuSection_Name(Methods.checkJsonObj(sect, "LpuSection_Name"));
                    vocLpuSection.setLpuUnitType_id(Methods.checkJsonObjGetInteger(sect, "LpuUnitType_id"));

                    vocLpuSection.setLpuSection_id(Long.valueOf(lpuSection_id));

                    vocLpuSections.add(vocLpuSection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return vocLpuSections;
        } else return null;
    }

}
/* private String LpuSection_id;
    private String LpuSectionProfile_id;//	"35173"
    private String LpuSectionProfile_Code;//	"53"
    private String LpuSection_Code;//	"2800"
    private String LpuSection_Name;//	"НЕВРОЛОГИЯ. пол-ка"
        private String LpuUnitType_id;//	"2"
    */