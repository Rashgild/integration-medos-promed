package ru.integration.model.medosEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class Visit {

    @JsonProperty("MedStaffFact_id")
    private Integer medStaffId;

    @JsonProperty("MedosId")
    private Integer medosId;

    @JsonProperty("ServiceType_id")
    private Integer serviceTypeId;

    @JsonProperty("DeseaseType_id")
    private Integer deseaseTypeId;

    @JsonProperty("Diag_lid")
    private Integer diagId;

    @JsonProperty("Diag_code")
    private String diagCode;

    @JsonProperty("LpuSection_id")
    private Long lpuSectionId;

    @JsonProperty("diary")
    private String diary;

    @JsonProperty("MedicalCareKind_id")
    private Integer medicalCareKindId;

    @JsonProperty("Mes_id")
    private Long mesId;

    @JsonProperty("PayType_id")
    private String payType;

    @JsonProperty("Evn_setDT")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime visitDate;

    @JsonProperty("VizitType_id")
    private Integer visitTypeId;

    private Boolean firstVisit;

    @JsonProperty("WorkStaffInfo")
    private WorkStaff workStaff;


    public Boolean getFirstVisit() {
        return firstVisit;
    }

    public void setFirstVisit(Boolean firstVisit) {
        this.firstVisit = firstVisit;
    }

    public Integer getMedStaffId() {
        return medStaffId;
    }

    public void setMedStaffId(Integer medStaffId) {
        this.medStaffId = medStaffId;
    }

    public Integer getMedosId() {
        return medosId;
    }

    public void setMedosId(Integer medosId) {
        this.medosId = medosId;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public Integer getDeseaseTypeId() {
        return deseaseTypeId;
    }

    public void setDeseaseTypeId(Integer deseaseTypeId) {
        this.deseaseTypeId = deseaseTypeId;
    }

    public Integer getDiagId() {
        return diagId;
    }

    public void setDiagId(Integer diagId) {
        this.diagId = diagId;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public Long getLpuSectionId() {
        return lpuSectionId;
    }

    public void setLpuSectionId(Long lpuSectionId) {
        this.lpuSectionId = lpuSectionId;
    }

    public String getDiary() {
        return diary;
    }

    public void setDiary(String diary) {
        this.diary = diary;
    }

    public Integer getMedicalCareKindId() {
        return medicalCareKindId;
    }

    public void setMedicalCareKindId(Integer medicalCareKindId) {
        this.medicalCareKindId = medicalCareKindId;
    }

    public Long getMesId() {
        return mesId;
    }

    public void setMesId(Long mesId) {
        this.mesId = mesId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getVisitTypeId() {
        return visitTypeId;
    }

    public void setVisitTypeId(Integer visitTypeId) {
        visitTypeId = visitTypeId;
    }

    public WorkStaff getWorkStaff() {
        return workStaff;
    }

    public void setWorkStaff(WorkStaff workStaff) {
        this.workStaff = workStaff;
    }

    public boolean checkWarnFields(){
        return this.diagCode != null && this.medStaffId != null && this.lpuSectionId != null;
    }

    //TODO visit codes
    public int getVisitType(int count) {
        if (diagCode.contains("Z")) {
            return 106;
        } else if (count == 1) {
            return 103;
        } else {
            return 102;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VisitList {

        @JsonProperty("visits")
        private List<Visit> visits;

        public List<Visit> getVisits() {
            return visits;
        }

        public void setVisits(List<Visit> visits) {
            this.visits = visits;
        }
    }
}