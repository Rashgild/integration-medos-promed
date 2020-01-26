package ru.rashgild.model.promed.dictionary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class LpuSectionDictionary {

    @JsonProperty("LpuSection_id")
    private Long lpuSectionId;

    @JsonProperty("LpuUnit_id")
    private Long lpuUnitId;

    @JsonProperty("LpuUnit_Name")
    private String lpuUnitName;

    @JsonProperty("LpuSection_Name")
    private String lpuSectionName;

    public Long getLpuSectionId() {
        return lpuSectionId;
    }

    public void setLpuSectionId(Long lpuSectionId) {
        this.lpuSectionId = lpuSectionId;
    }

    public Long getLpuUnitId() {
        return lpuUnitId;
    }

    public void setLpuUnitId(Long lpuUnitId) {
        this.lpuUnitId = lpuUnitId;
    }

    public String getLpuUnitName() {
        return lpuUnitName;
    }

    public void setLpuUnitName(String lpuUnitName) {
        this.lpuUnitName = lpuUnitName;
    }

    public String getLpuSectionName() {
        return lpuSectionName;
    }

    public void setLpuSectionName(String lpuSectionName) {
        this.lpuSectionName = lpuSectionName;
    }

    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("error_msg")
        private String errorMessage;

        @JsonProperty("data")
        private List<LpuSectionDictionary> data;

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

        public List<LpuSectionDictionary> getData() {
            return data;
        }

        public void setData(List<LpuSectionDictionary> data) {
            this.data = data;
        }
    }
}
