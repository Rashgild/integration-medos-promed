package ru.rashgild.model.medos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@Getter
@Setter
public class Visit {

    @JsonProperty("MedStaffFact_id")
    private Long medStaffId;

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

    public boolean checkWarnFields() {
        return this.diagCode != null && this.medStaffId != null && this.lpuSectionId != null;
    }

    public boolean hasDiag() {
        return this.diagCode != null;
    }

    public boolean hasMedstaff() {
        return this.medStaffId != null;
    }

    public boolean hasLpuSection() {
        return this.lpuSectionId != null;
    }


    /**
     * Get visitType code by params.
     *
     * @param count count of visits
     * @return int code of visit type
     */
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
    @Getter
    @Setter
    public static class VisitList {
        @JsonProperty("visits")
        private List<Visit> visits;
    }
}
