package ru.rashgild.model.promed;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkPlaceDto {

    @JsonProperty("MedWorker_id")
    private Long medWorkerId;

    @JsonProperty("Lpu_id")
    private Long lpuId;

    @JsonProperty("LpuSection_id")
    private Long lpuSectionId;

    @JsonProperty("beginDate")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;

    @JsonProperty("endDate")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public Long getMedWorkerId() {
        return medWorkerId;
    }

    public void setMedWorkerId(Long medWorkerId) {
        this.medWorkerId = medWorkerId;
    }

    public Long getLpuId() {
        return lpuId;
    }

    public void setLpuId(Long lpuId) {
        this.lpuId = lpuId;
    }

    public Long getLpuSectionId() {
        return lpuSectionId;
    }

    public void setLpuSectionId(Long lpuSectionId) {
        this.lpuSectionId = lpuSectionId;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WorkPlacesDto {
        @JsonProperty("error_code")
        private String error;

        @JsonProperty("error_msg")
        private String errorMessage;

        @JsonProperty("data")
        private List<WorkPlaceDto> workPlaceDtoList;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public List<WorkPlaceDto> getWorkPlaceDtoList() {
            return workPlaceDtoList;
        }

        public void setWorkPlaceDtoList(List<WorkPlaceDto> workPlaceDtoList) {
            this.workPlaceDtoList = workPlaceDtoList;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
