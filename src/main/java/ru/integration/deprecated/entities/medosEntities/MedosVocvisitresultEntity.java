package ru.integration.deprecated.entities.medosEntities;

import javax.persistence.*;


@Entity
@Table(name = "vocvisitresult", schema = "sqluser", catalog = "riams")
public class MedosVocvisitresultEntity {
    private Long id;
    private String name;
    private String code;
    private String promedcode1;
    private String promedcode2;

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
    @Column(name = "promedcode1")
    public String getPromedcode1() {
        return promedcode1;
    }
    public void setPromedcode1(String promedcode1) {
        this.promedcode1 = promedcode1;
    }

    @Basic
    @Column(name = "promedcode2")
    public String getPromedcode2() {
        return promedcode2;
    }
    public void setPromedcode2(String promedcode2) {
        this.promedcode2 = promedcode2;
    }

}
