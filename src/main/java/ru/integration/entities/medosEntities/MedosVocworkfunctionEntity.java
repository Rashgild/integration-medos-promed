package ru.integration.entities.medosEntities;

import javax.persistence.*;


@Entity
@Table(name = "vocworkfunction", schema = "sqluser", catalog = "riams")
public class MedosVocworkfunctionEntity {
    private Long id;
    private String name;
    private String code;
    private String shortname;
    private Boolean isnodiagnosis;
    private Boolean isfuncdiag;
    private Boolean islab;
    private Boolean isradiationdiagnosis;
    private Boolean isno039;
    private Integer emailclient;
    private String fssshortname;
    private String fsscode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "shortname")
    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Basic
    @Column(name = "isnodiagnosis")
    public Boolean getIsnodiagnosis() {
        return isnodiagnosis;
    }

    public void setIsnodiagnosis(Boolean isnodiagnosis) {
        this.isnodiagnosis = isnodiagnosis;
    }

    @Basic
    @Column(name = "isfuncdiag")
    public Boolean getIsfuncdiag() {
        return isfuncdiag;
    }

    public void setIsfuncdiag(Boolean isfuncdiag) {
        this.isfuncdiag = isfuncdiag;
    }

    @Basic
    @Column(name = "islab")
    public Boolean getIslab() {
        return islab;
    }

    public void setIslab(Boolean islab) {
        this.islab = islab;
    }

    @Basic
    @Column(name = "isradiationdiagnosis")
    public Boolean getIsradiationdiagnosis() {
        return isradiationdiagnosis;
    }

    public void setIsradiationdiagnosis(Boolean isradiationdiagnosis) {
        this.isradiationdiagnosis = isradiationdiagnosis;
    }

    @Basic
    @Column(name = "isno039")
    public Boolean getIsno039() {
        return isno039;
    }

    public void setIsno039(Boolean isno039) {
        this.isno039 = isno039;
    }

    @Basic
    @Column(name = "emailclient")
    public Integer getEmailclient() {
        return emailclient;
    }

    public void setEmailclient(Integer emailclient) {
        this.emailclient = emailclient;
    }

    @Basic
    @Column(name = "fssshortname")
    public String getFssshortname() {
        return fssshortname;
    }

    public void setFssshortname(String fssshortname) {
        this.fssshortname = fssshortname;
    }

    @Basic
    @Column(name = "fsscode")
    public String getFsscode() {
        return fsscode;
    }

    public void setFsscode(String fsscode) {
        this.fsscode = fsscode;
    }



}
