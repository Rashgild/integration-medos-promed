package ru.integration.model.promedEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Evn {

    @JsonProperty("Diag_lid")
    private Integer diagLid;

    @JsonProperty("Diag_id")
    private Integer diagId;

    @JsonProperty("LpuSection_id")
    private Long lpuSectionId;

    @JsonProperty("MedStaffFact_id")
    private Integer medStaffFactId;

    @JsonProperty("TreatmentClass_id")
    private Integer treatmentClassId;

    @JsonProperty("MedicalCareKind_id")
    private Integer medicalCareKindId;

    @JsonProperty("ServiceType_id")
    private Integer serviceTypeId;

    @JsonProperty("PayType_id")
    private Integer payTypeId;

    @JsonProperty("Evn_setDT")
    private String evnSetDt;

    @JsonProperty("VizitType_id")
    private Integer visitTypeId;

    @JsonProperty("ResultDeseaseType_id")
    private Integer resultDeseaseTypeId;

    @JsonProperty("EvnPL_NumCard")
    private String evnPlNumCard;

    @JsonProperty("EvnPL_IsFinish")
    private String isFinish;

    @JsonProperty("ResultClass_id")
    private Integer resultClassId;

    @JsonProperty("Person_id")
    private Integer personId;

    @JsonIgnore
    private Integer medosId;

    public Integer getDiagLid() {
        return diagLid;
    }

    public void setDiagLid(Integer diagLid) {
        this.diagLid = diagLid;
    }

    public Integer getMedStaffFactId() {
        return medStaffFactId;
    }

    public void setMedStaffFactId(Integer medStaffFactId) {
        this.medStaffFactId = medStaffFactId;
    }

    public Integer getTreatmentClassId() {
        return treatmentClassId;
    }

    public void setTreatmentClassId(Integer treatmentClassId) {
        this.treatmentClassId = treatmentClassId;
    }

    public Integer getResultDeseaseTypeId() {
        return resultDeseaseTypeId;
    }

    public void setResultDeseaseTypeId(Integer resultDeseaseTypeId) {
        this.resultDeseaseTypeId = resultDeseaseTypeId;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getEvnPlNumCard() {
        return evnPlNumCard;
    }

    public void setEvnPlNumCard(String evnPlNumCard) {
        this.evnPlNumCard = evnPlNumCard;
    }

    public Integer getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(Integer payTypeId) {
        this.payTypeId = payTypeId;
    }

    public String getEvnSetDt() {
        return evnSetDt;
    }

    public void setEvnSetDt(String evnSetDt) {
        this.evnSetDt = evnSetDt;
    }

    public Integer getVisitTypeId() {
        return visitTypeId;
    }

    public void setVisitTypeId(Integer visitTypeId) {
        this.visitTypeId = visitTypeId;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public Integer getResultClassId() {
        return resultClassId;
    }

    public void setResultClassId(Integer resultClassCode) {

        switch (resultClassCode) {
            case 301:
                this.resultClassId = 180;
                break;
            case 308:
                this.resultClassId = 187;
                break;
            default:
                break;
        }
    }

    public Integer getMedicalCareKindId() {
        return medicalCareKindId;
    }

    public void setMedicalCareKindId(Integer medicalCareKindId) {
        this.medicalCareKindId = medicalCareKindId;
    }

    public Long getLpuSectionId() {
        return lpuSectionId;
    }

    public void setLpuSectionId(Long lpuSectionId) {
        this.lpuSectionId = lpuSectionId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getDiagId() {
        return diagId;
    }

    public void setDiagId(Integer diagId) {
        this.diagId = diagId;
    }

    public Integer getMedosId() {
        return medosId;
    }

    public void setMedosId(Integer medosId) {
        this.medosId = medosId;
    }
}

