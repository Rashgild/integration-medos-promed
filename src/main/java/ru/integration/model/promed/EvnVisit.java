package ru.integration.model.promed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EvnVisit {

    @JsonProperty("EvnPLBase_id")
    private Integer evnPlBaseId;

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

    @JsonIgnore
    private Integer medosId;

    public Long getLpuSectionId() {
        return lpuSectionId;
    }

    public void setLpuSectionId(Long lpuSectionId) {
        this.lpuSectionId = lpuSectionId;
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

    public Integer getMedicalCareKindId() {
        return medicalCareKindId;
    }

    public void setMedicalCareKindId(Integer medicalCareKindId) {
        this.medicalCareKindId = medicalCareKindId;
    }

    public Integer getDiagId() {
        return diagId;
    }

    public void setDiagId(Integer diagId) {
        this.diagId = diagId;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
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

    public Integer getEvnPlBaseId() {
        return evnPlBaseId;
    }

    public void setEvnPlBaseId(Integer evnPlBaseId) {
        this.evnPlBaseId = evnPlBaseId;
    }

    public Integer getMedosId() {
        return medosId;
    }

    public void setMedosId(Integer medosId) {
        this.medosId = medosId;
    }
}
