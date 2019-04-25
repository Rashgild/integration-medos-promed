package ru.integration.deprecated.entities.medosEntities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "workfunction", schema = "sqluser", catalog = "riams")
public class MedosWorkfunctionEntity {

    private Long id;
    private Long workfunction_id;
    private String promedcodeWorkstaff;
    private String promedcodeLpusection;


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
    @Column(name = "workfunction_id")
    public Long getWorkfunction_id() {
        return workfunction_id;
    }

    public void setWorkfunction_id(Long workfunction_id) {
        this.workfunction_id = workfunction_id;
    }

    @Basic
    @Column(name = "promedcode_workstaff")
    public String getPromedcodeWorkstaff() {
        return promedcodeWorkstaff;
    }

    public void setPromedcodeWorkstaff(String promedcodeWorkstaff) {
        this.promedcodeWorkstaff = promedcodeWorkstaff;
    }

    @Basic
    @Column(name = "promedcode_lpusection")
    public String getPromedcodeLpusection() {
        return promedcodeLpusection;
    }

    public void setPromedcodeLpusection(String promedcodeLpusection) {
        this.promedcodeLpusection = promedcodeLpusection;
    }
}
