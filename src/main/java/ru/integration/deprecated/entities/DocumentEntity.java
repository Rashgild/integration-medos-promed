package ru.integration.deprecated.entities;

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
@Table(name = "document", schema = "public", catalog = "integration")
public class DocumentEntity {

    private Integer id;
    private PersonEntity person;
    private Integer promedPerson_id;
    private Integer document_id;
    private Integer documentType_id;
    private String document_Ser;
    private String document_Num;
    private Integer orgDep_id;
    private Date document_begDate;
    private Integer kLCountry_id;
    private Integer dkLCountry_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false,nullable = false)
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
    @Column (name = "promedperson_id")
    public Integer getPromedPerson_id() {
        return promedPerson_id;
    }
    public void setPromedPerson_id(Integer promedPerson_id) {
        this.promedPerson_id = promedPerson_id;
    }

    @Basic
    @Column (name = "document_id")
    public Integer getDocument_id() {
        return document_id;
    }
    public void setDocument_id(Integer document_id) {
        this.document_id = document_id;
    }

    @Basic
    @Column (name = "documenttype_id")
    public Integer getDocumentType_id() {
        return documentType_id;
    }
    public void setDocumentType_id(Integer documentType_id) {
        this.documentType_id = documentType_id;
    }

    @Basic
    @Column (name = "document_ser")
    public String getDocument_Ser() {
        return document_Ser;
    }
    public void setDocument_Ser(String document_Ser) {
        this.document_Ser = document_Ser;
    }

    @Basic
    @Column (name = "document_num")
    public String getDocument_Num() {
        return document_Num;
    }
    public void setDocument_Num(String document_Num) {
        this.document_Num = document_Num;
    }

    @Basic
    @Column (name = "orgdep_id")
    public Integer getOrgDep_id() {
        return orgDep_id;
    }
    public void setOrgDep_id(Integer orgDep_id) {
        this.orgDep_id = orgDep_id;
    }

    @Basic
    @Column (name = "document_begdate")
    public Date getDocument_begDate() {
        return document_begDate;
    }
    public void setDocument_begDate(Date document_begDate) {
        this.document_begDate = document_begDate;
    }

    @Basic
    @Column (name = "klcountry_id")
    public Integer getkLCountry_id() {
        return kLCountry_id;
    }
    public void setkLCountry_id(Integer kLCountry_id) {
        this.kLCountry_id = kLCountry_id;
    }

    @Override
    public String toString() {
        return "DocumentEntity{" +
                "id=" + id +
                ", personEntity=" + person.getId() +
                ", document_id=" + document_id +
                ", documentType_id=" + documentType_id +
                ", document_Ser='" + document_Ser + '\'' +
                ", document_Num='" + document_Num + '\'' +
                ", orgDep_id=" + orgDep_id +
                ", document_begDate=" + document_begDate +
                ", kLCountry_id=" + kLCountry_id +
                '}';
    }
}
