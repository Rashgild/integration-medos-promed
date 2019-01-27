package ru.integration.vocentities;

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
@Table(name = "medStaffFactById", schema = "public", catalog = "integration")
public class MedStaffFactByIdEntity {

    private Integer id;
    private Long LpuSection_id;
    private Integer MedPersonal_id;
    private Integer MedStaffFact_id;
    private String BeginDate;
    private String EndDate;
   /* private Integer MilitaryRelation_id;//	"3"
    private String  ArriveOrderNumber;//	"2528-л"
    private Integer  ArriveRecordType_id;//	"8"
    private Integer  PostOccupationType_id;//	"1"
    private Integer  MedSpecOms_id;//	"245"
    private Integer FRMPSubdivision_id;//	"10312"
    private Integer WorkMode_id;//	"1"
    private Integer Staff_id;//	"1026"
    private String TabCode;//	"1292"*/


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
    @Column(name = "medPersonal_id")
    public Integer getMedPersonal_id() {
        return MedPersonal_id;
    }

    public void setMedPersonal_id(Integer medPersonal_id) {
        MedPersonal_id = medPersonal_id;
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
    @Column(name = "medStaffFact_id")
    public Integer getMedStaffFact_id() {
        return MedStaffFact_id;
    }

    public void setMedStaffFact_id(Integer medStaffFact_id) {
        MedStaffFact_id = medStaffFact_id;
    }

    @Basic
    @Column(name = "beginDate")
    public String getBeginDate() {
        return BeginDate;
    }

    public void setBeginDate(String beginDate) {
        BeginDate = beginDate;
    }

    @Basic
    @Column(name = "endDate")
    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    /*


    @Basic
    @Column(name = "militaryRelation_id")
    public Integer getMilitaryRelation_id() {
        return MilitaryRelation_id;
    }
    public void setMilitaryRelation_id(Integer militaryRelation_id) {
        MilitaryRelation_id = militaryRelation_id;
    }

    @Basic
    @Column(name = "arriveOrderNumber")
    public String getArriveOrderNumber() {
        return ArriveOrderNumber;
    }
    public void setArriveOrderNumber(String arriveOrderNumber) {
        ArriveOrderNumber = arriveOrderNumber;
    }

    @Basic
    @Column(name = "arriveRecordType_id")
    public Integer getArriveRecordType_id() {
        return ArriveRecordType_id;
    }
    public void setArriveRecordType_id(Integer arriveRecordType_id) {
        ArriveRecordType_id = arriveRecordType_id;
    }

    @Basic
    @Column(name = "postOccupationType_id")
    public Integer getPostOccupationType_id() {
        return PostOccupationType_id;
    }
    public void setPostOccupationType_id(Integer postOccupationType_id) {
        PostOccupationType_id = postOccupationType_id;
    }

    @Basic
    @Column(name = "medSpecOms_id")
    public Integer getMedSpecOms_id() {
        return MedSpecOms_id;
    }
    public void setMedSpecOms_id(Integer medSpecOms_id) {
        MedSpecOms_id = medSpecOms_id;
    }

    @Basic
    @Column(name = "FRMPSubdivision_id")
    public Integer getFRMPSubdivision_id() {
        return FRMPSubdivision_id;
    }
    public void setFRMPSubdivision_id(Integer FRMPSubdivision_id) {
        this.FRMPSubdivision_id = FRMPSubdivision_id;
    }

    @Basic
    @Column(name = "workMode_id")
    public Integer getWorkMode_id() {
        return WorkMode_id;
    }
    public void setWorkMode_id(Integer workMode_id) {
        WorkMode_id = workMode_id;
    }


    @Basic
    @Column(name = "staff_id")
    public Integer getStaff_id() {
        return Staff_id;
    }
    public void setStaff_id(Integer staff_id) {
        Staff_id = staff_id;
    }

    @Basic
    @Column(name = "tabCode")
    public String getTabCode() {
        return TabCode;
    }
    public void setTabCode(String tabCode) {
        TabCode = tabCode;
    }*/



    /*
    //Comments	""
    //Rate	0.5
    //private Integer  Population	null
    //LeaveOrderNumber	null
    //LeaveRecordType_id	null
    //BeginDate	"2016-08-15"
    //EndDate	null
    PriemTime	null
    Contacts	""
    Descr	""
    IsDirRec	1
    IsQueueOnFree	0
    IsOMS	0
    RecType_id	"8"
    DLOBeginDate	null
    DLOEndDate	null
    OfficialSalary	null
    CommonLabourDays	null
    CommonLabourYears	null
    CommonLabourMonths	null
    SpecialLabourDays	null
    SpecialLabourYears	null
    SpecialLabourMonths	null
    QualificationLevel	null
    AdditionalAgreementDate	null
    AdditionalAgreementNumber	null
    DisableWorkPlaceChooseInDocuments	0
    IsNotReception	"0"
    IsDummyWP	"0"
    MedStaffFactOuter_id	null*/


    public List<MedStaffFactByIdEntity> parseJSON(String json, String medStaffFact_id) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        if (Methods.checkCode(jparse)) {
            List<MedStaffFactByIdEntity> medStaffFactByIdEntities = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {

                try {
                    JsonObject sect = medspecs.getAsJsonObject();
                    MedStaffFactByIdEntity medStaffFactById = new MedStaffFactByIdEntity();

                    medStaffFactById.setLpuSection_id(Long.valueOf(Methods.checkJsonObjGetInteger(sect, "LpuSection_id")));
                    medStaffFactById.setMedPersonal_id(Integer.valueOf(Methods.checkJsonObjGetInteger(sect, "MedPersonal_id")));

                    medStaffFactById.setBeginDate((Methods.checkJsonObj(sect, "BeginDate")));
                    medStaffFactById.setEndDate((Methods.checkJsonObj(sect, "EndDate")));

                    medStaffFactById.setMedStaffFact_id(Integer.valueOf(medStaffFact_id));
                    medStaffFactByIdEntities.add(medStaffFactById);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return medStaffFactByIdEntities;
        } else return null;
    }
}
