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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Basic
    @Column(name = "mis_id")
    private Integer misId;

    @Basic
    @Column(name = "Person_id")
    private Integer promedPersonId;

    @Basic
    @Column(name = "lastname")
    @JsonProperty("PersonSurName_SurName")
    private String lastname;

    @Basic
    @Column(name = "firstname")
    @JsonProperty("PersonFirName_FirName")
    private String firstname;

    @Basic
    @Column(name = "middlename")
    @JsonProperty("PersonSecName_SecName")
    private String middlename;

    @Basic
    @Column(name = "birthday")
    @JsonProperty("PersonBirthDay_BirthDay")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Basic
    @Column(name = "sexId")
    @JsonProperty("Person_Sex_id")
    private Integer sexId;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "snils")
    @JsonProperty("PersonSnils_Snils")
    private String snils;

    @Basic
    @Column(name = "socStatusId")
    @JsonProperty("SocStatus_id")
    private Integer socStatusId;

    @Basic
    @Column(name = "uaddressId")
    @JsonProperty("UAddress_id")
    private Integer uAddressId;

    @Basic
    @Column(name = "pAddressId")
    private Integer pAddressId;

    @Basic
    @Column(name = "bAddressId")
    private Integer bAddressId;

    @Basic
    @Column(name = "orgId")
    private Integer orgId; //место работы dbo.Org

    @Basic
    @Column(name = "postId")
    private Integer postId; //должность dbo.Post

    @Basic
    @Column(name = "medstaffFactId")
    private Integer medstaffFactId; // для работников

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMisId() {
        return misId;
    }

    public void setMisId(Integer misId) {
        this.misId = misId;
    }

    public Integer getPromedPersonId() {
        return promedPersonId;
    }

    public void setPromedPersonId(Integer promedPersonId) {
        this.promedPersonId = promedPersonId;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getSexId() {
        return sexId;
    }

    public void setSexId(Integer sexId) {
        this.sexId = sexId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public Integer getSocStatusId() {
        return socStatusId;
    }

    public void setSocStatusId(Integer socStatusId) {
        this.socStatusId = socStatusId;
    }

    public Integer getuAddressId() {
        return uAddressId;
    }

    public void setuAddressId(Integer uAddressId) {
        this.uAddressId = uAddressId;
    }

    public Integer getpAddressId() {
        return pAddressId;
    }

    public void setpAddressId(Integer pAddressId) {
        this.pAddressId = pAddressId;
    }

    public Integer getbAddressId() {
        return bAddressId;
    }

    public void setbAddressId(Integer bAddressId) {
        this.bAddressId = bAddressId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getMedstaffFactId() {
        return medstaffFactId;
    }

    public void setMedstaffFactId(Integer medstaffFactId) {
        this.medstaffFactId = medstaffFactId;
    }


    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PersonList {
        @JsonProperty("data")
        private List<Person> personList;

        public List<Person> getPersonList() {
            return personList;
        }

        public void setPersonList(List<Person> personList) {
            this.personList = personList;
        }
    }
}
