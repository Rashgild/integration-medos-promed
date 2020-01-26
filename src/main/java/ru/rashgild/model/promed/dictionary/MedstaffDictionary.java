package ru.rashgild.model.promed.dictionary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class MedstaffDictionary {

    @JsonProperty("MedStaffFact_id")
    private Long medstaffId;

    private Long lpuSectionId;

    public Long getMedstaffId() {
        return medstaffId;
    }

    public void setMedstaffId(Long medstaffId) {
        this.medstaffId = medstaffId;
    }

    public Long getLpuSectionId() {
        return lpuSectionId;
    }

    public void setLpuSectionId(Long lpuSectionId) {
        this.lpuSectionId = lpuSectionId;
    }

    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("error_msg")
        private String errorMessage;

        @JsonProperty("data")
        private List<MedstaffDictionary> data;

        public Integer getError() {
            return error;
        }

        public void setError(Integer error) {
            this.error = error;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public List<MedstaffDictionary> getData() {
            return data;
        }

        public void setData(List<MedstaffDictionary> data) {
            this.data = data;
        }
    }
}
