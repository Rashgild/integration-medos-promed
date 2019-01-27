package ru.integration.entities.spo.vocs;

import javax.persistence.*;


@Entity
@Table(name = "auth", schema = "public")
public class vocResultClass {

    private Integer id;
    private Integer promedId;
    private String promdeCode;
    private String name;
    private String medosCode;
    private Integer medosId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "promedId")
    public Integer getPromedId() {
        return promedId;
    }
    public void setPromedId(Integer promedId) {
        this.promedId = promedId;
    }

    @Basic
    @Column(name = "promdeCode")
    public String getPromdeCode() {
        return promdeCode;
    }
    public void setPromdeCode(String promdeCode) {
        this.promdeCode = promdeCode;
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
    @Column(name = "medosCode")
    public String getMedosCode() {
        return medosCode;
    }
    public void setMedosCode(String medosCode) {
        this.medosCode = medosCode;
    }

    @Basic
    @Column(name = "medosId")
    public Integer getMedosId() {
        return medosId;
    }
    public void setMedosId(Integer medosId) {
        this.medosId = medosId;
    }

    //ResultClass
}
