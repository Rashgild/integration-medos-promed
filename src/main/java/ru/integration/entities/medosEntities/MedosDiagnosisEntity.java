package ru.integration.entities.medosEntities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "diagnosis", schema = "sqluser", catalog = "riams")
public class MedosDiagnosisEntity {
    private Long id;
    private Long idc10_id;
    private Long medcase_id;

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
    @Column(name = "medcase_id")
    public Long getMedcase_id() {
        return medcase_id;
    }
    public void setMedcase_id(Long medcase_id) {
        this.medcase_id = medcase_id;
    }

    @Basic
    @Column(name = "idc10_id")
    public Long getIdc10_id() {
        return idc10_id;
    }
    public void setIdc10_id(Long idc10_id) {
        this.idc10_id = idc10_id;
    }
}
