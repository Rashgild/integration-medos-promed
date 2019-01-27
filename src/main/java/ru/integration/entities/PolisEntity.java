package ru.integration.entities;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "polis", schema = "public", catalog = "integration")
public class PolisEntity {

    private Integer id;
    private PersonEntity person;
    private Integer promedPerson_id;
    private Integer polis_id;
    private Integer omsSpeTerr_id;
    private Integer polisType_id;
    private String polis_Ser;
    private String polis_Num;
    private Integer orgSmoid;
    private Date polis_BegDate;
    private Date polis_EndDate;
    private Integer polisFormType_id;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    @Basic
    @Column(name = "polis_id")
    public Integer getPolis_id() {
        return polis_id;
    }

    public void setPolis_id(Integer polis_id) {
        this.polis_id = polis_id;
    }

    @Basic
    @Column(name = "promedperson_id")
    public Integer getPromedPerson_id() {
        return promedPerson_id;
    }

    public void setPromedPerson_id(Integer promedPerson_id) {
        this.promedPerson_id = promedPerson_id;
    }

    @Basic
    @Column(name = "omsspeterr_id")
    public Integer getOmsSpeTerr_id() {
        return omsSpeTerr_id;
    }

    public void setOmsSpeTerr_id(Integer omsSpeTerr_id) {
        this.omsSpeTerr_id = omsSpeTerr_id;
    }

    @Basic
    @Column(name = "polistype_id")
    public Integer getPolisType_id() {
        return polisType_id;
    }

    public void setPolisType_id(Integer polisType_id) {
        this.polisType_id = polisType_id;
    }

    @Basic
    @Column(name = "polis_ser")
    public String getPolis_Ser() {
        return polis_Ser;
    }

    public void setPolis_Ser(String polis_Ser) {
        this.polis_Ser = polis_Ser;
    }

    @Basic
    @Column(name = "polis_num")
    public String getPolis_Num() {
        return polis_Num;
    }

    public void setPolis_Num(String polis_Num) {
        this.polis_Num = polis_Num;
    }

    @Basic
    @Column(name = "orgsmoid")
    public Integer getOrgSmoid() {
        return orgSmoid;
    }

    public void setOrgSmoid(Integer orgSmoid) {
        this.orgSmoid = orgSmoid;
    }

    @Basic
    @Column(name = "polis_begdate")
    public Date getPolis_BegDate() {
        return polis_BegDate;
    }

    public void setPolis_BegDate(Date polis_BegDate) {
        this.polis_BegDate = polis_BegDate;
    }

    @Basic
    @Column(name = "polis_enddate")
    public Date getPolis_EndDate() {
        return polis_EndDate;
    }

    public void setPolis_EndDate(Date polis_EndDate) {
        this.polis_EndDate = polis_EndDate;
    }

    @Basic
    @Column(name = "polisformtype_id")
    public Integer getPolisFormType_id() {
        return polisFormType_id;
    }

    public void setPolisFormType_id(Integer polisFormType_id) {
        this.polisFormType_id = polisFormType_id;
    }

    @Override
    public String toString() {
        return "PolisEntity{" +
                "id=" + id +
                ", person=" + person.getId() +
                ", polis_id=" + polis_id +
                ", omsSpeTerr_id=" + omsSpeTerr_id +
                ", polisType_id=" + polisType_id +
                ", polis_Ser='" + polis_Ser + '\'' +
                ", polis_Num='" + polis_Num + '\'' +
                ", orgSmoid=" + orgSmoid +
                ", polis_BegDate=" + polis_BegDate +
                ", polis_EndDate=" + polis_EndDate +
                ", polisFormType_id=" + polisFormType_id +
                '}';
    }
}
