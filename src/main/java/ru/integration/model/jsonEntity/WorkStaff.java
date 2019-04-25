package ru.integration.model.jsonEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkStaff {

    @JsonProperty("doctorWf")
    private String workFunction;

    @JsonProperty("doctorLastname")
    private String lastname;

    @JsonProperty("doctorFirstname")
    private String firstname;

    @JsonProperty("doctorMiddlename")
    private String middlename;

    @JsonProperty("doctorSnils")
    private String snils;

    @JsonProperty("promedCode_workstaff")
    private Integer workStaffId;

    public String getWorkFunction() {
        return workFunction;
    }

    public void setWorkFunction(String workFunction) {
        this.workFunction = workFunction;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public Integer getWorkStaffId() {
        return workStaffId;
    }

    public void setWorkStaffId(Integer workStaffId) {
        this.workStaffId = workStaffId;
    }
}
