package ru.integration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "medstaff")
public class MedStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Basic
    @Column(name = "medsaff_id")//promed_id
    private Integer medsaffId;

    @JoinColumn(name = "person_id")
    @OneToOne
    private Person personId;

    @Basic
    @Column(name = "medpersonal_id")
    @JsonProperty("MedPersonal_id")
    private Integer medPersonalId;

    @Basic
    @Column(name = "staff_id")
    @JsonProperty("Staff_id")
    private Integer staffId;

    @Basic
    @Column(name = "begin_date")
    @JsonProperty("BeginDate")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;

    @Basic
    @Column(name = "end_date")
    @JsonProperty("EndDate")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Basic
    @Column(name = "lpu_section_id")
    @JsonProperty("LpuSection_id")
    private Integer lpuSectionId;

    @Basic
    @Column(name = "lpu_id")
    @JsonProperty("Lpu_id")
    private Integer lpuId;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MedstaffList {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("data")
        private List<MedStaff> medStaffList;

        public List<MedStaff> getMedStaffList() {
            return medStaffList;
        }

        public void setMedStaffList(List<MedStaff> medStaffList) {
            this.medStaffList = medStaffList;
        }

        public Integer getError() {
            return error;
        }

        public void setError(Integer error) {
            this.error = error;
        }
    }
}
